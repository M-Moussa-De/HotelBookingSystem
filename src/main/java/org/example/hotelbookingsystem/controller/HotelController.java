package org.example.hotelbookingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.hotelbookingsystem.repsonse.AppResponseHandler;
import org.example.hotelbookingsystem.model.Hotel;
import org.example.hotelbookingsystem.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Hotel Management API", description = "Operations related to hotel management")
@RestController
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Get all hotels", description = "Fetches a list of all available hotels")
    @GetMapping
    public AppResponseHandler<List<Hotel>> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "Get hotel by ID", description = "Fetches a specific hotel by its ID")
    @GetMapping("/{id}")
    public AppResponseHandler<Hotel> getHotelById(@PathVariable int id) {
        return hotelService.getHotelById(id);
    }
}