package com.wapanzi.baristajobsapi.domain.address.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.BDDAssertions;
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

    @Test
    void testGetAddressById_returnAddressDetails() {
        // given
        Address savedAddress = addressRepository.save(
                new Address(null, "Nai", "Kenya", "Kaunda St", "1212" ,
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        // when
        Address fetchedAddress = addressServiceImpl.getAddressById(savedAddress.getId());

        // then
        then(fetchedAddress.getId()).isNotNull();
        then(fetchedAddress.getCity()).isEqualTo(savedAddress.getCity());
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
        Address savedAddress = addressRepository.save(
                new Address(10001l, "Nai", "Kenya", "Kaunda St", "1212" ,
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        // when
        Address updatedAddress = addressServiceImpl.updateAddressDetails(10001l,
                new Address(10001l, "Nairobi", "Kenya", "Banda st", "2323",
                        LocalDateTime.now(),
                        LocalDateTime.now())
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
        Address updateAddress = new Address(23l, "Nai", "Kenya", "Kaunda St", "1212",
                LocalDateTime.now(),
                LocalDateTime.now());

        // when
        Throwable throwable = catchThrowable(() -> addressServiceImpl.updateAddressDetails(id, updateAddress));

        // then
        BDDAssertions.then(throwable).isInstanceOf(AddressNotFoundException.class);
    }

    @Test
    void testCreateAddress_returnNewAddress() {
        // given
        Address newAddress = new Address(
                null, "Kanairo",
                "Kenya",
                "Banda st",
                "2323",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        // when
        Address savedAddress = addressServiceImpl.createAddress(newAddress);

        // then
        then(savedAddress.getId()).isNotNull();
        then(savedAddress.getCity()).isEqualTo("Kanairo");
        then(savedAddress.getCountry()).isEqualTo("Kenya");
        then(savedAddress.getPostalCode()).isEqualTo("Banda st");
        then(savedAddress.getStreet()).isEqualTo("2323");
    }


}