package com.applyjobsmartly.ajs.serviceImpl;

import org.springframework.data.jpa.domain.Specification;

import com.applyjobsmartly.ajs.entity.Job;

public class JobSpecification {

    public static Specification<Job> hasJobRole(String role) {
        return (root, query, cb) ->
                role == null ? null :
                        cb.like(cb.lower(root.get("title")),
                                "%" + role.toLowerCase() + "%");
    }

    public static Specification<Job> hasLocation(String location) {
        return (root, query, cb) ->
                location == null ? null :
                        cb.like(cb.lower(root.get("location")),
                                "%" + location.toLowerCase() + "%");
    }

    public static Specification<Job> isVerified(Boolean verified) {
        return (root, query, cb) ->
                verified == null ? null :
                        cb.equal(root.get("verified"), verified);
    }

    public static Specification<Job> isExternal(Boolean external) {
        return (root, query, cb) ->
                external == null ? null :
                        cb.equal(root.get("source"), external ? "EXTERNAL" : "INTERNAL");
    }
}
