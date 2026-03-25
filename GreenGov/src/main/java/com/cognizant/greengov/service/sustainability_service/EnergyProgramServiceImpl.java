package com.cognizant.greengov.service.sustainability_service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.cognizant.greengov.dto.sustainability_dto.EnergyProgramDTO;
import com.cognizant.greengov.model.sustainability_renewable_proj.EnergyProgram;
import com.cognizant.greengov.modelmapper.EnergyProgramMapper;
import com.cognizant.greengov.repository.sustainability_repo.EnergyProgramRepository;

@Service

public class EnergyProgramServiceImpl implements EnergyProgramService {

	private final EnergyProgramRepository programRepo;

	public EnergyProgramServiceImpl(EnergyProgramRepository programRepo) {
		this.programRepo = programRepo;
	}

	@Override
	public List<EnergyProgramDTO> getAllPrograms() {
		return programRepo.findAll().stream().map(EnergyProgramMapper::toDTO).toList();
	}

	@Override
	@Transactional
	public EnergyProgramDTO createprogram(@Validated EnergyProgramDTO dto) {

		EnergyProgram program = new EnergyProgram();
		program.setTitle(dto.getTitle());
		program.setDescription(dto.getDescription());
		program.setStartDate(dto.getStartDate());
		program.setEndDate(dto.getEndDate());
		program.setBudget(dto.getBudget());
		program.setStatus(dto.getStatus());
		program.setCreatedBy("System");
		program.setUpdatedBy("System");

		EnergyProgram saved = programRepo.save(program);
		return EnergyProgramMapper.toDTO(saved);

	}

}