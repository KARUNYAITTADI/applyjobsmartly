package com.applyjobsmartly.ajs.external.service;

import java.util.List;

import com.applyjobsmartly.ajs.entity.Job;

public interface ExternalJobFetcher {
    List<Job> fetchJobs(String careerPageUrl);
}
