package com.applyjobsmartly.ajs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "verified_companies")
@Getter
@Setter
public class VerifiedCompany extends BaseEntity {

    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "career_page_url", length = 1000)
    private String careerPageUrl;

    @Column(name = "verified")
    private boolean verified;
}
