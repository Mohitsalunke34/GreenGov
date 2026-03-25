package com.cognizant.greengov.service.infra_resource_service;

import java.util.List;

import com.cognizant.greengov.dto.infra_resource_dto.InfrastructureCreateRequestDTO;
import com.cognizant.greengov.dto.infra_resource_dto.InfrastructureResponseDTO;

public interface InfrastructureService {

	InfrastructureResponseDTO createInfrastructure(InfrastructureCreateRequestDTO dto);

	List<InfrastructureResponseDTO> getByProject(Long projectId);
}