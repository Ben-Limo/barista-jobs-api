package com.wapanzi.baristajobsapi.domain.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.service.AddressNotFoundException;
import com.wapanzi.baristajobsapi.domain.address.service.AddressServiceImpl;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressServiceImpl addressServiceImpl;
    @Test
    void testGetAddress_returnSavedAddress() throws Exception{
        // given
        given(addressServiceImpl.getAddressById(anyLong())).willReturn(
                new AddressDto(1L, "Nairobi", "Kenya", "1212", "Kaunda st"));

        //when // then
        mockMvc.perform(get("/addresses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("city").value("Nairobi"))
                .andExpect(jsonPath("country").value("Kenya"))
                .andExpect(jsonPath("postalCode").value("1212"))
                .andExpect(jsonPath("street").value("Kaunda st"));
    }

    @Test
    void testGetAddress_forMissingAddress_returnStatus404() throws Exception{
        // given
        given(addressServiceImpl.getAddressById(anyLong())).willThrow(AddressNotFoundException.class);

        // when
        ResultActions response = mockMvc.perform(get("/addresses/1"));

        // then
        response.andExpect(status().isNotFound());
    }
    @Test
    void testUpdateAddress_returnUpdatedAddress() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // given
        Map<String, Object> body = new HashMap<>();
        body.put("id", 1l);
        body.put("city", "Nai");
        body.put("country", "Kenya");
        body.put("postalCode", "2442");
        body.put("street", "Koinange st");

        given(addressServiceImpl.updateAddressDetails(anyLong(), any(AddressDto.class))).willReturn(
                new Address(
                        1l,
                        "Nai",
                        "Kenya",
                        "2442",
                        "Koinange st" ,
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        // when
        ResultActions response = mockMvc.perform(post("/addresses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON));

        // then
        response
                .andExpect(status().isOk());
    }

    @Test
    void testCreateAddress_returnNewAddress() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        // given
        Map<String, Object> body = new HashMap<>();
        body.put("id", 1l);
        body.put("city", "Nai");
        body.put("country", "Kenya");
        body.put("postalCode", "2442");
        body.put("street", "Koinange st");

        given(addressServiceImpl.createAddress(any(CreateAddressRequest.class))).willReturn(
                new Address(1l, "Nai", "Kenya", "2442", "Koinange st" ,
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        // when
        ResultActions response = mockMvc.perform(post("/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON));
        // then
        response.andExpect(status().isCreated());
    }

    @Test
    void testRemoveAddress_return200() throws Exception{
        // given
        Long addressId = 12l;
        willDoNothing().given(addressServiceImpl).removeAddress(anyLong());

        // when
        ResultActions response = mockMvc.perform(delete("/addresses/{addressId}", addressId));

        // then
        response.andExpect(status().isOk());
    }
}