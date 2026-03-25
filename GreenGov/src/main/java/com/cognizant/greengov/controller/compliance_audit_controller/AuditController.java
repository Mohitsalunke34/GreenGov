package com.cognizant.greengov.controller.compliance_audit_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.compliance_audit_dto.AuditCreateRequestDTO;
import com.cognizant.greengov.dto.compliance_audit_dto.AuditResponseDTO;
import com.cognizant.greengov.service.compliance_audit_service.AuditService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/audits")
public class AuditController {

	private final AuditService service;

	public AuditController(AuditService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<AuditResponseDTO> start(@RequestParam Long auditorUserId,
			@RequestBody @Valid AuditCreateRequestDTO dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.startAudit(dto, auditorUserId));
	}

	@PostMapping("/{auditId}/close")
	public ResponseEntity<AuditResponseDTO> close(@PathVariable Long auditId, @RequestParam String status) {

		return ResponseEntity.ok(service.closeAudit(auditId, status));
	}
}