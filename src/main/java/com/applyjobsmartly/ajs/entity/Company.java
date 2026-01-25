package com.applyjobsmartly.ajs.entity;

import com.applyjobsmartly.ajs.util.AtsType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Company extends BaseEntity {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "career_page_url", nullable = false)
    private String careerPageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "ats_type", nullable = false)
    private AtsType atsType;

    @Column(name = "verified")
    private boolean verified = true;
}
