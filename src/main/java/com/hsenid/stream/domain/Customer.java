package com.hsenid.stream.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int customerId;
    @Column(name = "name")
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "gender")
    String gender;
    @Column(name = "country")
    String country;
}
