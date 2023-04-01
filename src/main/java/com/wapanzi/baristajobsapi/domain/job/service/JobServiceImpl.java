package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository repository;
    @Override
    public Job addNewJob(Job job) {
        return repository.save(job);
    }
}
