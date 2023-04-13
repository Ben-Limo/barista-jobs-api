package com.wapanzi.baristajobsapi.domain.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.company.dto.CompanyDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CompanyTypeDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyRequest;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyService;
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
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private CompanyDto companyDto;
    private Company company;
    private List<CompanyDto> companiesDto = new ArrayList<>();
    private List<Company> companyList = new ArrayList<>();
    private CompanyTypeDto companyTypeDto;
    private CompanyType companyType;
    private AddressDto addressDto;
    private Address address;
    private List<CompanyTypeDto> companyTypes;
    @BeforeEach
    void setup() {
        addressDto = new AddressDto(1L, "Nai", "Kenya",
                "Kaunda St", "2323");
        companyTypeDto = new CompanyTypeDto(1L, "Barista");

        companyTypes = new ArrayList<>();
        companyTypes.add(companyTypeDto);
        companyDto = new CompanyDto(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", addressDto, companyTypes);
        companyType = new CompanyType(1L, "Barista", LocalDateTime.now(), LocalDateTime.now());
        address = new Address(1L, "Nai", "Kenya",
                "Kaunda St", "2323", LocalDateTime.now(), LocalDateTime.now());
        List<CompanyType> companyTypeList = new ArrayList<>();
        companyTypeList.add(companyType);
        company = new Company(1L, "Brewery", "admin@company.com",
                "we are customer obsessed", address, companyTypeList, LocalDateTime.now(), LocalDateTime.now());

        companiesDto.add(companyDto);
        address = new Address(
                1L,
                "Nai", "Kenya",
                "Kaunda St", "2323",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        company = new Company(
                1L,
                "Brewery", "admin@company.com",
                "we are customer obsessed",
                address,
                List.of(companyType),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        companyList.add(company);
    }
    @Test
    void testGetCompany_whenSuccessful_returnCompanyDetails() throws Exception {
        // given
       given(companyService.getCompany(anyLong())).willReturn(companyDto);

        // when
        ResultActions actions = mockMvc.perform(get("/companies/{id}", 1L));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Brewery"));
    }

    @Test
    void testAddNewCompany_whenSuccessful_return200() throws Exception {
        // given
        List<CreateCompanyRequest> createCompaniesList = new ArrayList<>();
        CreateAddressRequest createAddressRequest = new CreateAddressRequest(
                "Nai", "Kenya",
                "Kaunda St", "2323"
        );
        CreateCompanyRequest companyRequest = new CreateCompanyRequest(
                "Brewery", "admin@company.com",
                "we are customer obsessed",
                createAddressRequest,
                companyTypes
        );

        createCompaniesList.add(companyRequest);
        given(companyService.addListOfNewCompanies(anyList())).willReturn(companyList);

        // when
        ResultActions response = mockMvc.perform(post("/companies")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createCompaniesList)));

        // then
        response
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCompany_whenSuccessful_return200() throws Exception{
        // given
        given(companyService.updateCompany(anyLong(), any(CompanyDto.class))).willReturn(company);

        // when
        ResultActions response = mockMvc.perform(put("/companies/{id}", 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(companyDto)));

        // then
        response
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveCompany_whenSuccessful_return200() throws Exception{
        // given
        willDoNothing().given(companyService).removeCompany(anyLong());

        // when
        ResultActions response =
                mockMvc.perform(delete("/companies/{id}", 1L));

        // then
        response
                .andExpect(status().isOk());
    }
}