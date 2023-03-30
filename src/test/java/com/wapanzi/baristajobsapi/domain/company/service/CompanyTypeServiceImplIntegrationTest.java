package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class CompanyTypeServiceImplIntegrationTest {
    @Autowired
    private CompanyTypeServiceImpl service;

    @Autowired
    private CompanyTypeRepository repository;

    private CompanyType companyType;
    @BeforeEach
    void setup() {
        companyType = new CompanyType(null, "Barista", LocalDateTime.now(), LocalDateTime.now());
    }
    @Test
    void testCreateCompanyType_returnCreatedCompanyType() {
        // given

        // when
        CompanyType savedCompanyType = service.createNewCompanyType(companyType);

        // then
        then(savedCompanyType.getId()).isNotNull();
        then(savedCompanyType.getName()).isEqualTo("Barista");
    }

    @Test
    void testUpdateCompanyType_happyPath_returnUpdatedDetails() {
        // given
        Long id = 1l;
        CompanyType savedCompanyType = repository.save(companyType);

        // when
        CompanyType updatedCompanyType = service.updateCompanyType(id,
                new CompanyType(1l, "Brewery", LocalDateTime.now(), LocalDateTime.now())
        );

        // then
        then(updatedCompanyType.getId()).isEqualTo(1l);
        then(updatedCompanyType.getName()).isEqualTo("Brewery");
    }
}