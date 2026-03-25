package com.cognizant.greengov.dto.compliance_audit_dto;

import java.time.Instant;

import lombok.Data;

@Data
public class ReportResponseDTO {

	private Long reportId;

	private String scope;
	private Long targetId;

	private String title;
	private String description;

	private String format;
	private String status;

	private Instant generatedDate;
	private Long generatedByUserId;

	private String fileUrl;
}