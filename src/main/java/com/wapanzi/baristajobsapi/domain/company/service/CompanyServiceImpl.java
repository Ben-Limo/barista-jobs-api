package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.exception.CompanyNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.exception.CompanyTypeNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyRepository;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyTypeRepository companyTypeRepository;


    @Override
    public List<Company> addNewCompany(List<Company> companies) {
        LocalDateTime now = LocalDateTime.now();
        for (Company company : companies) {
            company.setCreatedAt(now);
            company.setUpdatedAt(now);
            company.getAddress().setCreatedAt(now);
            company.getAddress().setUpdatedAt(now);
            for (CompanyType companyType : company.getCompanyTypes()) {
                companyType = companyTypeRepository.findById(companyType.getId())
                        .orElseThrow(() -> new CompanyTypeNotFoundException());
                companyType.setUpdatedAt(now);
            }
        }

        return companies.stream().map(companyRepository::save).collect(Collectors.toList());
    }

    @Override
    public Company updateCompany(Company company) {
        LocalDateTime now = LocalDateTime.now();

        Company savedCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new CompanyNotFoundException());

        savedCompany.setUpdatedAt(now);
        return companyRepository.save(company);
    }
}
