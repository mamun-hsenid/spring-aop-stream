package com.hsenid.stream.repository;

import com.hsenid.stream.RowMapper.CustomerRowMapper;
import com.hsenid.stream.domain.Customer;
import com.hsenid.stream.domain.CustomerType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CustomerRepository {


    public JdbcTemplate jdbcTemplate;
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    String customerInsertQuery="insert into customers (name, gender, age, country) values(?, ?, ?, ?)";

    String getAllCustomersQuery = "select * from customers";

    public void addCustomer(Customer customer) {
        jdbcTemplate.update(customerInsertQuery, new Object[] { customer.getName(), customer.getGender(), customer.getAge(), customer.getCountry()});
    }

    public List<Customer>  getAllCustomer() {
        List<Customer> allCustomers = jdbcTemplate.query(getAllCustomersQuery, new CustomerRowMapper());
        return allCustomers;
    }

    public List<Customer> getAllFemaleCustomer() {
        List<Customer> femaleCustomers = jdbcTemplate.query(getAllCustomersQuery, new CustomerRowMapper());

        return femaleCustomers.stream()
                .filter(customer -> customer.getGender().equals(CustomerType.FEMALE.toString()))
                .collect(Collectors.toList());

    }


}
