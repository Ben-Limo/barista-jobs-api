package com.wapanzi.baristajobsapi.domain.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyService;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CompanyController.class)
class CompanyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    private Company company;
    private List<Company> companies = new ArrayList<>();
    private CompanyType companyType;
    private Address address;
    private List<CompanyType> companyTypes;
    @BeforeEach
    void setup() {
        address = new Address(1L, "Nai", "Kenya",
                "Kaunda St", "2323" , LocalDateTime.now(), LocalDateTime.now());
        companyType = new CompanyType(1L, "Barista", LocalDateTime.now(),
                LocalDateTime.now());
        companyTypes = new ArrayList<>();
        companyTypes.add(companyType);
        company = new Company(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", address, companyTypes,
                LocalDateTime.now(), LocalDateTime.now());
        companies.add(company);
    }
    @Test
    void testGetCompany_whenSuccessful_returnCompanyDetails() throws Exception {
        // given
        given(companyService.getCompany(anyLong())).willReturn(company);

        // when
        ResultActions actions = mockMvc.perform(get("/companies/{id}", 1L));

        // then
        actions
                .andExpect(status().isFound())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Brewery"));
    }

    @Test
    void testAddNewCompany_whenSuccessful_returnCompanyDetails() throws Exception {
        // given
        given(companyService.addNewCompany(anyList())).willReturn(companies);

        // when
        ResultActions response = mockMvc.perform(post("/companies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(companies)));

        // then
        response
                .andExpect(status().isCreated());
    }
}