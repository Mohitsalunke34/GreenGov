package com.cognizant.greengov.dto.compliance_audit_dto;

import java.time.Instant;

import lombok.Data;

@Data
public class ComplianceRecordCreateRequestDTO {

	private String subjectType;
	private Long subjectId;
	private Long participantId;
	private String evidenceURL;
	
	private String result;
	private String notes;
	private Instant recordedDate;
}