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
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@Component
@EnableJpaRepositories("org.example.hotelbookingsystem.repository")
public class DatabaseInitializer implements CommandLineRunner {
    private final IRoomRepository roomRepository;

    @Autowired
    public DatabaseInitializer(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void run(String... args) throws Exception {
        System.out.println("Running DatabaseInitializer...");

        // Check if room data is already present
        if (roomRepository.count() == 0) {
            try {
                // Seed 30 rooms
                IntStream.range(1, 31).forEach(i -> {
                    RoomType roomType;
                    if (i % 4 == 0) {
                        roomType = RoomType.PENTHOUSE;
                    } else if (i % 3 == 0) {
                        roomType = RoomType.SUITE;
                    } else if (i % 2 == 0) {
                        roomType = RoomType.DOUBLE;
                    } else {
                        roomType = RoomType.SINGLE;
                    }

                    RoomStatus roomStatus = (i % 2 == 0) ? RoomStatus.BOOKED : RoomStatus.AVAILABLE;
                    String roomNumber = String.format("%03d", i);  // e.g. "001", "002", ..., "030"
                    int floor = (i - 1) / 10 + 1;  // Floors are grouped by 10 rooms

                    // Format the date with leading zero for the day part
                    String dateAvailable = String.format("2025-05-%02d", (i % 30) + 1);  // "2025-05-01", "2025-05-02", ..., "2025-05-30"

                    // Save each room to the repository
                    roomRepository.save(new Room(
                            roomNumber,
                            floor,
                            roomType,
                            50.0f + (i * 10),  // Price increases with each room
                            roomStatus,
                            2 + (i % 3),  // Random max occupancy between 2-4
                            "Room " + roomNumber + " description",
                            LocalDate.parse(dateAvailable, DateTimeFormatter.ISO_DATE),  // Parse using ISO format
                            true
                    ));
                });

                // Check if rooms are actually saved
                if (roomRepository.count() == 0) {
                    throw new RuntimeException("Seeding failed. No rooms were inserted into the database.");
                }

                System.out.println("30 rooms have been seeded.");
            } catch (Exception e) {
                throw new RuntimeException("Error during seeding: " + e.getMessage(), e);
            }
        } else {
            System.out.println("Data already exists, skipping seeding.");
        }
    }
}