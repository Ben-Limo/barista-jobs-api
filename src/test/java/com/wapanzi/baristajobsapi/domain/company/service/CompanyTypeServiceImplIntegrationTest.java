package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
@Transactional
public class CompanyTypeServiceImplIntegrationTest {
    @Autowired
    private CompanyTypeServiceImpl companyTypeServiceImpl;

    @Test
    void testCreateCompanyType_returnCreatedCompanyType() {
        // given
        CompanyType companyType = new CompanyType(null, "Barista", LocalDateTime.now(), LocalDateTime.now());

        // when
        CompanyType savedCompanyType = companyTypeServiceImpl.createNewCompanyType(companyType);

        // then
        then(savedCompanyType.getId()).isNotNull();
        then(savedCompanyType.getCompanyType()).isEqualTo("Barista");
    }
}