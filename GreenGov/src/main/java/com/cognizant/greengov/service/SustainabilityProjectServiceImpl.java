package com.cognizant.greengov.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cognizant.greengov.dto.SustainabilityRequest;
import com.cognizant.greengov.dto.SustainabilityResponse;
import com.cognizant.greengov.exception.DuplicateProjectException;
import com.cognizant.greengov.exception.ProjectNotFound;
import com.cognizant.greengov.model.SustainabilityProject;
import com.cognizant.greengov.repository.SustainabilityProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SustainabilityProjectServiceImpl implements SustainabilityProjectService {

    private final SustainabilityProjectRepository repository;

    @Override
    public SustainabilityResponse saveProject(SustainabilityRequest request) throws DuplicateProjectException {
        if (repository.findByTitle(request.getTitle()).isPresent()) {
            throw new DuplicateProjectException("Project title already exists.");
        }

        // Using Builder to create the Entity
        SustainabilityProject project = SustainabilityProject.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .budget(request.getBudget())
                .status(request.getStatus())
                .build();

        return mapToResponse(repository.save(project));
    }

    @Override
    public SustainabilityResponse updateProject(Long projectId, SustainabilityRequest request) throws ProjectNotFound {
        SustainabilityProject existing = repository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFound("Cannot update. Project not found."));
        
        // Update the fields (Entities usually don't need a full rebuild for updates)
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setBudget(request.getBudget());
        existing.setStatus(request.getStatus());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        
        return mapToResponse(repository.save(existing));
    }

    // ... deleteProject and other methods remain the same ...

    @Override
    public List<SustainabilityResponse> getAllProjects() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SustainabilityResponse mapToResponse(SustainabilityProject entity) {
        // Using Builder to create the DTO
        return SustainabilityResponse.builder()
                .projectId(entity.getProjectId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .budget(entity.getBudget())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public SustainabilityResponse getProjectById(Long projectId) throws ProjectNotFound {
        // Find the entity or throw a custom exception if missing
        SustainabilityProject project = repository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFound("Project with ID " + projectId + " not found."));
        
        // Convert the entity to a DTO using the builder
        return mapToResponse(project);
    }

    @Override
    public String deleteProject(Long projectId) throws ProjectNotFound {
        // Check if it exists before attempting deletion to handle errors gracefully
        if (!repository.existsById(projectId)) {
            throw new ProjectNotFound("Cannot delete. Project with ID " + projectId + " not found.");
        }
        
        repository.deleteById(projectId);
        
        return "Project with ID " + projectId + " has been deleted successfully.";
    }
}