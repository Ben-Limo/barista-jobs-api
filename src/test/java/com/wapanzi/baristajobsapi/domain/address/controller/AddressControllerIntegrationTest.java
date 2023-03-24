package com.wapanzi.baristajobsapi.domain.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.service.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@WebMvcTest
class AddressControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressServiceImpl addressServiceImpl;
    @Test
    void testGetAddress_returnSavedAddress() throws Exception{
        // given
        given(addressServiceImpl.getAddressById(anyLong())).willReturn(
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

        given(addressServiceImpl.updateAddressDetails(anyLong(), any(Address.class))).willReturn(
                new Address(1l, "Nai", "Kenya", "2442", "Koinange st")
        );

        // when // then
        mockMvc.perform(post("/addresses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON))
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

        given(addressServiceImpl.createAddress(any(Address.class))).willReturn(
                new Address(1l, "Nai", "Kenya", "2442", "Koinange st")
        );

        // when // then
        mockMvc.perform(post("/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}