package com.cognizant.greengov.model;



import java.time.Instant;

import com.cognizant.greengov.model.Enums.ComplianceResult;
import com.cognizant.greengov.model.Enums.ComplianceSubjectType;

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
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@Entity
@Table(name = "compliance_record", indexes = {
 
		@Index(name = "idx_compl_sub", columnList = "subject_type,subject_id"),
		@Index(name = "idx_compl_recorded_date", columnList = "recorded_date") })
public class ComplianceRecord extends CreatedUpdatedLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compliance_id")
	private Long id;
	@Enumerated(EnumType.STRING) // Stores the actual string name in DB
	@Column(name = "subject_type")
	private ComplianceSubjectType subjectType;
	@NotNull
//	@NotNull: prevents null values at runtime validation.
//	nullable = false: prevents null values at the database level.
//	This combination provides full safety and consistency.
	@Column(name = "subject_id", nullable = false)
	private Long subjectId;
	@NotNull
	@Column(name = "entity_id", nullable = false)
	private Long entityId;// Soft FK to CitizenBusiness
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "result", nullable = false, length = 30)
	private ComplianceResult result;
	@NotNull
	@Column(name = "recorded_date", nullable = false)
	private Instant recordedDate;
 
	@Size(max = 200, message = "Reference Code can not be greater than 200 in size")
	@Column(name = "reference_code", length = 200, unique = true)
 
	private String referenceCode;
	@Lob // Large Objects
	@Column(name = "notes")
	private String notes;
	@Size(max = 300, message = "Size of the URl can not be greater than 300 in size")
	@Column(name = "evidence_url", length = 300)
	private String evidenceURL;
	@NotNull
	@Column(name = "compliance_manager_id", nullable = false)
	private Long complianceManagerId;// Soft FK to User (Compliance Officer/Manager
}