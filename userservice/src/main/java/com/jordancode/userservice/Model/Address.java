package com.jordancode.userservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(length = 30, nullable = false)
    private String country;
    @Column(length = 30, nullable = false)
    private String city;
    @Column(length = 30)
    private String province;
    @Column(length = 150, nullable = false)
    private String street;
    @Column(length = 20, nullable = false)
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
}
