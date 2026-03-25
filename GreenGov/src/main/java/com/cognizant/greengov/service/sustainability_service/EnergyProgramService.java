package com.cognizant.greengov.service.sustainability_service;

import java.util.List;

import com.cognizant.greengov.dto.sustainability_dto.EnergyProgramDTO;

public interface EnergyProgramService {

	List<EnergyProgramDTO> getAllPrograms();
	EnergyProgramDTO createprogram(EnergyProgramDTO EnPr);

}