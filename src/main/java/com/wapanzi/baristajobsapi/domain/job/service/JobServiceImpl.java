package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.job.exception.JobNotFoundException;
import com.wapanzi.baristajobsapi.domain.job.model.Job;
import com.wapanzi.baristajobsapi.domain.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository repository;
    @Override
    public Job addNewJob(Job job) {
        return repository.save(job);
    }

    @Override
    public Job updateCompany(Long id, Job updateJob) {
        Job savedCompany = repository.findById(id).orElseThrow(
                () -> new JobNotFoundException());

        savedCompany.setJobType(updateJob.getJobType());
        savedCompany.setTitle(updateJob.getTitle());
        savedCompany.setDescription(updateJob.getDescription());
        savedCompany.setUpdatedAt(LocalDateTime.now());

        return repository.save(savedCompany);
    }

    @Override
    public Job getJobById(long id) {
        Job foundJob = repository.findById(id).orElseThrow(
                () -> new JobNotFoundException());

        return foundJob;
    }

    @Override
    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    @Override
    public void removeJob(long id) {
        repository.deleteById(id);
    }
}
