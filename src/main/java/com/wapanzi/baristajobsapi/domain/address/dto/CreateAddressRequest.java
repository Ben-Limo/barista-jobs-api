package com.wapanzi.baristajobsapi.domain.address.dto;

public record CreateAddressRequest(
        String city,
        String country,
        String postalCode,
        String street
) {
}
