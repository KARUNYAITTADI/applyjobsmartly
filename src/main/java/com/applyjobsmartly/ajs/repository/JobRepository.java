package com.applyjobsmartly.ajs.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.applyjobsmartly.ajs.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> ,
JpaSpecificationExecutor<Job> {

    List<Job> findByActiveTrue();

    List<Job> findBySourceAndActiveTrue(String source);

    List<Job> findByVerifiedTrueAndActiveTrue();
    
//    Optional<Job> findByExternalJobIdAndExternalSource(
//            String externalJobId,
//            String externalSource
//    );

    boolean existsByCompanyIdAndApplyUrl(UUID companyId, String applyUrl);

    @Modifying
    @Query("""
    UPDATE Job j
    SET j.active = false
    WHERE j.company.id = :companyId
    AND j.applyUrl NOT IN :urls
    """)
    void deactivateMissingJobs(
        UUID companyId,
        Set<String> urls
    );
    
 // ✔️ Used for deduplication
    Optional<Job> findByCompany_IdAndApplyUrl(UUID companyId, String applyUrl);

    // ✔️ Faster exists check (optional but recommended)
    boolean existsByCompany_IdAndApplyUrl(UUID companyId, String applyUrl);

}
