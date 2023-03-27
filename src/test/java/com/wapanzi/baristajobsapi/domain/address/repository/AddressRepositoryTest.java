package com.wapanzi.baristajobsapi.domain.address.repository;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;

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
                new Address(null, "Nai", "Kenya", "Kaunda St", "1212", LocalDateTime.now(), LocalDateTime.now())
        );

        // when
        Address address = addressRepository.getAddressByCity("Nai");

        // then
        then(address.getId()).isNotNull();
        then(address.getCity()).isEqualTo(savedAddress.getCity());
    }
}