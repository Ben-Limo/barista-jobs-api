package com.wapanzi.baristajobsapi.domain.job.repository;

import com.wapanzi.baristajobsapi.domain.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
