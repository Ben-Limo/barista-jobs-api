package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyRepository;
import com.wapanzi.baristajobsapi.domain.company.repository.CompanyTypeRepository;
import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.model.JobType;
import com.wapanzi.baristajobsapi.domain.job.repository.JobRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class JobServiceImplIntegrationTest {
    @Autowired
    private JobService service;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @Autowired
    private JobRepository jobRepository;

    private static Company company;
    private static List<Company> companies = new ArrayList<>();
    private static CompanyType companyType;
    private static Address address;
    private static List<CompanyType> companyTypes = new ArrayList<>();
    private static Job newJob;

    @BeforeAll
    static void setup() {
        address = new Address(1L, "Nai", "Kenya",
                "Kaunda St", "2323" , LocalDateTime.now(), LocalDateTime.now());
        companyType = new CompanyType(1L, "Barista", LocalDateTime.now(),
                LocalDateTime.now());
        companyTypes.add(companyType);
        company = new Company(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", address, companyTypes,
                LocalDateTime.now(), LocalDateTime.now());
        companies.add(company);

        newJob = Job.builder()
                .title("Barista")
                .description("Make tasteful cups of coffee")
                .jobType(JobType.FULL_TIME)
                .company(company)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testAddNewJob_whenSuccessful_returnJobDetails() {
        // given
        companyTypeRepository.save(companyType);
        companyRepository.save(company);

        // when
        Job savedJob = service.addNewJob(newJob);

        // then
        then(savedJob.getId()).isNotNull();
        then(savedJob.getTitle()).isEqualTo("Barista");
        then(savedJob.getJobType()).isEqualTo(JobType.FULL_TIME);
    }

    @Test
    void testUpdateCompany_whenSuccessful_returnJobDetails() {
       // given
        Job updateJob = Job.builder()
                .title("Roaster")
                .description("Roast green coffee beans")
                .jobType(JobType.PART_TIME)
                .company(company)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        companyTypeRepository.save(companyType);
        companyRepository.save(company);
        Job savedJob = jobRepository.save(newJob);

       // when
        Job updatedJob = service.updateCompany(1L, updateJob);

       // then
        then(updatedJob.getId()).isEqualTo(1L);
        then(updatedJob.getTitle()).isEqualTo("Roaster");
        then(updatedJob.getJobType()).isEqualTo(JobType.PART_TIME);
    }

    @Test
    void testGetJob_whenSuccessful_returnJobDetails() {
        // given
        companyTypeRepository.save(companyType);
        companyRepository.save(company);
        jobRepository.save(newJob);

        // when
        Job foundJob = service.getJobById(1L);

        // then
        then(foundJob.getId()).isNotNull();
        then(foundJob.getId()).isEqualTo(1L);
        then(foundJob.getTitle()).isEqualTo("Barista");
        then(foundJob.getJobType()).isEqualTo(JobType.FULL_TIME);
    }

    @Test
    void testGetAllJobs_whenSuccessful_returnListOfJobs() {
        // given
        companyTypeRepository.save(companyType);
        companyRepository.save(company);
        jobRepository.save(newJob);

        // when
        List<Job> jobs = service.getAllJobs();

        // then
        then(jobs.get(0).getId()).isNotNull();
        then(jobs.get(0).getTitle()).isEqualTo("Barista");
    }

}