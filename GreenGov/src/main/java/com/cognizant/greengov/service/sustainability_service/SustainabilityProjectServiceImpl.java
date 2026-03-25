package com.cognizant.greengov.service.sustainability_service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.model.sustainability_renewable_proj.SustainabilityProject;
import com.cognizant.greengov.repository.sustainability_repo.SustainabilityProjectRepository;

@Service
@Transactional
public class SustainabilityProjectServiceImpl implements SustainabilityProjectService {

	private final SustainabilityProjectRepository projectRepo;

	public SustainabilityProjectServiceImpl(SustainabilityProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
	}

	@Override
	public SustainabilityProject createProject(SustainabilityProject project) {
		project.setStatus("PLANNED");
		project.setStartDate(LocalDate.now());
		return projectRepo.save(project);
	}

	@Override
	public List<SustainabilityProject> getProjectsByStatus(String status) {
		return projectRepo.findByStatus(status);
	}
}