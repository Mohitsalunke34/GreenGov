package com.cognizant.greengov.dto.compliance_audit_dto;

import java.time.Instant;

import lombok.Data;

@Data
public class AuditResponseDTO {

	private Long auditId;
	private Long officerUserId;

	private String scope;
	private Instant openedDate;
	private Instant closedDate;

	private String status;
	private Integer severity;
}