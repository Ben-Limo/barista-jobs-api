package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;

public interface CompanyTypeService {
    CompanyType createNewCompanyType(CompanyType companyType);

    CompanyType findCompanyTypeById(Long id);
}
