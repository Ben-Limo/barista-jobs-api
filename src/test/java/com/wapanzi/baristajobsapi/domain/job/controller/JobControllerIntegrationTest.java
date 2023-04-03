package com.wapanzi.baristajobsapi.domain.job.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.model.JobType;
import com.wapanzi.baristajobsapi.domain.job.service.JobService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JobController.class)
class JobControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService service;

    @Autowired
    private ObjectMapper objectMapper;

    private static Company company;
    private static CompanyType companyType;
    private static Address address;
    private static List<CompanyType> companyTypes = new ArrayList<>();
    private static Job newJob;

    @BeforeAll
    static void setup() {
        address = new Address(1L, "Nai", "Kenya",
                "Kaunda St", "2323", LocalDateTime.now(), LocalDateTime.now());
        companyType = new CompanyType(1L, "Barista", LocalDateTime.now(),
                LocalDateTime.now());
        companyTypes.add(companyType);
        company = new Company(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", address, companyTypes,
                LocalDateTime.now(), LocalDateTime.now());
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
    void testGetAllJobs_whenSuccessful_return200() throws Exception {
        // given
        List<Job> jobs = new ArrayList<>();
        jobs.add(newJob);
        given(service.getAllJobs()).willReturn(jobs);

        // when
        ResultActions response = mockMvc.perform(get("/jobs")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].title").value("Barista"));
    }

    @Test
    void testGetJob_whenSuccessful_returnJobDetails() throws Exception{
        // given
        given(service.getJobById(anyLong())).willReturn(newJob);

        // when
        ResultActions response = mockMvc.perform(get("/jobs/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response
                .andExpect(status().isFound())
                .andExpect(jsonPath("title").value("Barista"))
                .andExpect(jsonPath("description").value("Make tasteful cups of coffee"));
    }

    @Test
    void testAddNewJob_whenSuccessful_returnJobDetails() throws  Exception{
        // given
        given(service.addNewJob(any(Job.class))).willReturn(newJob);

        // when
        ResultActions response = mockMvc.perform(post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newJob)));

        // then
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("title").value("Barista"))
                .andExpect(jsonPath("description").value("Make tasteful cups of coffee"));
    }

    @Test
    void testUpdateJob_whenSuccessful_returnUpdatedJobDetails() throws Exception{
        // given
        given(service.updateJob(anyLong(), any(Job.class))).willReturn(newJob);
        // when
        ResultActions response = mockMvc.perform(put("/jobs/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newJob)));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Barista"))
                .andExpect(jsonPath("description").value("Make tasteful cups of coffee"));
    }

}