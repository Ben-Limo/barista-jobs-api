package com.wapanzi.baristajobsapi.domain.address.dto;

public record AddressDto(
        Long id,
        String city,
        String country,
        String postalCode,
        String street
) {
}
