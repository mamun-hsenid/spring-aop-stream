package com.hsenid.stream.controller;

import com.hsenid.stream.domain.Customer;
import com.hsenid.stream.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    public CustomerService customerService;
    @RequestMapping(value ="/add-customer", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return customer;
    }

    @RequestMapping(value ="/get-customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        List<Customer> allCustomers = customerService.getAllCustomer();
        return allCustomers;
    }

    @RequestMapping(value = "/get-female-customer", method = RequestMethod.GET)
    public List<Customer>  getAllFemaleCustomer(){
        List<Customer> femaleCustomers = customerService.getAllFemaleCustomer();
        return femaleCustomers;
    }
}
