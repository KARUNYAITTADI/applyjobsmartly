package com.applyjobsmartly.ajs.service;

import org.springframework.data.domain.Page;

import com.applyjobsmartly.ajs.dto.request.JobFilterRequest;
import com.applyjobsmartly.ajs.dto.response.JobResponse;

public interface JobService {
	Page<JobResponse> getAllJobs(JobFilterRequest request, String userEmail);
}
