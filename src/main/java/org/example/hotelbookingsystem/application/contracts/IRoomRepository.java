package org.example.hotelbookingsystem.application.contracts;

import org.example.hotelbookingsystem.model.Room;
import org.example.hotelbookingsystem.model.enums.RoomStatus;
import org.example.hotelbookingsystem.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
public interface IRoomRepository extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {

    // List<Room> findByType(RoomType type);
    // List<Room> findByPriceBetween(Double minPrice, Double maxPrice);
    //  List<Room> findByCapacityGreaterThanEqual(int capacity);
    //   List<Room> findByStatus(RoomStatus status);

    // Find rooms by a specific amenity (e.g., Wi-Fi, AC)
    // List<Room> findByAmenitiesContains(String amenity);
    // List<Room> findByLocation(String location);
    /*
    @Query("SELECT r FROM Room r WHERE r NOT IN (SELECT b.room FROM Booking b WHERE b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)")
    List<Room> findAvailableRoomsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<Room> findByOrderByPriceAsc();
    List<Room> findByOrderBySizeAsc();
    @Query("SELECT r FROM Room r WHERE r.type = :type AND r.capacity >= :capacity")
    List<Room> findByTypeAndCapacity(@Param("type") RoomType type, @Param("capacity") int capacity);
    @Query("SELECT r FROM Room r WHERE r.type = :type AND r.id NOT IN (SELECT b.room.id FROM Booking b WHERE b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)")
    List<Room> findByTypeAndAvailability(@Param("type") RoomType type, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
     */
}
