package com.applyjobsmartly.ajs.external.scheduler;

import org.springframework.stereotype.Component;

import com.applyjobsmartly.ajs.external.client.GreenhouseJobFetcher;
import com.applyjobsmartly.ajs.external.client.LeverJobFetcherImpl;
import com.applyjobsmartly.ajs.external.service.ExternalJobFetcher;
import com.applyjobsmartly.ajs.util.AtsType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobFetcherFactory {

    private final GreenhouseJobFetcher greenhouseFetcher;
    private final LeverJobFetcherImpl leverFetcher;

    public ExternalJobFetcher getFetcher(AtsType atsType) {
        return switch (atsType) {
            case GREENHOUSE -> greenhouseFetcher;
            case LEVER -> leverFetcher;
            default -> throw new IllegalArgumentException("Unsupported ATS");
        };
    }
}

