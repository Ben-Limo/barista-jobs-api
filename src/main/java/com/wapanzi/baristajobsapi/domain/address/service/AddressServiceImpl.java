package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Cacheable("addresses")
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException()
        );
    }

    @Override
    public Address updateAddressDetails(long id, Address address) {
        Address savedAddress = addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException()
        );
        savedAddress.setCity(address.getCity());
        savedAddress.setCountry(address.getCountry());
        savedAddress.setStreet(address.getStreet());
        savedAddress.setPostalCode(address.getPostalCode());
        savedAddress.setUpdatedAt(address.getUpdatedAt());

        return addressRepository.save(savedAddress);
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void removeAddress(long id) {
        addressRepository.deleteById(id);
    }
}
