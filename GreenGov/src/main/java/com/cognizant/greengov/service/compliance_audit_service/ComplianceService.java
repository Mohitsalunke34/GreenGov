package com.cognizant.greengov.service.compliance_audit_service;

import com.cognizant.greengov.dto.compliance_audit_dto.ComplianceRecordCreateRequestDTO;
import com.cognizant.greengov.dto.compliance_audit_dto.ComplianceRecordResponseDTO;

public interface ComplianceService {

    ComplianceRecordResponseDTO recordCompliance(
            ComplianceRecordCreateRequestDTO dto,
            Long complianceOfficerUserId
    );
}