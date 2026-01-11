package com.applyjobsmartly.ajs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "experience")
    private String experience;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "source")
    private String source; // INTERNAL / EXTERNAL

    @Column(name = "apply_url", length = 1000)
    private String applyUrl;

    @Column(name = "verified")
    private boolean verified;
}
