package com.wapanzi.baristajobsapi.domain.job.model;

import com.wapanzi.baristajobsapi.domain.company.model.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Job {

    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 3, message = "Job title should have at least 3 characters")
    private String title;

    @Size(min = 10, message = "Job title should have at least 10 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @ManyToOne
    private Company company;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
