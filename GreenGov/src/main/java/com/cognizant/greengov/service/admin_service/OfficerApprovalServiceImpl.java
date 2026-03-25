package com.cognizant.greengov.service.admin_service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.model.Enums.ProfileStatus;
import com.cognizant.greengov.model.register_login.OfficerProfile;
import com.cognizant.greengov.repository.register_login_repo.OfficerProfileRepository;

@Service
@Transactional
public class OfficerApprovalServiceImpl implements OfficerApprovalService {

	private final OfficerProfileRepository officerRepo;

	public OfficerApprovalServiceImpl(OfficerProfileRepository officerRepo) {
		this.officerRepo = officerRepo;
	}

	@Override
	public List<OfficerProfile> getPendingOfficers() {
		return officerRepo.findByStatus(ProfileStatus.PENDING);
	}

	@Override
	public void approveOfficer(Long officerProfileId) {
		OfficerProfile officer = officerRepo.findById(officerProfileId)
				.orElseThrow(() -> new IllegalArgumentException("Officer not found"));

		officer.setStatus(ProfileStatus.APPROVED);
		officer.setApprovedAt(LocalDateTime.now());
	}

	@Override
	public void rejectOfficer(Long officerProfileId) {
		OfficerProfile officer = officerRepo.findById(officerProfileId)
				.orElseThrow(() -> new IllegalArgumentException("Officer not found"));

		officer.setStatus(ProfileStatus.REJECTED);
	}
}
