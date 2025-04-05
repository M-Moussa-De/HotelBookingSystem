package org.example.hotelbookingsystem;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example.hotelbookingsystem")
@EntityScan("org.example.hotelbookingsystem.model")
@EnableJpaRepositories(basePackages = "org.example.hotelbookingsystem.application.contracts")
public class HotelBookingSystemApplication {
		public static void main(String[] args) {
			// Load environment variables from .env file
			Dotenv dotenv = Dotenv.load();

			// Set system properties for Spring Boot to read
			System.setProperty("DB_URL", dotenv.get("DB_URL"));
			System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(HotelBookingSystemApplication.class, args);
	}
}