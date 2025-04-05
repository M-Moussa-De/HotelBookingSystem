package org.example.hotelbookingsystem.repository;

import org.example.hotelbookingsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}