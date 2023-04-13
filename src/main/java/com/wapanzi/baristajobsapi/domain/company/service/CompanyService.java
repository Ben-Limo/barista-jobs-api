package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.dto.CompanyDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyRequest;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyService {
    List<Company> addListOfNewCompanies(List<CreateCompanyRequest> createCompanyRequest);
    Company updateCompany(Long id, CompanyDto companyDto);

    CompanyDto getCompany(long anyLong);

    void removeCompany(long id);
}
