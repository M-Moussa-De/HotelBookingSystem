package org.example.hotelbookingsystem.model;

import jakarta.persistence.*;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.example.hotelbookingsystem.model.enums.RoomType;

import java.time.LocalDate;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String roomNumber;
    @Column(nullable = false)
    private int floor;
    @Enumerated(EnumType.STRING)  // Storing RoomType as a String in DB
    @Column(nullable = false)
    private RoomType type;

    @Column(nullable = false)
    private float pricePerNight;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Column(nullable = false)
    private int maxOccupancy;

    @Column(length = 500)
    private String description;

    private LocalDate dateAvailable;

    @Column(nullable = false)
    private boolean isActive = true;

    // Default constructor (required by JPA)
    public Room() {
    }

    public Room(String roomNumber, int floor, RoomType type, float pricePerNight,
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

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
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