package org.example.hotelbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example.hotelbookingsystem")
@EntityScan("org.example.hotelbookingsystem.model")
@EnableJpaRepositories(basePackages = "org.example.hotelbookingsystem.application.contracts")
public class HotelBookingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(HotelBookingSystemApplication.class, args);
	}
}