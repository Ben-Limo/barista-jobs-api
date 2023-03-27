package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.exception.CompanyTypeNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
@SpringBootTest(webEnvironment = NONE)
public class CompanyTypeServiceImplUnitTest {
    @Autowired
    private CompanyTypeService service;
    @MockBean
    private CompanyTypeRepository repository;
    private CompanyType companyType;

    @BeforeEach
    public void setup() {
        companyType = new CompanyType(1l, "Barista", LocalDateTime.now(), LocalDateTime.now());
    }
    @Test
    void testCreateCompanyType_returnCreatedCompanyType() {
        // given
        given(repository.save(any(CompanyType.class))).willReturn(
                companyType
        );

        // when
        CompanyType savedCompanyType = service.createNewCompanyType(
                new CompanyType(3l, "Bar", LocalDateTime.now(), LocalDateTime.now())
        );

        // then
        then(savedCompanyType.getId()).isNotNull();
        then(savedCompanyType.getCompanyType()).isEqualTo("Barista");

    }

    @Test
    void testGetCompanyType_returnCompanyType() {
        // given
        Long id = 1l;
        given(repository.findById(anyLong())).willReturn(Optional.of(companyType));

        // when
        CompanyType foundCompanyType = service.findCompanyTypeById(id);

        // then
        then(foundCompanyType.getId()).isNotNull();
        then(foundCompanyType.getId()).isEqualTo(1l);
        then(foundCompanyType.getCompanyType()).isEqualTo("Barista");
    }

    @Test
    void testGetCompanyType_forMissingCompanyType_returnNotFound() {
        // given
        Long id = 23l;

        // when
        Throwable throwable  = catchThrowable(() -> service.findCompanyTypeById(id));

        // then
        BDDAssertions.then(throwable).isInstanceOf(CompanyTypeNotFoundException.class);
    }

    @Test
    void testUpdateCompanyType_happyPath_returnUpdatedDetails() {
       // given
        Long id  = 1l;
        CompanyType newUpdate = new CompanyType(1l, "Brewery", LocalDateTime.now(), LocalDateTime.now());
        given(repository.findById(anyLong())).willReturn(Optional.of(companyType));
        given(repository.save(any(CompanyType.class))).willReturn(newUpdate);
       // when
        CompanyType updatedCompanyType = service.updateCompanyType(id, newUpdate);

        // then
        then(updatedCompanyType.getId()).isNotNull();
        then(updatedCompanyType.getId()).isEqualTo(companyType.getId());
        then(updatedCompanyType.getCompanyType()).isEqualTo(newUpdate.getCompanyType());
    }

}
