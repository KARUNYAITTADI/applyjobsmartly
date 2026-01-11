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

    @Override
    public List<Job> fetchJobs(String careerPageUrl) {

        // example: extract company name from URL
        String apiUrl = careerPageUrl + "/jobs";

        // call API (simplified)
        Object response = restTemplate.getForObject(apiUrl, Object.class);

        // map response → Job entity
        return new ArrayList<>();
    }
}
