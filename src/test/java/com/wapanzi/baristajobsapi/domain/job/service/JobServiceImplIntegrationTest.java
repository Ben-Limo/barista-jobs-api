package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyRepository;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.model.JobType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = NONE)
class JobServiceImplIntegrationTest {
    @Autowired
    private JobService service;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    private Company company;
    private List<Company> companies = new ArrayList<>();
    private CompanyType companyType;
    private Address address;
    private List<CompanyType> companyTypes = new ArrayList<>();
    @BeforeEach
    void setup() {
        address = new Address(1L, "Nai", "Kenya",
                "Kaunda St", "2323" , LocalDateTime.now(), LocalDateTime.now());
        companyType = new CompanyType(1L, "Barista", LocalDateTime.now(),
                LocalDateTime.now());
        companyTypes.add(companyType);
        company = new Company(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", address, companyTypes,
                LocalDateTime.now(), LocalDateTime.now());
        companies.add(company);
    }
    @Test
    void testAddNewJob_whenSuccessful_returnJobDetails() {
        // given
        companyTypeRepository.save(companyType);
        companyRepository.save(company);
        Job newJob = Job.builder()
                .title("Barista")
                .description("Make tasteful cups of coffee")
                .jobType(JobType.FULL_TIME)
                .company(company)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // when
        Job savedJob = service.addNewJob(newJob);

        // then
        then(savedJob.getId()).isNotNull();
        then(savedJob.getTitle()).isEqualTo("Barista");
        then(savedJob.getJobType()).isEqualTo(JobType.FULL_TIME);

    }
}