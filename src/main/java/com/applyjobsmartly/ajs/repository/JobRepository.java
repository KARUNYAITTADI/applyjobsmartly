package com.applyjobsmartly.ajs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.applyjobsmartly.ajs.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> ,
JpaSpecificationExecutor<Job> {

    List<Job> findByActiveTrue();

    List<Job> findBySourceAndActiveTrue(String source);

    List<Job> findByVerifiedTrueAndActiveTrue();
}
