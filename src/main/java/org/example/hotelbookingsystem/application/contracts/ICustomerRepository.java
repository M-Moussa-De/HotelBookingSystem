package org.example.hotelbookingsystem.application.contracts;

import org.example.hotelbookingsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}