package com.cognizant.greengov.controller.infra_resource_controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.infra_resource_dto.InfrastructureCreateRequestDTO;
import com.cognizant.greengov.dto.infra_resource_dto.InfrastructureResponseDTO;
import com.cognizant.greengov.service.infra_resource_service.InfrastructureService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/infrastructure")
public class InfrastructureController {

    private final InfrastructureService infrastructureService;

    public InfrastructureController(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    
    //  Create infrastructure for a project
     
    @PostMapping
    public ResponseEntity<InfrastructureResponseDTO> create(
            @RequestBody @Valid InfrastructureCreateRequestDTO dto) {

        InfrastructureResponseDTO response =
                infrastructureService.createInfrastructure(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
     // List all infrastructure for a project
     
    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<InfrastructureResponseDTO>> getByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                infrastructureService.getByProject(projectId)
        );
    }
}