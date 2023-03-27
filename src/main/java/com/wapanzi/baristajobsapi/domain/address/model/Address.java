package com.wapanzi.baristajobsapi.domain.address.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min=2, message = "City should have at least 2 characters")
    private String city;
    @NotNull
    @Size(min=2, message = "Country should have at least 2 characters")
    private String country;
    @NotNull
    @Size(min=2, message = "Postal code should have at least 2 characters")
    private  String postalCode;
    @NotNull
    @Size(min=2, message = "Street should have at least 2 characters")
    private String street;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
