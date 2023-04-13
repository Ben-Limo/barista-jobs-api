package com.wapanzi.baristajobsapi.domain.company.controller;

import com.wapanzi.baristajobsapi.domain.company.dto.CompanyTypeDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyTypeRequest;
import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyTypeController {
    @Autowired
    private final CompanyTypeService service;
    @GetMapping("/company-type/{id}")
    public CompanyTypeDto getCompanyType(@PathVariable("id") Long id) {
        return service.findCompanyTypeById(id);
    }

    @PostMapping("/company-type")
    public ResponseEntity<String> createNewCompanyType(
            @Valid @RequestBody CreateCompanyTypeRequest request) {
        service.createNewCompanyType(request);
        return new ResponseEntity<>("Company Type created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/company-type/{id}")
    public ResponseEntity<String> updateCompanyType(@PathVariable("id") Long id,
                                            @Valid @RequestBody CompanyTypeDto companyTypeDto) {
        service.updateCompanyType(id, companyTypeDto);
        return new ResponseEntity<>("Company Type created successfully", HttpStatus.OK);
    }

    @DeleteMapping("/company-type/{id}")
    public ResponseEntity<String> removeCompanyType(@PathVariable("id") Long id) {
        service.removeCompanyType(id);

        return new ResponseEntity<String>("Company type deleted successfully", HttpStatus.OK);
    }
}
