package com.wapanzi.baristajobsapi.domain.address.controller;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@WebMvcTest
class AddressControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;
    @Test
    void testGetAddress_returnSavedAddress() throws Exception{
        // given
        given(addressService.getAddressById(anyLong())).willReturn(
                new Address(1L, "Nairobi", "Kenya", "1212", "Kaunda st")

        );

        //when // then
        mockMvc.perform(get("/addresses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("city").value("Nairobi"))
                .andExpect(jsonPath("country").value("Kenya"))
                .andExpect(jsonPath("postalCode").value("1212"))
                .andExpect(jsonPath("street").value("Kaunda st"));
    }
}