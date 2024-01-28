package com.hsenid.stream.RowMapper;

import com.hsenid.stream.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setAge(rs.getInt("age"));
        customer.setGender(rs.getString("gender"));
        customer.setCountry(rs.getString("country"));
        return customer;
    }
}
