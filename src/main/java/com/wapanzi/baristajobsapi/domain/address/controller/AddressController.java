package com.wapanzi.baristajobsapi.domain.address.controller;

import com.wapanzi.baristajobsapi.domain.address.dto.AddressDto;
import com.wapanzi.baristajobsapi.domain.address.dto.CreateAddressRequest;
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
    public AddressDto getAddress(@PathVariable Long id) {
        return addressServiceImpl.getAddressById(id);
    }

    @PostMapping("/addresses/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable Long id,
                                 @Valid @RequestBody AddressDto updateAddressRequest) {

        addressServiceImpl.updateAddressDetails(id, updateAddressRequest);

        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @PostMapping("/addresses")
    public ResponseEntity<String> createAddress(@Valid @RequestBody CreateAddressRequest request) {
        addressServiceImpl.createAddress(request);

        return new ResponseEntity<>("Address created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> removeAddress(@PathVariable Long addressId) {
        addressServiceImpl.removeAddress(addressId);

        return new ResponseEntity<String>("Address deleted successfully", HttpStatus.OK);
    }
}
