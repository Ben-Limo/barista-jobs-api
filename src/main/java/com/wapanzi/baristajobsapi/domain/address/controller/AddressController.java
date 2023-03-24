package com.wapanzi.baristajobsapi.domain.address.controller;

import com.wapanzi.baristajobsapi.domain.address.model.Address;
import com.wapanzi.baristajobsapi.domain.address.service.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private final AddressServiceImpl addressServiceImpl;
    @GetMapping("/addresses/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressServiceImpl.getAddressById(id);
    }

    @PostMapping("/addresses/{id}")
    public Address updateAddress(@PathVariable Long id,
                                 @Valid @RequestBody Address address) {
        return addressServiceImpl.updateAddressDetails(id, address);
    }

    @PostMapping("/addresses")
    public Address createAddress(@Valid @RequestBody Address address) {
        return addressServiceImpl.createAddress(address);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> removeAddress(@PathVariable Long addressId) {
        addressServiceImpl.removeAddress(addressId);

        return new ResponseEntity<String>("Address deleted successfully", HttpStatus.OK);
    }
}
