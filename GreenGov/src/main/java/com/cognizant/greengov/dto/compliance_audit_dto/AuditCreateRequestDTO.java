package com.cognizant.greengov.dto.compliance_audit_dto;

import java.time.Instant;

import lombok.Data;

@Data
public class AuditCreateRequestDTO {

	private String scope;
	private Long targetId;
	private Instant openedDate;
	private String summary;
}