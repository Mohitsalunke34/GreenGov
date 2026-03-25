package com.cognizant.greengov.service.disburse_incentive_service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.disburse_incentive_dto.IncentiveCreateRequestDTO;
import com.cognizant.greengov.dto.disburse_incentive_dto.IncentiveResponseDTO;
import com.cognizant.greengov.model.incentive_subsidy.Incentive;
import com.cognizant.greengov.model.register_login.ParticipantProfile;
import com.cognizant.greengov.model.register_login.UserAccount;
import com.cognizant.greengov.model.sustainability_renewable_proj.ProgramApplication;
import com.cognizant.greengov.modelmapper.IncentiveMapper;
import com.cognizant.greengov.repository.disbursement_incentive_repo.IncentiveRepository;
import com.cognizant.greengov.repository.register_login_repo.ParticipantProfileRepository;
import com.cognizant.greengov.repository.register_login_repo.UserAccountRepository;
import com.cognizant.greengov.repository.sustainability_repo.ProgramApplicationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class IncentiveServiceImpl implements IncentiveService {

	private final IncentiveRepository incentiveRepo;
	private final ProgramApplicationRepository appRepo;
	private final UserAccountRepository userRepo;
	private final ParticipantProfileRepository participantRepo;

	@Override
	public IncentiveResponseDTO createIncentive(IncentiveCreateRequestDTO dto, Long officerUserId) {

		ProgramApplication app = appRepo.findById(dto.getApplicationId())
				.orElseThrow(() -> new IllegalArgumentException("Application not found"));

		if (incentiveRepo.findByApplication(app).isPresent()) {
			throw new IllegalStateException("Incentive already exists");
		}

		UserAccount officer = userRepo.findById(officerUserId)
				.orElseThrow(() -> new IllegalArgumentException("Officer not found"));

		Incentive incentive = new Incentive();
		incentive.setApplication(app);
		incentive.setProgram(app.getProgram());
		incentive.setBeneficiary(app.getApplicant());
		incentive.setAmount(dto.getAmount());
		incentive.setSanctionedDate(LocalDate.now());
		incentive.setStatus("APPROVED");
		incentive.setApprovedBy(officer);

		incentive.setCreatedBy(officer.getUsername());
		incentive.setUpdatedBy(officer.getUsername());

		return IncentiveMapper.toDTO(incentiveRepo.save(incentive));
	}

	@Override
	public IncentiveResponseDTO getByApplication(Long applicationId) {

		ProgramApplication app = appRepo.findById(applicationId)
				.orElseThrow(() -> new IllegalArgumentException("Application not found"));

		Incentive incentive = incentiveRepo.findByApplication(app)
				.orElseThrow(() -> new IllegalArgumentException("No incentive found for this application"));

		return IncentiveMapper.toDTO(incentive);
	}

	@Override
	public List<IncentiveResponseDTO> getByBeneficiary(Long participantId) {

		ParticipantProfile participant = participantRepo.findById(participantId)
				.orElseThrow(() -> new IllegalArgumentException("Participant not found"));

		return incentiveRepo.findByBeneficiary(participant).stream().map(IncentiveMapper::toDTO).toList();
	}
}