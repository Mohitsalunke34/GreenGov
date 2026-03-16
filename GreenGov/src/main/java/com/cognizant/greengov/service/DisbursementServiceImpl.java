package com.cognizant.greengov.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.DisbursementRequest;
import com.cognizant.greengov.dto.DisbursementResponse;
import com.cognizant.greengov.exception.InvalidIncentiveException;
import com.cognizant.greengov.exception.ResourceNotFoundException;
import com.cognizant.greengov.model.Disbursement;
import com.cognizant.greengov.model.Incentive;
import com.cognizant.greengov.repository.DisbursementRepository;
import com.cognizant.greengov.repository.IncentiveRepository;

@Service
public class DisbursementServiceImpl implements DisbursementService {

    @Autowired
    private DisbursementRepository disbursementRepository;

    @Autowired
    private IncentiveRepository incentiveRepository;

    @Override
    @Transactional
    public DisbursementResponse processDisbursement(DisbursementRequest request) {
        // 1. Validate Incentive existence
        Incentive incentive = incentiveRepository.findById(request.getIncentiveId())
                .orElseThrow(() -> new ResourceNotFoundException("Incentive not found for ID: " + request.getIncentiveId()));

        // 2. Business Logic: Must be APPROVED to pay
        if (!"APPROVED".equalsIgnoreCase(incentive.getStatus())) {
            throw new InvalidIncentiveException("Disbursement failed. Incentive must be 'APPROVED'. Current status: " + incentive.getStatus());
        }

        // 3. Create Disbursement
        Disbursement disbursement = new Disbursement();
        disbursement.setIncentiveId(request.getIncentiveId());
        disbursement.setOfficerId(request.getOfficerId());
        disbursement.setPaymentDate(LocalDate.now());
        disbursement.setStatus("COMPLETED");

        Disbursement savedDisbursement = disbursementRepository.save(disbursement);

        // 4. Update the parent Incentive to DISBURSED
        incentive.setStatus("DISBURSED");
        incentiveRepository.save(incentive);

        return mapToResponse(savedDisbursement, "Disbursement processed successfully. Funds released to applicant.");
    }

    @Override
    public DisbursementResponse getDisbursementById(Long id) {
        Disbursement d = disbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disbursement record not found for ID: " + id));
        return mapToResponse(d, "Record retrieved successfully.");
    }

    @Override
    public List<DisbursementResponse> getAllDisbursements() {
        return disbursementRepository.findAll().stream()
                .map(d -> mapToResponse(d, "Disbursement record found."))
                .collect(Collectors.toList());
    }

    private DisbursementResponse mapToResponse(Disbursement d, String message) {
        return DisbursementResponse.builder()
                .message(message)
                .disbursementId(d.getDisbursementId())
                .incentiveId(d.getIncentiveId())
                .officerId(d.getOfficerId())
                .paymentDate(d.getPaymentDate())
                .status(d.getStatus())
                .build();
    }
}