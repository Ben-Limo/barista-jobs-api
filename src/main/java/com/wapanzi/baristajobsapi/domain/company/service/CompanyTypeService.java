package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.dto.CompanyTypeDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyTypeRequest;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;

public interface CompanyTypeService {
    CompanyType createNewCompanyType(CreateCompanyTypeRequest request);

    CompanyTypeDto findCompanyTypeById(Long id);
    CompanyType updateCompanyType(Long id, CompanyTypeDto companyTypeDto);

    void removeCompanyType(Long id);

}
