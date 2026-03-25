package com.cognizant.greengov.dto.compliance_audit_dto;

import java.time.Instant;

import lombok.Data;

@Data
public class ComplianceRecordResponseDTO {

    private Long complianceId;
    private String subjectType;
    private Long subjectId;
    private Long participantId;

    private String result;
    private Instant recordedDate;
    private String notes;

    private Long complianceManagerUserId;
}