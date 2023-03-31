package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface CompanyService {
    List<Company> addNewCompany(List<Company> company);
    Company updateCompany(Long id, Company company);

    Company getCompany(long anyLong);
}
