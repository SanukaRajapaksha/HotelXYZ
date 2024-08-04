package com.hotelxyz.service;



import com.hotelxyz.entity.Room;
import com.hotelxyz.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.hotelxyz.room.dto.RoomDTO;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = modelMapper.map(roomDTO, Room.class);
        Room savedRoom = roomRepository.save(room);
        return modelMapper.map(savedRoom, RoomDTO.class);
    }

    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room room = modelMapper.map(roomDTO, Room.class);
        room.setId(id);
        Room updatedRoom = roomRepository.save(room);
        return modelMapper.map(updatedRoom, RoomDTO.class);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
