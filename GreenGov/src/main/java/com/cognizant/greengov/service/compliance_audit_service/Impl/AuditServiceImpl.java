package com.cognizant.greengov.service.compliance_audit_service.Impl;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.compliance_audit_dto.AuditCreateRequestDTO;
import com.cognizant.greengov.dto.compliance_audit_dto.AuditResponseDTO;
import com.cognizant.greengov.model.Enums.AuditStatus;
import com.cognizant.greengov.model.Enums.ReportScope;
import com.cognizant.greengov.model.compliance_audit.Audit;
import com.cognizant.greengov.model.register_login.UserAccount;
import com.cognizant.greengov.modelmapper.AuditMapper;
import com.cognizant.greengov.repository.audit_compliance_repo.AuditRepository;
import com.cognizant.greengov.repository.register_login_repo.UserAccountRepository;
import com.cognizant.greengov.service.compliance_audit_service.AuditService;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

	private final AuditRepository auditRepo;
	private final UserAccountRepository userRepo;

	public AuditServiceImpl(AuditRepository auditRepo, UserAccountRepository userRepo) {
		this.auditRepo = auditRepo;
		this.userRepo = userRepo;
	}

	@Override
	public AuditResponseDTO startAudit(AuditCreateRequestDTO dto, Long auditorUserId) {

		UserAccount auditor = userRepo.findById(auditorUserId)
				.orElseThrow(() -> new IllegalArgumentException("Auditor not found"));

		Audit audit = new Audit();
		audit.setOfficer(auditor);
		audit.setScope(ReportScope.valueOf(dto.getScope()));
		audit.setOpenedDate(dto.getOpenedDate() != null ? dto.getOpenedDate() : Instant.now());
		audit.setStatus(AuditStatus.IN_PROGRESS);
		audit.setSummary(dto.getSummary());
		audit.setCreatedBy(auditor.getUsername());
		audit.setUpdatedBy(auditor.getUsername());

		return AuditMapper.toDTO(auditRepo.save(audit));
	}

	@Override
	public AuditResponseDTO closeAudit(Long auditId, String finalStatus) {

		Audit audit = auditRepo.findById(auditId).orElseThrow(() -> new IllegalArgumentException("Audit not found"));

		audit.setStatus(AuditStatus.valueOf(finalStatus));
		audit.setClosedDate(Instant.now());
		

		return AuditMapper.toDTO(audit);
	}
}