package com.cognizant.greengov.controller.disbursement_incentive_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.service.disburse_incentive_service.DisbursementService;

@RestController
@RequestMapping("/api/disbursements")
public class DisbursementController {

	private final DisbursementService service;

	public DisbursementController(DisbursementService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Void> disburse(@RequestParam Long officerUserId, @RequestParam Long incentiveId,
			@RequestParam Double amount) {

		service.disburse(incentiveId, amount, officerUserId);
		return ResponseEntity.ok().build();
	}
}
