package com.cognizant.greengov.service;

import java.util.List;

import com.cognizant.greengov.dto.SustainabilityRequest;
import com.cognizant.greengov.dto.SustainabilityResponse;
import com.cognizant.greengov.exception.DuplicateProjectException;
import com.cognizant.greengov.exception.ProjectNotFound;

public interface SustainabilityProjectService {
    public abstract SustainabilityResponse saveProject(SustainabilityRequest request) throws DuplicateProjectException;
    
   
    public abstract SustainabilityResponse updateProject(Long projectId, SustainabilityRequest request) throws ProjectNotFound;
    
  
    public abstract String deleteProject(Long projectId) throws ProjectNotFound;
    
    public abstract SustainabilityResponse getProjectById(Long projectId) throws ProjectNotFound;
    
    public abstract List<SustainabilityResponse> getAllProjects();
}