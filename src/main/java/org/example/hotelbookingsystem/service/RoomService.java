package org.example.hotelbookingsystem.service;

import org.example.hotelbookingsystem.application.contracts.IRoomRepository;
import org.example.hotelbookingsystem.exception.ApiResponse;
import org.example.hotelbookingsystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final IRoomRepository roomRepository;
    @Autowired
    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public ApiResponse<List<Room>> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        if (!rooms.isEmpty()) {
            return ApiResponse.success(rooms);
        } else {
            return ApiResponse.notFound("Rooms not found", "No rooms are available in the system.");
        }
    }

    public ApiResponse<Room> getRoomById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);

        return roomOptional
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.notFound("Room not found", "No room found with ID: " + id));
    }

}