package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.exception.CompanyTypeNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyTypeServiceImpl implements  CompanyTypeService {
    @Autowired
    private CompanyTypeRepository repository;
    @Override
    public CompanyType createNewCompanyType(CompanyType companyType) {
        return repository.save(companyType);
    }

    @Override
    @Cacheable("company_types")
    public CompanyType findCompanyTypeById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new CompanyTypeNotFoundException());
    }

    @Override
    public CompanyType updateCompanyType(Long id, CompanyType newUpdate) {
        CompanyType savedCompanyType = repository.findById(id)
                .orElseThrow(() -> new CompanyTypeNotFoundException());

        savedCompanyType.setCompanyType(newUpdate.getCompanyType());
        savedCompanyType.setUpdatedAt(LocalDateTime.now());

        return repository.save(savedCompanyType);
    }
}
