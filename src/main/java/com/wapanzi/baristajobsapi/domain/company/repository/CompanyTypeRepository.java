package com.wapanzi.baristajobsapi.domain.company.repository;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
}
