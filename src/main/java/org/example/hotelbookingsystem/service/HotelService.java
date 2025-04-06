package org.example.hotelbookingsystem.service;

import org.example.hotelbookingsystem.application.contracts.IHotelRepository;
import org.example.hotelbookingsystem.repsonse.AppResponseHandler;
import org.example.hotelbookingsystem.model.Hotel;
import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final IHotelRepository hotelRepository;

    @Autowired
    public HotelService(IHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public AppResponseHandler<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();

        if(!hotels.isEmpty()) {
            return AppResponseHandler.success(hotels);
        } else {
            return AppResponseHandler.notFound("No hotels available in the system");
        }
    }

    public AppResponseHandler<Hotel> getHotelById(int id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        return hotelOptional
                .map(AppResponseHandler::success)
                .orElseGet(() -> AppResponseHandler.notFound("No hotel found with ID: " + id));
    }


    public AppResponseHandler<Hotel> createHotel(Hotel hotel) {
        try {
            Hotel savedHotel = hotelRepository.save(hotel);
            return AppResponseHandler.success(savedHotel);
        } catch (Exception e) {
            return AppResponseHandler.creationFailed("An error occurred while creating the hotel: " + e.getMessage());
        }
    }

    public AppResponseHandler<Hotel> updateHotel(int id, Hotel hotelDetails) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            hotel.setName(hotelDetails.getName());
            hotel.setLocation(hotelDetails.getLocation());
            hotel.setPhoneNumber(hotelDetails.getPhoneNumber());
            hotel.setEmail(hotelDetails.getEmail());
            // Update other fields as necessary
            hotelRepository.save(hotel);
            return AppResponseHandler.success(hotel);
        } else {
            return AppResponseHandler.notFound("No hotel found with ID: " + id);
        }
    }

    public AppResponseHandler<Void> deleteHotel(int id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            hotelRepository.deleteById(id);
            // Return success with no data, using `null` as the parameter to indicate no content
            return AppResponseHandler.success(null);  // Null indicates no content is returned
        } else {
            return AppResponseHandler.notFound("No hotel found with ID: " + id);
        }
    }


    public AppResponseHandler<List<Hotel>> findHotelsByCity(String city) {
        List<Hotel> hotels = hotelRepository.findByLocationCity(city);
        if (!hotels.isEmpty()) {
            return AppResponseHandler.success(hotels);
        } else {
            return AppResponseHandler.notFound("No hotels found in city: " + city);
        }
    }

    public AppResponseHandler<List<Hotel>> findHotelsByName(String name) {
        List<Hotel> hotels = hotelRepository.findByNameContainingIgnoreCase(name);
        if (!hotels.isEmpty()) {
            return AppResponseHandler.success(hotels);
        } else {
            return AppResponseHandler.notFound("No hotels found with the name: " + name);
        }
    }


    public AppResponseHandler<Long> countHotels() {
        long hotelCount = hotelRepository.count();
        return AppResponseHandler.success(hotelCount);
    }

    public AppResponseHandler<Hotel> addRoomToHotel(int hotelId, Room room) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            hotel.getRooms().add(room);
            room.setHotel(hotel);  // Make sure the room knows which hotel it belongs to
            hotelRepository.save(hotel);
            return AppResponseHandler.success(hotel);
        } else {
            return AppResponseHandler.notFound("No hotel found with ID: " + hotelId);
        }
    }


    public AppResponseHandler<List<Hotel>> findHotelsWithAvailableRooms() {
        List<Hotel> hotels = hotelRepository.findByRoomsStatus(RoomStatus.AVAILABLE);
        if (!hotels.isEmpty()) {
            return AppResponseHandler.success(hotels);
        } else {
            return AppResponseHandler.notFound("There are no hotels with available rooms.");
        }
    }


    public AppResponseHandler<Room> updateRoomInHotel(int hotelId, int roomId, Room roomDetails) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            Optional<Room> roomOptional = hotel.getRooms().stream().filter(room -> room.getId() == roomId).findFirst();
            if (roomOptional.isPresent()) {
                Room room = roomOptional.get();
                room.setRoomNumber(roomDetails.getRoomNumber());
                room.setType(roomDetails.getType());
                room.setPricePerNight(roomDetails.getPricePerNight());
                room.setStatus(roomDetails.getStatus());
                room.setMaxOccupancy(roomDetails.getMaxOccupancy());
                room.setDescription(roomDetails.getDescription());
                hotelRepository.save(hotel);  // Save hotel to persist room changes
                return AppResponseHandler.success(room);
            } else {
                return AppResponseHandler.notFound("No room found with ID: " + roomId);
            }
        } else {
            return AppResponseHandler.notFound("No hotel found with ID: " + hotelId);
        }
    }


    public AppResponseHandler<Void> deleteRoomFromHotel(int hotelId, int roomId) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            Optional<Room> roomOptional = hotel.getRooms().stream().filter(room -> room.getId() == roomId).findFirst();
            if (roomOptional.isPresent()) {
                hotel.getRooms().remove(roomOptional.get());
                hotelRepository.save(hotel);
                // Return success with no content, using `null` as the parameter
                return AppResponseHandler.success(null); // Indicating successful deletion
            } else {
                return AppResponseHandler.notFound("No room found with ID: " + roomId);
            }
        } else {
            return AppResponseHandler.notFound("No hotel found with ID: " + hotelId);
        }
    }





}