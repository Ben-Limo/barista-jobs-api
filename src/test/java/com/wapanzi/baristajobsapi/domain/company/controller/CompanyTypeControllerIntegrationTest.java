package com.wapanzi.baristajobsapi.domain.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapanzi.baristajobsapi.domain.company.dto.CompanyTypeDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyTypeRequest;
import com.wapanzi.baristajobsapi.domain.company.exception.CompanyTypeNotFoundException;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CompanyTypeController.class)
class CompanyTypeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CompanyTypeService service;

    private CompanyTypeDto companyTypeDto;
    private CreateCompanyTypeRequest companyTypeRequest;

    private CompanyType companyType;

    @BeforeEach
    void setup() {
        companyType = new CompanyType(1L, "Brewery", LocalDateTime.now(), LocalDateTime.now());
        companyTypeDto = new CompanyTypeDto(1l,"Brewery");
        companyTypeRequest = new CreateCompanyTypeRequest("Coffee House");
    }
    @Test
    void testGetCompanyType_returnCompanyType() throws Exception {
        // given
        Long id = 1L;
        given(service.findCompanyTypeById(anyLong())).willReturn(companyTypeDto);

        // when
        ResultActions response = mockMvc.perform(get("/company-type/{id}", id));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Brewery"));

    }

    @Test
    void testGetCompanyType_missingCompanyType_returnNotFound() throws Exception {
        // given
        given(service.findCompanyTypeById(anyLong())).willThrow(CompanyTypeNotFoundException.class);

        // when
        ResultActions response = mockMvc.perform(get("/company-type/1"));

        // then
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateNewCompanyType_whenSuccessful_returnCreatedCompanyType() throws Exception{
        // given
        given(service.createNewCompanyType(any(CreateCompanyTypeRequest.class))).willReturn(companyType);

        // when
        ResultActions response = mockMvc.perform(post("/company-type")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(companyTypeRequest)));

        // then
        response
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCompanyType_whenSuccessful_returnUpdatedCompanyType() throws Exception {
        // given
        given(service.updateCompanyType(anyLong(), any(CompanyTypeDto.class))).willReturn(companyType);

        // when
        ResultActions response = mockMvc.perform(
                post("/company-type/{id}", 1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(companyType)));

        // then
        response
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCompanyType_whenMissing_returnNotFound() throws Exception {
        // given
        given(service.updateCompanyType(anyLong(), any(CompanyTypeDto.class))).willThrow(CompanyTypeNotFoundException.class);

        // when
        ResultActions response = mockMvc.perform(
                post("/company-type/{id}", 1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(companyType)));

        // then
        response.andExpect(status().isNotFound());
    }

    @Test
    void testRemoveCompanyType_return200() throws Exception{
        // given
        willDoNothing().given(service).removeCompanyType(anyLong());

        // when
        ResultActions response = mockMvc.perform(
                delete("/company-type/{id}", 1l)
        );

        // then
        response.andExpect(status().isOk());
    }
}