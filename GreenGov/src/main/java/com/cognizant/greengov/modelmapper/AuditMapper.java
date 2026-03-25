package com.cognizant.greengov.modelmapper;

import com.cognizant.greengov.dto.compliance_audit_dto.AuditResponseDTO;
import com.cognizant.greengov.model.compliance_audit.Audit;

public class AuditMapper {

	private AuditMapper() {
	}

	public static AuditResponseDTO toDTO(Audit entity) {
		AuditResponseDTO dto = new AuditResponseDTO();
		dto.setAuditId(entity.getId());
		dto.setOfficerUserId(entity.getOfficer().getId());
		dto.setScope(entity.getScope().name());
		dto.setOpenedDate(entity.getOpenedDate());
		dto.setClosedDate(entity.getClosedDate());
		dto.setStatus(entity.getStatus().name());
		dto.setSeverity(entity.getSeverity());
		return dto;
	}
}