package com.wapanzi.baristajobsapi.domain.company.service;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.dto.*;
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
    @Autowired
    private CompanyDtoMapper companyDtoMapper;
    @Override
    public List<Company> addListOfNewCompanies(List<CreateCompanyRequest> createCompanyRequest) {
        LocalDateTime now = LocalDateTime.now();
        List<Company> companies = new ArrayList<>();
        for (CreateCompanyRequest companyRequest : createCompanyRequest) {
            Company company = new Company();
            Address address = new Address();
            address.setUpdatedAt(LocalDateTime.now());
            company.setName(companyRequest.name());
            company.setEmail(companyRequest.email());
            company.setDescription(companyRequest.description());
            company.setCreatedAt(now);
            company.setUpdatedAt(now);
            address.setCity(companyRequest.address().city());
            address.setStreet(companyRequest.address().street());
            address.setCountry(companyRequest.address().country());
            address.setPostalCode(companyRequest.address().postalCode());
            address.setCreatedAt(LocalDateTime.now());
            company.setAddress(address);
            List<CompanyType> companyTypes = new ArrayList<>();
            for (CompanyTypeDto companyTypeReq : companyRequest.companyTypes()) {
                CompanyType companyType = companyTypeRepository.findById(companyTypeReq.id())
                        .orElseThrow(() -> new CompanyTypeNotFoundException());
                companyTypes.add(companyType);
            }
            company.setCompanyTypes(companyTypes);
            companies.add(company);
        }

        return companies.stream().map(companyRepository::save).collect(Collectors.toList());
    }

    @Override
    public Company updateCompany(Long id, CompanyDto companyDto) {

        Company savedCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException());

        savedCompany.setName(companyDto.name());
        savedCompany.setEmail(companyDto.email());
        savedCompany.setDescription(companyDto.description());
        savedCompany.getAddress().setCity(companyDto.address().city());
        savedCompany.getAddress().setStreet(companyDto.address().street());
        savedCompany.getAddress().setCountry(companyDto.address().country());
        savedCompany.getAddress().setPostalCode(companyDto.address().postalCode());
        savedCompany.getAddress().setUpdatedAt(LocalDateTime.now());
        // update company type pending
        savedCompany.setUpdatedAt(LocalDateTime.now());
        return companyRepository.save(savedCompany);
    }

    @Override
    public CompanyDto getCompany(long id) {
        CompanyDto savedCompany = companyRepository.findById(id)
                .map(companyDtoMapper)
                .orElseThrow(
                () -> new CompanyNotFoundException()
        );
        return savedCompany;
    }

    @Override
    public void removeCompany(long id) {
        companyRepository.deleteById(id);
    }
}
