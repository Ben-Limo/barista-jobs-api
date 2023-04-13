package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.AddressDtoMapper;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressDtoMapper addressDtoMapper;

    @Cacheable("addresses")
    public AddressDto getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressDtoMapper)
                .orElseThrow(
                () -> new AddressNotFoundException()
        );
    }

    @Override
    public Address updateAddressDetails(long id, AddressDto updateAddressRequest) {
        Address savedAddress = addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException()
        );
        savedAddress.setCity(updateAddressRequest.city());
        savedAddress.setCountry(updateAddressRequest.country());
        savedAddress.setStreet(updateAddressRequest.street());
        savedAddress.setPostalCode(updateAddressRequest.postalCode());
        savedAddress.setUpdatedAt(LocalDateTime.now());

        return addressRepository.save(savedAddress);
    }

    @Override
    public Address createAddress(CreateAddressRequest request) {
        Address address = new Address(
                null,
                request.city(),
                request.country(),
                request.postalCode(),
                request.street(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return addressRepository.save(address);
    }

    @Override
    public void removeAddress(long id) {
        addressRepository.deleteById(id);
    }
}
