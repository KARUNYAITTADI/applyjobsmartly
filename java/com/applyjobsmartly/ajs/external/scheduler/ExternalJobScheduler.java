package com.applyjobsmartly.ajs.external.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.applyjobsmartly.ajs.entity.VerifiedCompany;
import com.applyjobsmartly.ajs.external.service.ExternalJobFetcher;
import com.applyjobsmartly.ajs.repository.JobRepository;
import com.applyjobsmartly.ajs.repository.VerifiedCompanyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalJobScheduler {

    private final VerifiedCompanyRepository companyRepository;
    private final ExternalJobFetcher jobFetcher;
    private final JobRepository jobRepository;

    @Scheduled(cron = "0 0 9 * * ?") // daily 9 AM
    public void fetchExternalJobs() {

        log.info("begin ExternalJobScheduler --> fetchExternalJobs");

        List<VerifiedCompany> companies =
                companyRepository.findAll();

        companies.forEach(company -> {
            try {
                jobRepository.saveAll(
                        jobFetcher.fetchJobs(company.getCareerPageUrl())
                );
            } catch (Exception e) {
                log.error("Failed for company {}", company.getCompanyName(), e);
            }
        });

        log.info("end ExternalJobScheduler --> fetchExternalJobs");
    }
}
