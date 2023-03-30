package com.wapanzi.baristajobsapi.domain.company.repository;

import com.wapanzi.baristajobsapi.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
