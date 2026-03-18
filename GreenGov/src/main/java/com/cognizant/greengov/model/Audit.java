package com.cognizant.greengov.model;

import java.time.Instant;

import com.cognizant.greengov.model.Enums.AuditStatus;
import com.cognizant.greengov.model.Enums.ReportScope;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "audit", indexes = { @Index(name = "idx_audit_scope", columnList = "scope, scope_id"),
		@Index(name = "idx_audit_status", columnList = "status") })
public class Audit extends CreatedUpdatedLogs {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id")
	private Long id;
 
	@NotNull
	@Column(name = "officer_id", nullable = false)
	private Long officerId;// Soft FK to User ; add @ManyToOne<User> when User is present.
 
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "scope", nullable = false, length = 30)
	private ReportScope scope;
 
	@NotNull
	@Column(name = "scope_id", nullable = false)
	private Long scopeId;
 
	@Size(max = 250)
	@Column(name = "summary", length = 250)
	private String summary;
 
	@Lob
	@Column(name = "findings")
	private String findings;
 
	// “Date” in spec expanded into audit lifecycle dates
	@NotNull
	@Column(name = "opened_date", nullable = false)
	private Instant openedDate;
 
	@Column(name = "closed_date")
	private Instant closedDate;
 
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 30)
	private AuditStatus status = AuditStatus.PLANNED;
 
	@Min(1)
	@Max(5)
	@Column(name = "severity")
	private Integer severity; // 1=low .. 5=critical
 
	@Size(max = 200)
	@Column(name = "external_ref", length = 200)
	private String externalRef;
 
}