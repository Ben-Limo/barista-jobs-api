package com.wapanzi.baristajobsapi.domain.company.dto;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;

import java.util.List;

public record CompanyDto(
        Long id,
        String name,
        String email,
        String description,
        AddressDto address,
        List<CompanyTypeDto> companyTypes
) {
}
