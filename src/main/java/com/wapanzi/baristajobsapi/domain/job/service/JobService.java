package com.wapanzi.baristajobsapi.domain.job.service;

import com.wapanzi.baristajobsapi.domain.job.model.Job;
import org.springframework.stereotype.Component;

@Component
public interface JobService {
    Job addNewJob(Job job);
}
