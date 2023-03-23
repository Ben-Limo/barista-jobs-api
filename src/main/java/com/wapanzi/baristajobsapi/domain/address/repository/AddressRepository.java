package com.wapanzi.baristajobsapi.domain.address.repository;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
