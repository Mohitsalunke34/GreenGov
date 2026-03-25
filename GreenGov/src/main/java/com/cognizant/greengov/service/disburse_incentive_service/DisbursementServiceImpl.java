package com.cognizant.greengov.service.disburse_incentive_service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.model.incentive_subsidy.Disbursement;
import com.cognizant.greengov.model.incentive_subsidy.Incentive;
import com.cognizant.greengov.model.register_login.UserAccount;
import com.cognizant.greengov.repository.disbursement_incentive_repo.DisbursementRepository;
import com.cognizant.greengov.repository.disbursement_incentive_repo.IncentiveRepository;
import com.cognizant.greengov.repository.register_login_repo.UserAccountRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DisbursementServiceImpl implements DisbursementService {

	private final DisbursementRepository disbursementRepo;
	private final IncentiveRepository incentiveRepo;
	private final UserAccountRepository userRepo;

	@Override
	public void disburse(Long incentiveId, Double amount, Long officerUserId) {

		Incentive incentive = incentiveRepo.findById(incentiveId)
				.orElseThrow(() -> new IllegalArgumentException("Incentive not found"));

		UserAccount officer = userRepo.findById(officerUserId)
				.orElseThrow(() -> new IllegalArgumentException("Officer not found"));

		Disbursement d = new Disbursement();
		d.setIncentive(incentive);
		d.setOfficer(officer);
		d.setAmount(amount);
		d.setPaymentDate(LocalDate.now());
		d.setStatus("SUCCESS");

		disbursementRepo.save(d);
	}
}