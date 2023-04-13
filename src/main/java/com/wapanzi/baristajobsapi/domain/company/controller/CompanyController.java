package com.wapanzi.baristajobsapi.domain.company.controller;

import com.wapanzi.baristajobsapi.domain.company.dto.CompanyDto;
import com.wapanzi.baristajobsapi.domain.company.dto.CreateCompanyRequest;
import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    @Autowired
    private final CompanyService service;

    @PostMapping("/companies")
    public ResponseEntity<String> addListOfNewCompanies(
            @Valid @RequestBody List<CreateCompanyRequest> request) {
        service.addListOfNewCompanies(request);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                           @Valid @RequestBody CompanyDto companyDto) {
        service.updateCompany(id, companyDto);

        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/companies/{id}")
    public CompanyDto getCompany(@PathVariable Long id) {
        return service.getCompany(id);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> removeCompany(@PathVariable Long id) {
        service.removeCompany(id);

        return new ResponseEntity<>("Company deleted successfully ", HttpStatus.OK);
    }
}
