package com.applyjobsmartly.ajs.serviceImpl;

import org.springframework.stereotype.Service;

import com.applyjobsmartly.ajs.dto.request.UserProfileRequest;
import com.applyjobsmartly.ajs.dto.response.UserProfileResponse;
import com.applyjobsmartly.ajs.entity.User;
import com.applyjobsmartly.ajs.entity.UserProfile;
import com.applyjobsmartly.ajs.exception.CustomException;
import com.applyjobsmartly.ajs.exception.ResourceNotFoundException;
import com.applyjobsmartly.ajs.repository.UserProfileRepository;
import com.applyjobsmartly.ajs.repository.UserRepository;
import com.applyjobsmartly.ajs.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;

    @Override
    public UserProfileResponse updateProfile(
            String email, UserProfileRequest request) {

        log.info("begin ProfileServiceImpl --> updateProfile");

        try {
            User user = userRepository.findByEmailAndActiveTrue(email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found"));

            UserProfile profile = profileRepository.findByCreatedBy(user.getId())
                    .orElse(new UserProfile());

            profile.setCreatedBy(user.getId());
            profile.setJobRole(request.getJobRole());
            profile.setExperienceYears(request.getExperienceYears());
            profile.setPreferredLocation(request.getPreferredLocation());
            profile.setSkills(request.getSkills());

            profileRepository.save(profile);

            return mapToResponse(profile);

        } catch (Exception e) {
            log.error("Error updating profile", e);
            throw new CustomException("Unable to update profile");
        } finally {
            log.info("end ProfileServiceImpl --> updateProfile");
        }
    }

    private UserProfileResponse mapToResponse(UserProfile profile) {
        UserProfileResponse response = new UserProfileResponse();
        response.setJobRole(profile.getJobRole());
        response.setExperienceYears(profile.getExperienceYears());
        response.setPreferredLocation(profile.getPreferredLocation());
        response.setSkills(profile.getSkills());
        return response;
    }
}
