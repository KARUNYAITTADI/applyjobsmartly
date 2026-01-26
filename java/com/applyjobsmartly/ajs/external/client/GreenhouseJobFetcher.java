package com.applyjobsmartly.ajs.external.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.applyjobsmartly.ajs.entity.Job;
import com.applyjobsmartly.ajs.external.service.ExternalJobFetcher;

@Component
public class GreenhouseJobFetcher implements ExternalJobFetcher {

    private final RestTemplate restTemplate = new RestTemplate();
    private final JobRepository jobRepository;

    @Override
    public List<Job> fetchJobs(String careerPageUrl) {

        // example: extract company name from URL
        String apiUrl = careerPageUrl + "/jobs";

        // call API (simplified)
        Object response = restTemplate.getForObject(apiUrl, Object.class);

        Job job = jobRepository
        	    .findByExternalJobIdAndExternalSource(extId, source)
        	    .orElse(new Job());

        	job.setTitle(title);
        	job.setCompanyName(company);
        	job.setApplyUrl(applyUrl);
        	job.setSource("EXTERNAL");
        	job.setExternalSource("GREENHOUSE");
        	job.setExternalJobId(extId);
        	job.setVerified(true);

        	jobRepository.save(job);

        // map response → Job entity
        return new ArrayList<>();
    }
}
