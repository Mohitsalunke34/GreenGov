package com.cognizant.greengov.model;



public class Enums {
 
	// for specific type of Compliance
	public enum ComplianceSubjectType {
		PROJECT, PROGRAM, INCETIVE
	}
 
	// for showing the compliance response
	public enum ComplianceResult {
		PASS, FAIL, NEEDS_REVIEW
	}
 
	// I have chosen those three Audit status but it can be modified as per
	// requirement
	public enum AuditStatus {
		PLANNED, IN_PROGRESS, COMPLETED
	}
 
	public enum ReportScope {
		PROJECT, PROGRAM, INCENTIVE, COMPLIANCE
	}
 
	public enum ReportStatus {
		GENERATED, FAILED, ARCHIVED
	}
	
	//need to change afterwards
	public enum DocumentType {
		PROJECT, PROGRAM, INCENTIVE, COMPLIANCE
	}
}