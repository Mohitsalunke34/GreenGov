package com.cognizant.greengov.service.disburse_incentive_service;

public interface DisbursementService {

	void disburse(Long incentiveId, Double amount, Long officerUserId);
}