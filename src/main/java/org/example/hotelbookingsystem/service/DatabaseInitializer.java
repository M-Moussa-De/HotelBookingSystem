package org.example.hotelbookingsystem.service;

import org.example.hotelbookingsystem.application.contracts.IHotelRepository;
import org.example.hotelbookingsystem.application.contracts.IRoomRepository;
import org.example.hotelbookingsystem.exception.messages.ErrorMessage;
import org.example.hotelbookingsystem.model.Hotel;
import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.Address;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.example.hotelbookingsystem.model.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@EnableJpaRepositories("org.example.hotelbookingsystem.repository")
public class DatabaseInitializer implements CommandLineRunner {

    private final IRoomRepository roomRepository;
    private final IHotelRepository hotelRepository;

    @Autowired
    public DatabaseInitializer(IRoomRepository roomRepository, IHotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running DatabaseInitializer...");

        // Initialize hotel data if not already present
        if (hotelRepository.count() == 0) {
            seedHotelWithRooms();
        } else {
            System.out.println(ErrorMessage.SEEDING_SKIPPED);
        }

        // Initialize room data if not already present
        if (roomRepository.count() == 0) {
            seedRooms();
        } else {
            System.out.println(ErrorMessage.SEEDING_SKIPPED);
        }
    }

    // Method to seed Hotel with rooms
    private void seedHotelWithRooms() {
        try {
            // Initialize the hotel
            Hotel hotel = new Hotel(
                    "Grand Palace Hotel",
                    new Address("123 Main Street", "Springfield", "Illinois", "United States", "62701"),
                    "123-456-7890",
                    "contact@grandpalace.com"
            );

            // Create rooms for the hotel
            Set<Room> rooms = generateRoomsForHotel();
            rooms.forEach(room -> room.setHotel(hotel));  // Ensure each room is associated with the hotel
            hotel.setRooms(rooms);

            // Save the hotel (rooms will be saved due to cascade)
            hotelRepository.save(hotel);

            System.out.println("Hotel and rooms have been seeded.");
        } catch (Exception e) {
            throw new RuntimeException("Error during hotel seeding: " + e.getMessage(), e);
        }
    }

    // Method to generate and return rooms
    private Set<Room> generateRoomsForHotel() {
        Set<Room> rooms = new HashSet<>();
        IntStream.range(1, 31).forEach(i -> {
            RoomType roomType = determineRoomType(i);
            RoomStatus roomStatus = (i % 2 == 0) ? RoomStatus.BOOKED : RoomStatus.AVAILABLE;
            String roomNumber = String.format("%03d", i); // e.g. "001", "002", ..., "030"
            int floor = (i - 1) / 10 + 1; // Floors are grouped by 10 rooms
            String dateAvailable = String.format("2025-05-%02d", (i % 30) + 1); // "2025-05-01", "2025-05-02", ...

            Room room = new Room(
                    roomNumber,
                    floor,
                    roomType,
                    50.0f + (i * 10), // Price increases with each room
                    roomStatus,
                    2 + (i % 3), // Random max occupancy between 2-4
                    "Room " + roomNumber + " description",
                    LocalDate.parse(dateAvailable, DateTimeFormatter.ISO_DATE), // Parse using ISO format
                    true
            );

            rooms.add(room);
        });

        return rooms;
    }

    // Helper method to determine room type based on the index
    private RoomType determineRoomType(int i) {
        if (i % 4 == 0) {
            return RoomType.PENTHOUSE;
        } else if (i % 3 == 0) {
            return RoomType.SUITE;
        } else if (i % 2 == 0) {
            return RoomType.DOUBLE;
        } else {
            return RoomType.SINGLE;
        }
    }

    // Method to seed rooms if not present
    private void seedRooms() {
        try {
            // Seed 30 rooms
            IntStream.range(1, 31).forEach(i -> {
                RoomType roomType = determineRoomType(i);
                RoomStatus roomStatus = (i % 2 == 0) ? RoomStatus.BOOKED : RoomStatus.AVAILABLE;
                String roomNumber = String.format("%03d", i); // e.g. "001", "002", ..., "030"
                int floor = (i - 1) / 10 + 1; // Floors are grouped by 10 rooms
                String dateAvailable = String.format("2025-05-%02d", (i % 30) + 1); // "2025-05-01", "2025-05-02", ...

                roomRepository.save(new Room(
                        roomNumber,
                        floor,
                        roomType,
                        50.0f + (i * 10), // Price increases with each room
                        roomStatus,
                        2 + (i % 3), // Random max occupancy between 2-4
                        "Room " + roomNumber + " description",
                        LocalDate.parse(dateAvailable, DateTimeFormatter.ISO_DATE), // Parse using ISO format
                        true
                ));
            });

            System.out.println("30 rooms have been seeded.");
        } catch (Exception e) {
            throw new RuntimeException("Error during room seeding: " + e.getMessage(), e);
        }
    }
}