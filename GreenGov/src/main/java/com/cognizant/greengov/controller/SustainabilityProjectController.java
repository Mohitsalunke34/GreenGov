package com.cognizant.greengov.controller;

import com.cognizant.greengov.dto.SustainabilityRequest;
import com.cognizant.greengov.dto.SustainabilityResponse;
import com.cognizant.greengov.exception.DuplicateProjectException;
import com.cognizant.greengov.exception.ProjectNotFound;
import com.cognizant.greengov.service.SustainabilityProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class SustainabilityProjectController {

    private final SustainabilityProjectService service;

    @PostMapping("/save")
    public ResponseEntity<SustainabilityResponse> createProject(@RequestBody @Validated SustainabilityRequest request) throws DuplicateProjectException {
        return new ResponseEntity<>(service.saveProject(request), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<SustainabilityResponse>> getAll() {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @GetMapping("/fetch/{projectId}")
    public ResponseEntity<SustainabilityResponse> getById(@PathVariable Long projectId) throws ProjectNotFound {
        return ResponseEntity.ok(service.getProjectById(projectId));
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<String> delete(@PathVariable Long projectId) throws ProjectNotFound {
        service.deleteProject(projectId);
        return ResponseEntity.ok("Deleted successfully");
    }
}