package org.example.hotelbookingsystem.application.contracts;

import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IRoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByType(RoomType type);
}