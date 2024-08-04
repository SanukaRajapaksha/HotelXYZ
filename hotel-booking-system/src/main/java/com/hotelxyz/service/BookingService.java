package com.hotelxyz.service;

import com.hotelxyz.dto.BookingDTO;
import com.hotelxyz.entity.Booking;
import com.hotelxyz.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    public BookingDTO getBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        return modelMapper.map(booking, BookingDTO.class);
    }

    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }
}
