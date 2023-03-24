package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class AddressServiceUnitTest {
    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;
    @Test
    void testRemoveAddress_happyPath() {
        // given
        Address newAddress = new Address(1l, "Kanairo", "Kenya", "Banda st", "2323");
        addressRepository.save(newAddress);

        // when
        addressService.removeAddress(newAddress.getId());

        // then
        BDDMockito.then(addressRepository).should(times(1)).deleteById(newAddress.getId());
    }

    @Test
    void testCreateAddress_returnSavedAddress() {
        // given
        Address newAddress = new Address(1l, "Kanairo", "Kenya", "Banda st", "2323");
        given(addressRepository.save(any(Address.class))).willReturn(newAddress);

        // when
        Address savedAddress = addressService.createAddress(newAddress);

        // then
        then(savedAddress.getId()).isNotNull();
        then(savedAddress.getCity()).isEqualTo(newAddress.getCity());
        then(savedAddress.getCountry()).isEqualTo(newAddress.getCountry());
    }

}
