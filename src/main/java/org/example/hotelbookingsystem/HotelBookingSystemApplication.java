package org.example.hotelbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "org.example.hotelbookingsystem")
//@ComponentScan({"org.example.hotelbookingsystem"})
@EntityScan("org.example.hotelbookingsystem.model")
public class HotelBookingSystemApplication {
	public static void main(String[] args) {

		SpringApplication.run(HotelBookingSystemApplication.class, args);
	}
}