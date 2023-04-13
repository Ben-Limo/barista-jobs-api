package com.wapanzi.baristajobsapi.domain.address.dto;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddressDtoMapper implements Function<Address, AddressDto> {
    @Override
    public AddressDto apply(Address address) {
        return new AddressDto(
                address.getId(),
                address.getCity(),
                address.getCountry(),
                address.getPostalCode(),
                address.getStreet());
    }
}
