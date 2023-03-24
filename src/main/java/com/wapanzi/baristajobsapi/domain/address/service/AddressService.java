package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Cacheable("addresses")
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address updateAddressDetails(long id, Address address) {
        Address savedAddress = addressRepository.findById(id).get();
        savedAddress.setCity(address.getCity());
        savedAddress.setCountry(address.getCountry());
        savedAddress.setStreet(address.getStreet());
        savedAddress.setPostalCode(address.getPostalCode());

        return addressRepository.save(savedAddress);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public void removeAddress(long id) {
        addressRepository.deleteById(id);
    }
}
