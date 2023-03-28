package com.wapanzi.baristajobsapi.domain.company.controller;

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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CompanyTypeController.class)
class CompanyTypeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyTypeService service;

    private CompanyType companyType;

    @BeforeEach
    void setup() {
        companyType = new CompanyType(1L, "Brewery", LocalDateTime.now(), LocalDateTime.now());
    }
    @Test
    void testGetCompanyType_returnCompanyType() throws Exception {
        // given
        Long id = 1L;
        given(service.findCompanyTypeById(anyLong())).willReturn(companyType);

        // when
        ResultActions response = mockMvc.perform(get("/company-type/{id}", id));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("companyType").value("Brewery"));

    }
}