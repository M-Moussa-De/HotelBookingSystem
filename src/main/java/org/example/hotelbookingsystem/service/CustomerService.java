package org.example.hotelbookingsystem.service;

import org.example.hotelbookingsystem.application.contracts.ICustomerRepository;
import org.example.hotelbookingsystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService {
    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}