package com.wapanzi.baristajobsapi.domain.company.dto;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CompanyDtoMapper implements Function<Company, CompanyDto> {
    @Override
    public CompanyDto apply(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getDescription(),
                new AddressDto(
                        company.getAddress().getId(),
                        company.getAddress().getCity(),
                        company.getAddress().getCountry(),
                        company.getAddress().getPostalCode(),
                        company.getAddress().getStreet()
                ),
                company.getCompanyTypes().stream().map(companyType ->
                        new CompanyTypeDto(companyType.getId(), companyType.getName())
                ).collect(Collectors.toList())
        );
    }
}
