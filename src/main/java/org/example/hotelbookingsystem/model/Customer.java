package org.example.hotelbookingsystem.model;

import jakarta.persistence.Entity;
import org.example.hotelbookingsystem.model.enums.Address;

@Entity
public class Customer extends Person {

    // Constructors
    public Customer() {}

    public Customer(String firstName, String lastName, String email, String phone, Address address) {
        super(firstName, lastName, email, phone, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                '}';
    }
}
