package com.cognizant.greengov.controller.sustainability_prog_controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.model.sustainability_renewable_proj.SustainabilityProject;
import com.cognizant.greengov.service.sustainability_service.SustainabilityProjectService;

@RestController
@RequestMapping("/api/projects")
public class SustainabilityProjectController {

	private final SustainabilityProjectService projectService;

	public SustainabilityProjectController(SustainabilityProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * Admin / Officer creates project
	 */
	@PostMapping
	public ResponseEntity<SustainabilityProject> create(@RequestBody SustainabilityProject project) {

		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(project));
	}

	/**
	 * List projects by status (dashboard / reporting)
	 */
	@GetMapping
	public ResponseEntity<List<SustainabilityProject>> getByStatus(@RequestParam String status) {

		return ResponseEntity.ok(projectService.getProjectsByStatus(status));
	}
}