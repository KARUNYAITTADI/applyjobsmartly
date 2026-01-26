package com.applyjobsmartly.ajs.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.applyjobsmartly.ajs.entity.VerifiedCompany;

@Repository
public interface VerifiedCompanyRepository extends JpaRepository<VerifiedCompany, UUID> {

    Optional<VerifiedCompany> findByCompanyNameAndVerifiedTrue(String companyName);
}
