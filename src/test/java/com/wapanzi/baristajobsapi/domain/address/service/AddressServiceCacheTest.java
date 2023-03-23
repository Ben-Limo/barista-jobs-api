package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class AddressServiceCacheTest {
    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @Test
    void testGetAddressById_forMultipleRequest_returnFromCache() {
        // given
        Long id = 12L;
        Address address = new Address(null, "Nai", "Kenya", "Kaunda St", "1212");
        given(addressRepository.findById(id)).willReturn(Optional.of(address));

        // when
        addressService.getAddressById(id);
        addressService.getAddressById(id);
        addressService.getAddressById(id);

        // then
        then(addressRepository).should(times(1)).findById(id);
    }
}
