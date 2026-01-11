package com.applyjobsmartly.ajs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
public class UserProfile extends BaseEntity {

    @Column(name = "job_role")
    private String jobRole;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "preferred_location")
    private String preferredLocation;

    @Column(name = "skills")
    private String skills;
}
