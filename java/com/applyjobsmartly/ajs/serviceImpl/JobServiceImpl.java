package com.applyjobsmartly.ajs.serviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.applyjobsmartly.ajs.dto.request.JobFilterRequest;
import com.applyjobsmartly.ajs.dto.response.JobResponse;
import com.applyjobsmartly.ajs.entity.Job;
import com.applyjobsmartly.ajs.entity.User;
import com.applyjobsmartly.ajs.repository.JobRepository;
import com.applyjobsmartly.ajs.repository.UserRepository;
import com.applyjobsmartly.ajs.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public Page<JobResponse> getAllJobs(JobFilterRequest request, String userEmail) {

        log.info("begin JobServiceImpl --> getAllJobs");

        try {
            // Auto filter from profile
            if (userEmail != null) {
                User user = userRepository.findByEmail(userEmail).orElse(null);
                if (user != null) {
                    request.setJobRole(
                            request.getJobRole() != null ?
                                    request.getJobRole() :
                                    user.getRole()
                    );
                }
            }

            Pageable pageable = PageRequest.of(
                    request.getPageNumber() - 1,
                    request.getPageSize(),
                    Sort.by("createdOn").descending()
            );

            Specification<Job> spec = Specification
                    .where(JobSpecification.hasJobRole(request.getJobRole()))
                    .and(JobSpecification.hasLocation(request.getLocation()))
                    .and(JobSpecification.isVerified(request.getVerifiedOnly()))
                    .and(JobSpecification.isExternal(
                            request.getIncludeExternalJobs()));

            Page<Job> jobs = jobRepository.findAll(spec, pageable);

            log.info("end JobServiceImpl --> getAllJobs");

            return jobs.map(this::mapToResponse);

        } catch (Exception e) {
            log.error("Job fetch failed", e);
            throw e;
        }
    }

    private JobResponse mapToResponse(Job job) {
        JobResponse response = new JobResponse();
        response.setTitle(job.getTitle());
        response.setCompanyName(job.getCompanyName());
        response.setLocation(job.getLocation());
        response.setExperience(job.getExperience());
        response.setJobType(job.getJobType());
        response.setVerified(job.isVerified());
        response.setApplyUrl(job.getApplyUrl());
        response.setSource(job.getSource());
        return response;
    }
}
