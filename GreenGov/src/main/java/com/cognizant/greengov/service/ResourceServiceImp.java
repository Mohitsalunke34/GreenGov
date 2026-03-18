package com.cognizant.greengov.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.greengov.dto.ResourceDTO;
import com.cognizant.greengov.dto.ResourceStatusDTO;
import com.cognizant.greengov.model.Resources;
import com.cognizant.greengov.repository.ResourceRepository;
import com.cognizant.greengov.exception.ResourceNotFoundException; 

@Service
public class ResourceServiceImp implements ResourceServiceInterface {

    @Autowired
    private ResourceRepository repository;

    @Override
    public Resources addResource(ResourceDTO dto) {
        Resources res = Resources.builder()
                .projectId(dto.getProjectId())
                .type(dto.getType())
                .quantity(dto.getQuantity())
                .status("Available")
                .build();

        return repository.save(res);
    }

    @Override
    public Resources updateResource(long resourceId, ResourceDTO dto) {
        Resources res = repository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));
        
        res.setProjectId(dto.getProjectId());
        res.setType(dto.getType());
        res.setQuantity(dto.getQuantity());
        return repository.save(res);
    }

    @Override
    public Resources getResource(long resourceId) {
        return repository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + resourceId));
    }

    @Override
    public void deleteResource(long resourceId) {
        if (!repository.existsById(resourceId)) {
            throw new ResourceNotFoundException("Cannot delete. Resource not found with id: " + resourceId);
        }
        repository.deleteById(resourceId);
    }

    @Override
    public List<Resources> getAllResources() {
        return repository.findAll();
    }

    @Override
    public Resources updateStatus(ResourceStatusDTO dto) {
        Resources res = repository.findById(dto.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + dto.getResourceId()));
        
        res.setStatus(dto.getStatus());
        return repository.save(res);
    }
}