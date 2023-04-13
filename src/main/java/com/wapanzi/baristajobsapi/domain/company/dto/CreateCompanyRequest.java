package com.wapanzi.baristajobsapi.domain.company.dto;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;

import java.util.List;

public record CreateCompanyRequest(
        String name,
        String email,
        String description,
        CreateAddressRequest address,
        List<CompanyTypeDto> companyTypes
) {
}
