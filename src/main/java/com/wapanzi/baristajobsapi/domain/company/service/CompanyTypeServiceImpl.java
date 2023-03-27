package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.exception.CompanyTypeNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CompanyTypeServiceImpl implements  CompanyTypeService {
    @Autowired
    private CompanyTypeRepository companyTypeRepository;
    @Override
    public CompanyType createNewCompanyType(CompanyType companyType) {
        return companyTypeRepository.save(companyType);
    }

    @Override
    @Cacheable("company_types")
    public CompanyType findCompanyTypeById(Long id) {
        return companyTypeRepository.findById(id)
                .orElseThrow(()-> new CompanyTypeNotFoundException());
    }
}
