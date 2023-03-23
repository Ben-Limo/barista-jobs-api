package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AddressServiceIntegrationTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    void testGetAddressById_returnAddressDetails() {
        // given
        Address savedAddress = addressRepository.save(
                new Address(null, "Nai", "Kenya", "Kaunda St", "1212")
        );

        // when
        Address fetchedAddress = addressService.getAddressById(savedAddress.getId());

        // then
        then(fetchedAddress.getId()).isNotNull();
        then(fetchedAddress.getCity()).isEqualTo(savedAddress.getCity());
    }
}