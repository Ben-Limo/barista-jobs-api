package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = NONE)
@Transactional
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

    @Test
    void testCreateAddress_returnNewAddress() {
        // given
        Address newAddress = new Address(null, "Kanairo", "Kenya", "Banda st", "2323");

        // when
        Address savedAddress = addressService.createAddress(newAddress);

        // then
        then(savedAddress.getId()).isNotNull();
        then(savedAddress.getCity()).isEqualTo("Kanairo");
        then(savedAddress.getCountry()).isEqualTo("Kenya");
        then(savedAddress.getPostalCode()).isEqualTo("Banda st");
        then(savedAddress.getStreet()).isEqualTo("2323");
    }


}