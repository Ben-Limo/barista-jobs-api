package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.job.model.Job;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobService {
    Job addNewJob(Job job);

    Job updateJob(Long id, Job updateJob);
    Job getJobById(long id);
    List<Job>  getAllJobs();
    void removeJob(long id);
}
