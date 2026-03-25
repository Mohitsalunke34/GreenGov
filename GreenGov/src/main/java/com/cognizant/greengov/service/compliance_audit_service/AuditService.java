package com.cognizant.greengov.service.compliance_audit_service;

import com.cognizant.greengov.dto.compliance_audit_dto.AuditCreateRequestDTO;
import com.cognizant.greengov.dto.compliance_audit_dto.AuditResponseDTO;

public interface AuditService {

	AuditResponseDTO startAudit(AuditCreateRequestDTO dto, Long auditorUserId);

	AuditResponseDTO closeAudit(Long auditId, String finalStatus);
}