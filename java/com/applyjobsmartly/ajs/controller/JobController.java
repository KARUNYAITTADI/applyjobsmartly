package com.applyjobsmartly.ajs.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applyjobsmartly.ajs.dto.request.JobFilterRequest;
import com.applyjobsmartly.ajs.dto.response.JobResponse;
import com.applyjobsmartly.ajs.service.JobService;
import com.applyjobsmartly.ajs.util.ResponseModel;
import com.applyjobsmartly.ajs.util.StatusCodeEnum;
import com.applyjobsmartly.ajs.util.StatusCodeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @PostMapping("/all")
    public ResponseEntity<ResponseModel<List<JobResponse>>> getAllJobs(
            @RequestBody JobFilterRequest request,
            @RequestHeader(value = "X-USER-EMAIL", required = false) String email) {

        log.info("begin JobController --> getAllJobs");

        Page<JobResponse> response =
                jobService.getAllJobs(request, email);

        log.info("end JobController --> getAllJobs");

        return StatusCodeMapper.build(
                StatusCodeEnum.SUCCESS,
                response.getContent(),   // ✅ return content, not Page
                response.getSize(),
                response.getNumber() + 1,
                response.getTotalElements()
        );
        
    }
}
