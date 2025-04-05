package org.example.hotelbookingsystem.service;

import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}