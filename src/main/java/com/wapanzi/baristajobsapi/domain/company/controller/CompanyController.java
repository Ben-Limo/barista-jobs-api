package com.wapanzi.baristajobsapi.domain.company.controller;

import com.wapanzi.baristajobsapi.domain.company.model.Company;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    @Autowired
    private final CompanyService service;

    @PostMapping("/companies")
    public ResponseEntity<?> addNewCompany(@Valid @RequestBody List<Company> companies) {
        List<Company> savedCompany = service.addNewCompany(companies);

        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id,
                                           @Valid @RequestBody Company company) {
        Company savedCompany = service.updateCompany(id, company);

        return new ResponseEntity<>(savedCompany, HttpStatus.OK);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id) {
        Company savedCompany = service.getCompany(id);

        return new ResponseEntity<>(savedCompany, HttpStatus.FOUND);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> removeCompany(@PathVariable Long id) {
        service.removeCompany(id);

        return new ResponseEntity<>("Company deleted successfully ", HttpStatus.OK);
    }
}
