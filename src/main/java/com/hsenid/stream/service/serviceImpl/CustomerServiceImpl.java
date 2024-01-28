package com.hsenid.stream.service.serviceImpl;

import com.hsenid.stream.domain.Customer;
import com.hsenid.stream.repository.CustomerRepository;
import com.hsenid.stream.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }
    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> allCustomers =  customerRepository.getAllCustomer();
        return allCustomers;
    }
    @Override
    public List<Customer> getAllFemaleCustomer() {
        List<Customer> allCustomers = customerRepository.getAllFemaleCustomer();
        return allCustomers;
    }
}
