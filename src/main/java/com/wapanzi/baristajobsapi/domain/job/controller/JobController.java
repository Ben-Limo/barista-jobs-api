package com.wapanzi.baristajobsapi.domain.job.controller;

import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJobs(@PathVariable Long id) {
        Job job = service.getJobById(id);
        return new ResponseEntity<>(job, HttpStatus.FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> getAllJobs(@Valid @RequestBody Job job) {
        Job newJob = service.addNewJob(job);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }
}
