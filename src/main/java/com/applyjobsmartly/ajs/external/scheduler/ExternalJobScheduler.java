package com.applyjobsmartly.ajs.external.scheduler;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.applyjobsmartly.ajs.entity.Company;
import com.applyjobsmartly.ajs.entity.Job;
import com.applyjobsmartly.ajs.external.service.ExternalJobFetcher;
import com.applyjobsmartly.ajs.repository.CompanyRepository;
import com.applyjobsmartly.ajs.repository.JobRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalJobScheduler {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobFetcherFactory jobFetcherFactory;

    @Transactional
    @Scheduled(cron = "0 * * * * *") // every 30 mins
    public void syncExternalJobs() {

        log.info("begin JobSyncScheduler --> syncExternalJobs");

//        should get only active jobs in future
        List<Company> companies = companyRepository.findAll();

        companies.forEach(company -> {
            try {
                ExternalJobFetcher fetcher =
                        jobFetcherFactory.getFetcher(company.getAtsType());

                List<Job> fetchedJobs =
                        fetcher.fetchJobs(company.getCareerPageUrl());

                Set<String> fetchedUrls = new HashSet<>();

                for (Job job : fetchedJobs) {
                    fetchedUrls.add(job.getApplyUrl());

                    Optional<Job> existing =
                            jobRepository.findByCompany_IdAndApplyUrl(
                                    company.getId(),
                                    job.getApplyUrl()
                            );

                    if (existing.isPresent()) {
                        Job dbJob = existing.get();
                        dbJob.setLastFetchedAt(LocalDateTime.now());
                        dbJob.setActive(true);
                        jobRepository.save(dbJob);
                    } else {
                        job.setCompany(company);
                        job.setActive(true);
                        job.setLastFetchedAt(LocalDateTime.now());
                        jobRepository.save(job);
                    }
                }

                // 🔐 SAFE bulk update
                if (!fetchedUrls.isEmpty()) {
                    jobRepository.deactivateMissingJobs(
                            company.getId(),
                            fetchedUrls
                    );
                }

            } catch (Exception e) {
                log.error("Job sync failed for {}", company.getCompanyName(), e);
            }
        });

        log.info("end JobSyncScheduler --> syncExternalJobs");
    }
}
