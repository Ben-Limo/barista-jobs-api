package com.wapanzi.baristajobsapi.domain.address.repository;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void testGetAddressByCity_returnAddressDetails() {
        // given
        Address savedAddress = testEntityManager.persistFlushFind(
                new Address(null, "Nai", "Kenya", "Kaunda St", "1212")
        );

        // when
        Address address = addressRepository.getAddressByCity("Nai");

        // then
        then(address.getId()).isNotNull();
        then(address.getCity()).isEqualTo(savedAddress.getCity());
    }
}