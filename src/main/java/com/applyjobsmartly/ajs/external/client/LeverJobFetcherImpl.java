package com.applyjobsmartly.ajs.external.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.applyjobsmartly.ajs.dto.response.LeverJobDto;
import com.applyjobsmartly.ajs.entity.Job;
import com.applyjobsmartly.ajs.external.service.ExternalJobFetcher;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LeverJobFetcherImpl implements ExternalJobFetcher {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Job> fetchJobs(String careerPageUrl) {

        log.info("begin LeverJobFetcherImpl --> fetchJobs");

        List<Job> jobs = new ArrayList<>();

        try {
            // careerPageUrl example:
            // https://jobs.lever.co/spotify
            String companySlug =
                    careerPageUrl.substring(careerPageUrl.lastIndexOf("/") + 1);

            String apiUrl =
                    "https://api.lever.co/v0/postings/" + companySlug;

            LeverJobDto[] response =
                    restTemplate.getForObject(apiUrl, LeverJobDto[].class);

            if (response != null) {
                for (LeverJobDto j : response) {
                    Job job = Job.builder()
                            .title(j.getText())
                            .location(j.getCategories().getLocation())
                            .applyUrl(j.getHostedUrl())
//                            .external(true)
                            .build();
                    jobs.add(job);
                }
            }

        } catch (Exception e) {
            log.error("Error fetching Lever jobs", e);
        }

        log.info("end LeverJobFetcherImpl --> fetchJobs");
        return jobs;
    }
}
