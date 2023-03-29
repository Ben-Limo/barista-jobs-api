package com.wapanzi.baristajobsapi.domain.company.controller;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CompanyTypeController {
    @Autowired
    private final CompanyTypeService service;
    @GetMapping("/company-type/{id}")
    public CompanyType getCompanyType(@PathVariable("id") Long id) {
        return service.findCompanyTypeById(id);
    }

    @PostMapping("/company-type")
    public CompanyType createNewCompanyType(@Valid @RequestBody CompanyType companyType) {
        return service.createNewCompanyType(companyType);
    }

    @PostMapping("/company-type/{id}")
    public CompanyType createNewCompanyType(@PathVariable("id") Long id,
                                            @Valid @RequestBody CompanyType companyType) {
        return service.updateCompanyType(id, companyType);
    }
}
