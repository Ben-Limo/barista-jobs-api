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
import java.util.ArrayList;
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
            List<CompanyType> companyTypes = new ArrayList<>();
            for (CompanyType companyType : company.getCompanyTypes()) {
                companyType = companyTypeRepository.findById(companyType.getId())
                        .orElseThrow(() -> new CompanyTypeNotFoundException());
                companyTypes.add(companyType);
            }
            company.setCompanyTypes(companyTypes);
        }

        return companies.stream().map(companyRepository::save).collect(Collectors.toList());
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        LocalDateTime now = LocalDateTime.now();

        Company savedCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException());

        savedCompany.setName(company.getName());
        savedCompany.setUpdatedAt(now);
        return companyRepository.save(savedCompany);
    }

    @Override
    public Company getCompany(long id) {
        Company savedCompany = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFoundException()
        );

        return savedCompany;
    }

    @Override
    public void removeCompany(long id) {
        companyRepository.deleteById(id);
    }
}
