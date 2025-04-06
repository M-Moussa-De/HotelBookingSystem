package org.example.hotelbookingsystem.application.contracts;

import org.example.hotelbookingsystem.model.Hotel;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByLocationCity(String city);
    List<Hotel> findByNameContainingIgnoreCase(String name);
    List<Hotel> findByRoomsStatus(RoomStatus roomStatus);
}