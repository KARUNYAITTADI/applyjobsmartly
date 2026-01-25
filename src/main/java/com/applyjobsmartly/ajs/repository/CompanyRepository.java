package com.applyjobsmartly.ajs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.applyjobsmartly.ajs.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    List<Company> findByActiveTrue();

    boolean existsByCompanyNameIgnoreCase(String companyName);

//	List<Company> findAllActive();
}
