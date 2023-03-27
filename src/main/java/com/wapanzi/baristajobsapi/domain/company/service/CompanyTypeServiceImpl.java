package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyTypeServiceImpl implements  CompanyTypeService {
    @Autowired
    private CompanyTypeRepository companyTypeRepository;
    @Override
    public CompanyType createNewCompanyType(CompanyType companyType) {
        return companyTypeRepository.save(companyType);
    }
}
