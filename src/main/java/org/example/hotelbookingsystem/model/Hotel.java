package org.example.hotelbookingsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.example.hotelbookingsystem.model.enums.Address;

import java.util.Set;

@Entity
public class Hotel extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Address location;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms;

    // Constructors
    public Hotel() {
    }

    public Hotel(String name, Address location, String phoneNumber, String email) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
