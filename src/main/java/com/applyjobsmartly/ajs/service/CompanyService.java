package com.applyjobsmartly.ajs.service;

import org.springframework.web.multipart.MultipartFile;

public interface CompanyService {
    void uploadCompanies(MultipartFile file);
}
