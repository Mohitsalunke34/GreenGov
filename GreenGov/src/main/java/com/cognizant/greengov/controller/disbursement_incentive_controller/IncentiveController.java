package com.cognizant.greengov.controller.disbursement_incentive_controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.disburse_incentive_dto.IncentiveCreateRequestDTO;
import com.cognizant.greengov.dto.disburse_incentive_dto.IncentiveResponseDTO;
import com.cognizant.greengov.service.disburse_incentive_service.IncentiveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/incentives")
public class IncentiveController {

    private final IncentiveService incentiveService;

    public IncentiveController(IncentiveService incentiveService) {
        this.incentiveService = incentiveService;
    }

    
     // Officer creates incentive after approving an application
     
    @PostMapping
    public ResponseEntity<IncentiveResponseDTO> createIncentive(
            @RequestParam Long officerUserId,   // later from JWT
            @RequestBody @Valid IncentiveCreateRequestDTO dto) {

        IncentiveResponseDTO response =
                incentiveService.createIncentive(dto, officerUserId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
     // Get incentive details for a specific application
     
    @GetMapping("/by-application/{applicationId}")
    public ResponseEntity<IncentiveResponseDTO> getByApplication(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                incentiveService.getByApplication(applicationId)
        );
    }

    
    // List incentives for a citizen/business (beneficiary view)
     
    @GetMapping("/by-beneficiary/{participantId}")
    public ResponseEntity<List<IncentiveResponseDTO>> getByBeneficiary(
            @PathVariable Long participantId) {

        return ResponseEntity.ok(
                incentiveService.getByBeneficiary(participantId)
        );
    }
}