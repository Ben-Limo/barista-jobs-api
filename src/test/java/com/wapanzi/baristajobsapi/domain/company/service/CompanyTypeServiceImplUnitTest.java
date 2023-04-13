package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.dto.CompanyTypeDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyTypeRequest;
import com.wapanzi.baristajobsapi.domain.company.exception.CompanyTypeNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
@SpringBootTest(webEnvironment = NONE)
public class CompanyTypeServiceImplUnitTest {
    @Autowired
    private CompanyTypeService service;
    @MockBean
    private CompanyTypeRepository repository;
    private CompanyType companyType;
    private CreateCompanyTypeRequest companyTypeRequest;
    private CompanyTypeDto companyTypeDto;
    @BeforeEach
    public void setup() {
        companyType = new CompanyType(1l, "Barista", LocalDateTime.now(), LocalDateTime.now());
        companyTypeDto = new CompanyTypeDto(1l,"Brewery");
        companyTypeRequest = new CreateCompanyTypeRequest("Barista");
    }
    @Test
    void testCreateCompanyType_returnCreatedCompanyType() {
        // given
        given(repository.save(any(CompanyType.class))).willReturn(
                companyType
        );

        // when
        CompanyType savedCompanyType = service.createNewCompanyType(companyTypeRequest);

        // then
        then(savedCompanyType.getId()).isNotNull();
        then(savedCompanyType.getName()).isEqualTo("Barista");

    }

    @Test
    void testGetCompanyType_returnCompanyType() {
        // given
        Long id = 1l;
        given(repository.findById(anyLong())).willReturn(Optional.of(companyType));

        // when
        CompanyTypeDto foundCompanyType = service.findCompanyTypeById(id);

        // then
        then(foundCompanyType.id()).isNotNull();
        then(foundCompanyType.id()).isEqualTo(1l);
        then(foundCompanyType.name()).isEqualTo("Barista");
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
        CompanyType updatedCompanyType = service.updateCompanyType(id, companyTypeDto);

        // then
        then(updatedCompanyType.getId()).isNotNull();
        then(updatedCompanyType.getId()).isEqualTo(companyType.getId());
        then(updatedCompanyType.getName()).isEqualTo(newUpdate.getName());
    }

    @Test
    void testRemoveCompanyType_successful_return200() {
        // given
        Long id =  1l;
        willDoNothing().given(repository).deleteById(anyLong());

        // when
        service.removeCompanyType(id);

        // then
        BDDMockito.then(repository).should(times(1)).deleteById(id);
    }
}
