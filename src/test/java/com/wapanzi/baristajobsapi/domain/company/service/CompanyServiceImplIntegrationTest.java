package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyRepository;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class CompanyServiceImplIntegrationTest {
    @Autowired
    private CompanyServiceImpl service;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private CompanyTypeRepository companyTypeRepository;

    private Company company;
    private List<Company> companies = new ArrayList<>();
    private CompanyType companyType;
    private Address address;
    private List<CompanyType> companyTypes;
    @BeforeEach
    void setup() {
        address = new Address(1L, "Nai", "Kenya",
                "Kaunda St", "2323" , LocalDateTime.now(), LocalDateTime.now());
        companyType = new CompanyType(1L, "Barista", LocalDateTime.now(),
                LocalDateTime.now());
        companyTypes = new ArrayList<>();
        companyTypes.add(companyType);
        company = new Company(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", address, companyTypes,
                LocalDateTime.now(), LocalDateTime.now());
        companies.add(company);
    }
    @Test
    void testAddNewCompany_whenSuccessful_returnNewCompanyDetails() {
        // given
        given(companyRepository.save(any(Company.class))).willReturn(company);
        given(companyTypeRepository.findById(anyLong())).willReturn(Optional.of(companyType));

        // when
        List<Company> savedCompanies = service.addNewCompany(companies);

        // then
        then(savedCompanies.get(0).getId()).isNotNull();
        then(savedCompanies.get(0).getName()).isEqualTo("Brewery");

    }

    @Test
    void testUpdateCompany_whenSuccessful_returnCompanyDetails() {
        // given
        Company updateCompany = new Company(1L, "Coffee House", "info@company.com",
                "description about the company", address, companyTypes,
                LocalDateTime.now(), LocalDateTime.now());

        given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
        given(companyRepository.save(any(Company.class))).willReturn(updateCompany);

        // when
        Company updatedCompany = service.updateCompany(1L, updateCompany);

        // then
        then(updatedCompany.getId()).isNotNull();
        then(updatedCompany.getName()).isEqualTo("Coffee House");
    }
}