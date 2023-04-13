package com.wapanzi.baristajobsapi.domain.job.dto;

import com.wapanzi.baristajobsapi.domain.job.model.JobType;

public record JobDto(
        Long id,
        String title,
        String description,
        JobType jobType
) {
}
