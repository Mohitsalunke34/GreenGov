package com.cognizant.greengov.model;



import java.time.Instant;

import com.cognizant.greengov.model.Enums.ReportScope;
import com.cognizant.greengov.model.Enums.ReportStatus;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name = "report", indexes = { @Index(name = "idx_report_scope", columnList = "scope, scope_id"),
		@Index(name = "idx_report_generated_date", columnList = "generated_date") })
public class Report extends CreatedUpdatedLogs {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long id;
 
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "scope", nullable = false, length = 30)
	private ReportScope scope;
 
	@Column(name = "scope_id")
	private Long scopeId;
 
	@NotNull
	@Column(name = "generated_date", nullable = false)
	private Instant generatedDate;
 
	@NotNull
	@Size(min = 3, max = 150)
	@Column(name = "title", nullable = false, length = 150)
	private String title;
 
	@Size(max = 500)
	@Column(name = "description", length = 500)
	private String description;
 
	// Raw metrics payload (JSON or key-value text)
	@Lob
	@Column(name = "metrics")
	private String metrics;
 
	@Size(max = 30)
	@Column(name = "format", length = 30)
	private String format; // e.g., "JSON", "CSV", "PDF"
 
	@Size(max = 30)
	@Column(name = "status", length = 30)
	private ReportStatus status; // "GENERATED", "FAILED", "ARCHIVED"
 
	@Column(name = "generated_by_user_id")
	private Long generatedByUserId;// Soft FK to User
 
	@Size(max = 500)
	@Column(name = "file_url", length = 500)
	private String fileUrl;
 
}
