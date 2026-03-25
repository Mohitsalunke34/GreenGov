package com.cognizant.greengov.service.sustainability_service;

import java.util.List;

import com.cognizant.greengov.model.sustainability_renewable_proj.SustainabilityProject;

public interface SustainabilityProjectService {

	SustainabilityProject createProject(SustainabilityProject project);

	List<SustainabilityProject> getProjectsByStatus(String status);
}
