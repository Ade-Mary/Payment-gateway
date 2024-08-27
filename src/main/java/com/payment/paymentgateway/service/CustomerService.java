package com.payment.paymentgateway.service;


import com.payment.paymentgateway.dto.CustomerDTO;
import com.payment.paymentgateway.model.Customer;
import com.payment.paymentgateway.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CustomerDTO customerDTO) {
        // Convert CustomerDTO to Customer entity
        Customer customer = new Customer();
        customer.setFullName(customerDTO.getFullName());
        customer.setEmail(customerDTO.getEmail());

        // Set other fields from CustomerDTO if necessary

        // Save the customer in the database
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}

