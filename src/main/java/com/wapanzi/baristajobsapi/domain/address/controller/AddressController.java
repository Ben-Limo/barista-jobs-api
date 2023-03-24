package com.wapanzi.baristajobsapi.domain.address.controller;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.service.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private final AddressServiceImpl addressServiceImpl;
    @GetMapping("/addresses/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressServiceImpl.getAddressById(id);
    }
}
