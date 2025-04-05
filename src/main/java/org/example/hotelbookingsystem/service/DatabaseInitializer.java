package org.example.hotelbookingsystem.service;

import org.example.hotelbookingsystem.application.contracts.IRoomRepository;
import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.example.hotelbookingsystem.model.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@EnableJpaRepositories("org.example.hotelbookingsystem.repository")
public class DatabaseInitializer implements CommandLineRunner {
    private final IRoomRepository roomRepository;

    @Autowired
    public DatabaseInitializer(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running DatabaseInitializer...");

        // Check if data already exists
        if (roomRepository.count() == 0) {
            // Seed data
            roomRepository.save(new Room("101", 1, RoomType.SINGLE, 100.0, RoomStatus.AVAILABLE, 2, "Cozy room with a city view", LocalDate.parse("2025-05-01"), true));
            roomRepository.save(new Room("102", 1, RoomType.SINGLE, 95.0, RoomStatus.AVAILABLE, 2, "Small room with street view", LocalDate.parse("2025-06-01"), true));
            roomRepository.save(new Room("201", 2, RoomType.DOUBLE, 150.0, RoomStatus.BOOKED, 4, "Double room with garden view",  LocalDate.parse("2025-08-01"), true));
            roomRepository.save(new Room("301", 3, RoomType.SUITE, 250.0, RoomStatus.UNDER_MAINTENANCE, 5, "Luxurious suite with balcony", LocalDate.parse("2025-06-01"), false));
            roomRepository.save(new Room("401", 4, RoomType.PENTHOUSE, 500.0, RoomStatus.AVAILABLE, 6, "Exclusive penthouse with stunning views", LocalDate.parse("2025-05-07"), true));
        }  else {
            System.out.println("Data already exists, skipping seeding.");
        }
    }
}