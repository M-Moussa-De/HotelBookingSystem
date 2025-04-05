package org.example.hotelbookingsystem.model;

import jakarta.persistence.*;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.example.hotelbookingsystem.model.enums.RoomType;

import java.time.LocalDate;

@Entity
@Table(name="rooms")
public class Room extends BaseEntity {
    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;  // Unique room number for identification

    @Column(name = "floor", nullable = false)
    private int floor;  // Floor number where the room is located

    @Enumerated(EnumType.STRING)  // Storing RoomType as a String in DB
    @Column(name = "type", nullable = false)
    private RoomType type;  // Type of the room (e.g., Single, Double, Suite)

    @Column(name = "price_per_night", nullable = false)
    private Double pricePerNight;  // Price per night for the room

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;  // Current status of the room (Available, Booked, Under Maintenance)

    @Column(name = "max_occupancy", nullable = false)
    private int maxOccupancy;  // Max number of people allowed in the room

    @Column(name = "description", length = 500)
    private String description;  // Description of the room (e.g., view, size, features)

    @Column(name = "date_available")
    private LocalDate dateAvailable;  // Date when room becomes available again (useful for long bookings)

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;  // To indicate if the room is active and available for booking

    // Default constructor (required by JPA)
    public Room() {
    }

    public Room(String roomNumber, int floor, RoomType type, Double pricePerNight,
                RoomStatus status, int maxOccupancy, String description,
                LocalDate dateAvailable, boolean isActive) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.maxOccupancy = maxOccupancy;
        this.description = description;
        this.dateAvailable = dateAvailable;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(LocalDate dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}