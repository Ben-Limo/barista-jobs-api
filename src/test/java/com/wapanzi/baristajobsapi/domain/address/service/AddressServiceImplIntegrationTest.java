package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = NONE)
@Transactional
public class AddressServiceImplIntegrationTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    private Address address;
    @BeforeEach
    public void setup() {
        address = new Address(
                10001l,
                "Nai",
                "Kenya",
                "Kaunda St",
                "2323",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }

    @Test
    void testGetAddressById_returnAddressDetails() {
        // given
        Address savedAddress = addressRepository.save(address);

        // when
        AddressDto fetchedAddress = addressServiceImpl.getAddressById(savedAddress.getId());

        // then
        then(fetchedAddress.id()).isNotNull();
        then(fetchedAddress.city()).isEqualTo(savedAddress.getCity());
    }

    @Test
    void testGetAddressById_returnNotFoundException() {
        // given
        Long id = 23l;

        // when
        Throwable throwable = catchThrowable(() -> addressServiceImpl.getAddressById(id));

        // then
        BDDAssertions.then(throwable).isInstanceOf(AddressNotFoundException.class);
    }

    @Test
    void testUpdateAddressDetails_returnUpdatedAddress() {
        //given
        Address savedAddress = addressRepository.save(address);

        // when
        Address updatedAddress = addressServiceImpl.updateAddressDetails(10001l,
                new AddressDto(
                        10001l,
                        "Nairobi",
                        "Kenya",
                        "Banda st",
                        "2323")
        );

        // then
        then(updatedAddress.getId()).isNotNull();
        then(updatedAddress.getId()).isEqualTo(savedAddress.getId());
        then(updatedAddress.getCity()).isEqualTo("Nairobi");
        then(updatedAddress.getCountry()).isEqualTo("Kenya");
        then(updatedAddress.getPostalCode()).isEqualTo("Banda st");
        then(updatedAddress.getStreet()).isEqualTo("2323");
    }

    @Test
    void testUpdateAddressDetails_returnNotFoundException() {
        // given
        Long id = 23l;
        AddressDto addressDto = new AddressDto(
                10001l,
                "Nai",
                "Kenya",
                "Kaunda St",
                "2323" );

        // when
        Throwable throwable = catchThrowable(() -> addressServiceImpl.updateAddressDetails(id, addressDto));

        // then
        BDDAssertions.then(throwable).isInstanceOf(AddressNotFoundException.class);
    }

    @Test
    void testCreateAddress_returnNewAddress() {
        // given
        CreateAddressRequest createAddressRequest = new CreateAddressRequest(
                "Nai",
                "Kenya",
                "Kaunda St",
                "2323" );
        // when
        Address savedAddress = addressServiceImpl.createAddress(createAddressRequest);

        // then
        then(savedAddress.getId()).isNotNull();
        then(savedAddress.getCity()).isEqualTo("Nai");
        then(savedAddress.getCountry()).isEqualTo("Kenya");
        then(savedAddress.getPostalCode()).isEqualTo("Kaunda St");
        then(savedAddress.getStreet()).isEqualTo("2323");
    }


}