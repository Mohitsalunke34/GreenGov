package com.cognizant.greengov.service.infra_resource_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.infra_resource_dto.ResourcesCreateRequestDTO;
import com.cognizant.greengov.dto.infra_resource_dto.ResourcesResponseDTO;
import com.cognizant.greengov.model.resource_infrastructure.Resources;
import com.cognizant.greengov.model.sustainability_renewable_proj.SustainabilityProject;
import com.cognizant.greengov.modelmapper.ResourcesMapper;
import com.cognizant.greengov.repository.infra_resource_repo.ResourcesRepository;
import com.cognizant.greengov.repository.sustainability_repo.SustainabilityProjectRepository;

@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

	private final ResourcesRepository resourcesRepo;
	private final SustainabilityProjectRepository projectRepo;

	public ResourcesServiceImpl(ResourcesRepository resourcesRepo, SustainabilityProjectRepository projectRepo) {
		this.resourcesRepo = resourcesRepo;
		this.projectRepo = projectRepo;
	}

	@Override
	public ResourcesResponseDTO allocateResource(ResourcesCreateRequestDTO dto) {

		SustainabilityProject project = projectRepo.findById(dto.getProjectId())
				.orElseThrow(() -> new IllegalArgumentException("Project not found"));

		Resources resource = new Resources();
		resource.setProject(project);
		resource.setType(dto.getType());
		resource.setQuantity(dto.getQuantity());
		resource.setStatus("ALLOCATED");

		return ResourcesMapper.toDTO(resourcesRepo.save(resource));
	}
}