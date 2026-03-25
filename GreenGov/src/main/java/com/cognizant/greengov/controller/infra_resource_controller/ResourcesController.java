package com.cognizant.greengov.controller.infra_resource_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.infra_resource_dto.ResourcesCreateRequestDTO;
import com.cognizant.greengov.dto.infra_resource_dto.ResourcesResponseDTO;
import com.cognizant.greengov.service.infra_resource_service.ResourcesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

	private final ResourcesService service;

	public ResourcesController(ResourcesService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ResourcesResponseDTO> allocate(@RequestBody @Valid ResourcesCreateRequestDTO dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.allocateResource(dto));
	}
}