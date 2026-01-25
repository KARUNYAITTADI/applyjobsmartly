package com.applyjobsmartly.ajs.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.applyjobsmartly.ajs.entity.VerifiedCompany;
import com.applyjobsmartly.ajs.repository.VerifiedCompanyRepository;
import com.applyjobsmartly.ajs.service.CompanyService;
import com.applyjobsmartly.ajs.util.ResponseModel;
import com.applyjobsmartly.ajs.util.StatusCodeEnum;
import com.applyjobsmartly.ajs.util.StatusCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin/company")
@RequiredArgsConstructor
@Slf4j
public class AdminCompanyController {

    private final VerifiedCompanyRepository companyRepository;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ResponseModel<VerifiedCompany>> addCompany(
            @RequestBody VerifiedCompany company) {

        log.info("begin AdminCompanyController --> addCompany");

        VerifiedCompany saved = companyRepository.save(company);

        log.info("end AdminCompanyController --> addCompany");

        return StatusCodeMapper.build(
                StatusCodeEnum.CREATED,
                saved);
    }
    
    @PostMapping(
    	    value = "/companies/upload",
    	    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    	)
    public ResponseEntity<ResponseModel<String>> uploadCompanies(
            @RequestParam MultipartFile file) {

        log.info("begin CompanyController --> uploadCompanies");

        companyService.uploadCompanies(file);

        log.info("end CompanyController --> uploadCompanies");

        return StatusCodeMapper.build(
                StatusCodeEnum.SUCCESS,
                "Companies uploaded successfully"
        );
    }

}
