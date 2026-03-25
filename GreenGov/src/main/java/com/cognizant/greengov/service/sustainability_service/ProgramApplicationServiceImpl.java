package com.cognizant.greengov.service.sustainability_service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.sustainability_dto.ProgramApplicationRequestDTO;
import com.cognizant.greengov.model.register_login.ParticipantProfile;
import com.cognizant.greengov.model.sustainability_renewable_proj.EnergyProgram;
import com.cognizant.greengov.model.sustainability_renewable_proj.ProgramApplication;
import com.cognizant.greengov.repository.register_login_repo.ParticipantProfileRepository;
import com.cognizant.greengov.repository.sustainability_repo.EnergyProgramRepository;
import com.cognizant.greengov.repository.sustainability_repo.ProgramApplicationRepository;

@Service
@Transactional
public class ProgramApplicationServiceImpl implements ProgramApplicationService {

	private final ProgramApplicationRepository appRepo;
	private final EnergyProgramRepository programRepo;
	private final ParticipantProfileRepository participantRepo;

	public ProgramApplicationServiceImpl(ProgramApplicationRepository appRepo, EnergyProgramRepository programRepo,
			ParticipantProfileRepository participantRepo) {
		this.appRepo = appRepo;
		this.programRepo = programRepo;
		this.participantRepo = participantRepo;
	}

	@Override
	public void apply(Long participantId, ProgramApplicationRequestDTO dto) {

		ParticipantProfile applicant = participantRepo.findById(participantId)
				.orElseThrow(() -> new IllegalArgumentException("Participant not found"));

		EnergyProgram program = programRepo.findById(dto.getProgramId())
				.orElseThrow(() -> new IllegalArgumentException("Program not found"));

		if (appRepo.findByApplicantAndProgram(applicant, program).isPresent()) {
			throw new IllegalStateException("Already applied");
		}

		ProgramApplication app = new ProgramApplication();
		app.setApplicant(applicant);
		app.setProgram(program);
		app.setSubmittedDate(LocalDate.now());
		app.setStatus("PENDING");

		appRepo.save(app);
	}
	
}