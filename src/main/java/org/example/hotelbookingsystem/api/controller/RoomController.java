package org.example.hotelbookingsystem.api.controller;

import org.example.hotelbookingsystem.exception.ApiResponse;
import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ApiResponse<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ApiResponse<>(true, "Rooms fetched successfully", HttpStatus.OK.value(), rooms);
    }

    @GetMapping("/{id}")
    public ApiResponse<Room> getRoomById(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.getRoomById(id);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            return ApiResponse.success(room);
        } else {
            return ApiResponse.notFound("Room not found", "No room found with ID: " + id);
        }
    }


    /*
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
     */
}