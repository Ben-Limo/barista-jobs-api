package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
@SpringBootTest(webEnvironment = NONE)
public class CompanyTypeServiceImplUnitTest {
    @Autowired
    private CompanyTypeService companyTypeService;
    @MockBean
    private CompanyTypeRepository companyTypeRepository;

    @Test
    void testCreateCompanyType_returnCreatedCompanyType() {
        // given
        CompanyType companyType =  new CompanyType(1l, "Barista", LocalDateTime.now(), LocalDateTime.now());
        given(companyTypeRepository.save(any(CompanyType.class))).willReturn(
                companyType
        );

        // when
        CompanyType savedCompanyType = companyTypeService.createNewCompanyType(
                new CompanyType(3l, "Bar", LocalDateTime.now(), LocalDateTime.now())
        );

        // then
        then(savedCompanyType.getId()).isNotNull();
        then(savedCompanyType.getCompanyType()).isEqualTo("Barista");

    }
}
