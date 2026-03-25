package com.cognizant.greengov.repository.register_login_repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.greengov.model.register_login.ParticipantProfile;

public interface ParticipantProfileRepository extends JpaRepository<ParticipantProfile, Long> {
}