package com.wapanzi.baristajobsapi.domain.job.controller;

import com.wapanzi.baristajobsapi.domain.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJobs() {
        return null;
    }
}
