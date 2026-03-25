package com.cognizant.greengov.service.infra_resource_service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.infra_resource_dto.InfrastructureCreateRequestDTO;
import com.cognizant.greengov.dto.infra_resource_dto.InfrastructureResponseDTO;
import com.cognizant.greengov.model.resource_infrastructure.Infrastructure;
import com.cognizant.greengov.model.sustainability_renewable_proj.SustainabilityProject;
import com.cognizant.greengov.modelmapper.InfrastructureMapper;
import com.cognizant.greengov.repository.infra_resource_repo.InfrastructureRepository;
import com.cognizant.greengov.repository.sustainability_repo.SustainabilityProjectRepository;

@Service
@Transactional
public class InfrastructureServiceImpl implements InfrastructureService {

	private final InfrastructureRepository infraRepo;
	private final SustainabilityProjectRepository projectRepo;

	public InfrastructureServiceImpl(InfrastructureRepository infraRepo, SustainabilityProjectRepository projectRepo) {
		this.infraRepo = infraRepo;
		this.projectRepo = projectRepo;
	}

	@Override
	public InfrastructureResponseDTO createInfrastructure(InfrastructureCreateRequestDTO dto) {

		SustainabilityProject project = projectRepo.findById(dto.getProjectId())
				.orElseThrow(() -> new IllegalArgumentException("Project not found"));

		Infrastructure infra = new Infrastructure();
		infra.setProject(project);
		infra.setType(dto.getType());
		infra.setLocation(dto.getLocation());
		infra.setCapacity(dto.getCapacity());
		infra.setStatus("ACTIVE");

		return InfrastructureMapper.toDTO(infraRepo.save(infra));
	}

	@Override
	public List<InfrastructureResponseDTO> getByProject(Long projectId) {

		SustainabilityProject project = projectRepo.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("Project not found"));

		return infraRepo.findByProject(project).stream().map(InfrastructureMapper::toDTO).toList();
	}

}