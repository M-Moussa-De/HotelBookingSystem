package org.example.hotelbookingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.hotelbookingsystem.model.enums.Address;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Email
    @Column(nullable = false, length = 100)
    private String email;

    @NotNull
    @Size(min = 10, max = 15)
    @Column(nullable = false, length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Address address;

    // Constructors
    public Person() {}

    public Person(String firstName, String lastName, String email, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // Helper methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isValidEmail() {
        return email != null && email.contains("@");
    }

    public boolean isValidPhone() {
        return phone != null && phone.matches("\\d{10}");
    }
}