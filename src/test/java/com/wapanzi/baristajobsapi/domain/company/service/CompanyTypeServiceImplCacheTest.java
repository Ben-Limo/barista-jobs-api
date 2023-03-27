package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class CompanyTypeServiceImplCacheTest {
    @Autowired
    private CompanyTypeService service;
    @MockBean
    private CompanyTypeRepository repository;

    @Test
    void testGetCompanyType_forMultipleRequest_returnFromCache() {
        // given
        Long id = 2l;
        given(repository.findById(anyLong())).willReturn(
                Optional.of(new CompanyType(2l, "Barista", LocalDateTime.now(), LocalDateTime.now()))
        );

        // when
        service.findCompanyTypeById(id);
        service.findCompanyTypeById(id);
        service.findCompanyTypeById(id);

        // then
        then(repository).should(times(1)).findById(id);
    }
}
