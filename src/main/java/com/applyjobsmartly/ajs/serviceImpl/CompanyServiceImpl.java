package com.applyjobsmartly.ajs.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.applyjobsmartly.ajs.entity.Company;
import com.applyjobsmartly.ajs.repository.CompanyRepository;
import com.applyjobsmartly.ajs.service.CompanyService;
import com.applyjobsmartly.ajs.util.AtsType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public void uploadCompanies(MultipartFile file) {

        log.info("begin CompanyServiceImpl --> uploadCompanies");

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String name = data[0].trim();
                String url = data[1].trim();

                AtsType atsType = detectAts(url);

                Company company = Company.builder()
                        .companyName(name)
                        .careerPageUrl(url)
                        .atsType(atsType)
                        .verified(true)
                        .active(true)
                        .build();

                companyRepository.save(company);
            }

        } catch (Exception e) {
            log.error("CSV upload failed", e);
        }

        log.info("end CompanyServiceImpl --> uploadCompanies");
    }

    private AtsType detectAts(String url) {
        if (url.contains("greenhouse")) return AtsType.GREENHOUSE;
        if (url.contains("lever")) return AtsType.LEVER;
        return AtsType.CUSTOM;
    }
}
