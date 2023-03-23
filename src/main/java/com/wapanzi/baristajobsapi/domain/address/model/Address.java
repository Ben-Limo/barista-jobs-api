package com.wapanzi.baristajobsapi.domain.address.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String City;
    private String country;
    private  String postalCode;
    private String street;

}
