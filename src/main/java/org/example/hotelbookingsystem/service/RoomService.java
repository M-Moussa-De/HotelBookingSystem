package org.example.hotelbookingsystem.service;

import jakarta.persistence.criteria.Predicate;
import org.example.hotelbookingsystem.application.contracts.IRoomRepository;
import org.example.hotelbookingsystem.exception.ApiResponse;
import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
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
    public ApiResponse<Room> getRoomById(int id) {
        Optional<Room> roomOptional = roomRepository.findById(id);

        return roomOptional
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.notFound("Room not found", "No room found with ID: " + id));
    }
    public ApiResponse<Room> createRoom(Room room) {
        try {
            Room savedRoom = roomRepository.save(room);

            return ApiResponse.success(savedRoom);
        } catch (Exception e) {
            return ApiResponse.creationFailed("Room creation failed", "An error occurred while creating the room: " + e.getMessage());
        }
    }
    public ApiResponse<Room> updateRoom(int id, Room roomDetails) {
        Optional<Room> existingRoomOptional = roomRepository.findById(id);

        if (existingRoomOptional.isPresent()) {
            Room existingRoom = existingRoomOptional.get();

            if (isRoomDetailsEmpty(roomDetails)) {
                return ApiResponse.notModified("No data provided", "The request body is empty, so no update was performed.");
            }

            boolean isUpdated = false;

            if (roomDetails.getFloor() != existingRoom.getFloor()) {
                existingRoom.setFloor(roomDetails.getFloor());
                isUpdated = true;
            }
            if (roomDetails.getType() != null && !roomDetails.getType().equals(existingRoom.getType())) {
                existingRoom.setType(roomDetails.getType());
                isUpdated = true;
            }
            if (roomDetails.getPricePerNight() != existingRoom.getPricePerNight()) {
                existingRoom.setPricePerNight(roomDetails.getPricePerNight());
                isUpdated = true;
            }
            if (roomDetails.getStatus() != null && !roomDetails.getStatus().equals(existingRoom.getStatus())) {
                existingRoom.setStatus(roomDetails.getStatus());
                isUpdated = true;
            }
            if (roomDetails.getMaxOccupancy() != existingRoom.getMaxOccupancy()) {
                existingRoom.setMaxOccupancy(roomDetails.getMaxOccupancy());
                isUpdated = true;
            }
            if (roomDetails.getDescription() != null && !roomDetails.getDescription().equals(existingRoom.getDescription())) {
                existingRoom.setDescription(roomDetails.getDescription());
                isUpdated = true;
            }
            if (roomDetails.getDateAvailable() != null && !roomDetails.getDateAvailable().equals(existingRoom.getDateAvailable())) {
                existingRoom.setDateAvailable(roomDetails.getDateAvailable());
                isUpdated = true;
            }
            if (roomDetails.isActive() != existingRoom.isActive()) {
                existingRoom.setActive(roomDetails.isActive());
                isUpdated = true;
            }

            // If no changes were made, return a response indicating no update was necessary
            if (!isUpdated) {
                return ApiResponse.notModified("No changes detected", "The provided room details are identical to the existing room.");
            }

            // Save the updated room back to the repository
            Room updatedRoom = roomRepository.save(existingRoom);
            return ApiResponse.update();

        } else {
            return ApiResponse.notFound("Room not found", "No room found with ID: " + id);
        }
    }
    public ApiResponse<Void> deleteRoom(int id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            roomRepository.delete(roomOptional.get());
            return ApiResponse.delete("Room deleted successfully", "Room with ID " + id + " has been successfully deleted.");
        } else {
            return ApiResponse.notFound("Room not found", "No room found with ID: " + id);
        }
    }

    //Filters
    public ApiResponse<List<Room>> filterRooms(String roomType, Double minPrice, Double maxPrice, Integer floor,
                                               Integer maxOccupancy, RoomStatus roomStatus, LocalDate dateAvailableFrom,
                                               LocalDate dateAvailableTo) {

        List<Room> rooms = roomRepository.findAll((Specification<Room>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            // Apply filters based on search criteria
            if (StringUtils.hasText(roomType)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), roomType));
            }
            if (minPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("pricePerNight"), minPrice));
            }
            if (maxPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("pricePerNight"), maxPrice));
            }
            if (floor != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("floor"), floor));
            }
            if (maxOccupancy != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("maxOccupancy"), maxOccupancy));
            }
            if (roomStatus != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), roomStatus));
            }
            if (dateAvailableFrom != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("dateAvailable"), dateAvailableFrom));
            }
            if (dateAvailableTo != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("dateAvailable"), dateAvailableTo));
            }

            return predicate;
        });

        // Return an ApiResponse with the rooms found
        if (!rooms.isEmpty()) {
            return ApiResponse.success(rooms);
        } else {
            return ApiResponse.notFound("No rooms found", "No rooms match the search criteria.");
        }
    }

    // Helper method to check if the roomDetails object is empty
    private boolean isRoomDetailsEmpty(Room roomDetails) {
        return  roomDetails.getFloor() == 0 &&
                roomDetails.getType() == null &&
                roomDetails.getPricePerNight() == 0 &&
                roomDetails.getRoomNumber() == null &&
                roomDetails.getStatus() == null &&
                roomDetails.getMaxOccupancy() == 0 &&
                roomDetails.getDescription() == null &&
                roomDetails.getDateAvailable() == null &&
                !roomDetails.isActive();  // Active is false in case no data is provided
    }
}