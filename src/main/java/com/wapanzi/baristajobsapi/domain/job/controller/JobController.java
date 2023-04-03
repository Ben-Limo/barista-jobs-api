package com.wapanzi.baristajobsapi.domain.job.controller;

import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJobs() {
        List<Job> jobs = service.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
}
