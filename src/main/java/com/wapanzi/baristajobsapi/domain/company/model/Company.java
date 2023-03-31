package com.wapanzi.baristajobsapi.domain.company.model;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, message = "Company name should have at least 2 characters")
    private String name;

    @Email(message = "Wrong input. please enter a valid email address")
    private String email;

    @Size(min = 10, message = "Description should have at least 50 characters")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany
    @JoinTable(name="company_company_type",
            joinColumns = @JoinColumn(name = "company_type_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private List<CompanyType> companyTypes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
