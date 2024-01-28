package com.hsenid.stream.service;

import com.hsenid.stream.domain.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);

    List<Customer> getAllCustomer();

    List<Customer> getAllFemaleCustomer();
}
