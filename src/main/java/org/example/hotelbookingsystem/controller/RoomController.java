package org.example.hotelbookingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.hotelbookingsystem.repsonse.AppResponseHandler;
import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.example.hotelbookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Room Management API", description = "Operations related to room management")
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "Get all rooms", description = "Fetches a list of all available rooms")
    @GetMapping
    public AppResponseHandler<List<Room>> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Operation(summary = "Get room by ID", description = "Fetches a specific room by its ID")
    @GetMapping("/{id}")
    public AppResponseHandler<Room> getRoomById(@PathVariable int id) {
        return roomService.getRoomById(id);
    }

    @Operation(summary = "Create a new room", description = "Creates a new room")
    @PostMapping
    public AppResponseHandler<Room> createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @Operation(summary = "Update a room", description = "Updates an existing room with the provided details. If the room exists, it is updated."
    )
    @PutMapping("/{id}")
    public AppResponseHandler<Room> updateRoom(@PathVariable int id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @Operation(summary = "Delete a room by ID",
            description = "Deletes a room from the system by its ID. Returns success if the room is deleted, or an error if the room is not found."
    )
    @DeleteMapping("/{id}")
    public AppResponseHandler<Void> deleteRoom(@PathVariable int id) {
        return roomService.deleteRoom(id);
    }

    @Operation(summary = "Filter rooms", description = "Filter rooms based on various criteria like room type, price, floor, etc.")
    @GetMapping("/filter")
    public AppResponseHandler<List<Room>> filterRooms(
            @Parameter(description = "The type of room to search for", example = "SINGLE") @RequestParam(required = false) String roomType,
            @Parameter(description = "The minimum price of the room", example = "50.0") @RequestParam(required = false) Double minPrice,
            @Parameter(description = "The maximum price of the room", example = "200.0") @RequestParam(required = false) Double maxPrice,
            @Parameter(description = "The floor where the room is located", example = "1") @RequestParam(required = false) Integer floor,
            @Parameter(description = "The maximum occupancy of the room", example = "2") @RequestParam(required = false) Integer maxOccupancy,
            @Parameter(description = "The status of the room (e.g., AVAILABLE, BOOKED, UNDER_MAINTENANCE)", example = "AVAILABLE") @RequestParam(required = false) String roomStatus,
            @Parameter(description = "The date when the room becomes available (YYYY-MM-DD)", example = "2025-05-01") @RequestParam(required = false) String dateAvailableFrom,
            @Parameter(description = "The date when the room becomes available (YYYY-MM-DD)", example = "2025-06-01") @RequestParam(required = false) String dateAvailableTo) {

        // Convert roomStatus string to RoomStatus enum if provided
        RoomStatus status = roomStatus != null ? RoomStatus.valueOf(roomStatus) : null;

        // Convert date strings to LocalDate if provided
        LocalDate fromDate = dateAvailableFrom != null ? LocalDate.parse(dateAvailableFrom) : null;
        LocalDate toDate = dateAvailableTo != null ? LocalDate.parse(dateAvailableTo) : null;

        return roomService.filterRooms(roomType, minPrice, maxPrice, floor, maxOccupancy, status, fromDate, toDate);
    }
}