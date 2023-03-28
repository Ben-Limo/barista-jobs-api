package com.wapanzi.baristajobsapi.domain.company.controller;

import com.wapanzi.baristajobsapi.domain.company.model.CompanyType;
import com.wapanzi.baristajobsapi.domain.company.service.CompanyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyTypeController {
    @Autowired
    private final CompanyTypeService service;
    @GetMapping("/company-type/{id}")
    public CompanyType getCompanyType(@PathVariable("id") Long id) {
        return service.findCompanyTypeById(id);
    }
}
