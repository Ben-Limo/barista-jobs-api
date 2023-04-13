package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.dto.CompanyTypeDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyTypeRequest;
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
    public CompanyType createNewCompanyType(CreateCompanyTypeRequest request) {
        CompanyType companyType = new CompanyType(
                null,
                request.name(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return repository.save(companyType);
    }

    @Override
    @Cacheable("company_types")
    public CompanyTypeDto findCompanyTypeById(Long id) {
        return repository.findById(id)
                .map(companyType -> new CompanyTypeDto(
                       companyType.getId(),
                       companyType.getName()
                ))
                .orElseThrow(()-> new CompanyTypeNotFoundException());
    }

    @Override
    public CompanyType updateCompanyType(Long id, CompanyTypeDto companyTypeDto) {
        CompanyType savedCompanyType = repository.findById(id)
                .orElseThrow(() -> new CompanyTypeNotFoundException());

        savedCompanyType.setName(companyTypeDto.name());
        savedCompanyType.setUpdatedAt(LocalDateTime.now());

        return repository.save(savedCompanyType);
    }

    @Override
    public void removeCompanyType(Long id) {
        repository.deleteById(id);

    }
}
