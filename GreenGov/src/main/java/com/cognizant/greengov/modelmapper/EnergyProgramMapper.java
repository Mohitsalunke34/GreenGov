package com.cognizant.greengov.modelmapper;

import com.cognizant.greengov.dto.sustainability_dto.EnergyProgramDTO;
import com.cognizant.greengov.model.sustainability_renewable_proj.EnergyProgram;

public class EnergyProgramMapper {

	private EnergyProgramMapper() {
	}

	public static EnergyProgramDTO toDTO(EnergyProgram entity) {
		EnergyProgramDTO dto = new EnergyProgramDTO();
		dto.setProgramId(entity.getProgramId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setBudget(entity.getBudget());
		dto.setStatus(entity.getStatus());

		if (entity.getOwner() != null) {
			dto.setOwnerUserId(entity.getOwner().getId());
		}
		return dto;
	}
}