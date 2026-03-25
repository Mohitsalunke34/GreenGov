package com.cognizant.greengov.controller.compliance_audit_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.compliance_audit_dto.ComplianceRecordCreateRequestDTO;
import com.cognizant.greengov.dto.compliance_audit_dto.ComplianceRecordResponseDTO;
import com.cognizant.greengov.service.compliance_audit_service.ComplianceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

	private final ComplianceService service;

	public ComplianceController(ComplianceService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ComplianceRecordResponseDTO> record(@RequestParam("officerUserId") Long officerUserId,
			@RequestBody @Valid ComplianceRecordCreateRequestDTO dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.recordCompliance(dto, officerUserId));
	}
}
