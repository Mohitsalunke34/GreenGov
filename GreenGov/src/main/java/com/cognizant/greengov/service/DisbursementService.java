package com.cognizant.greengov.service;

import java.util.List;

import com.cognizant.greengov.dto.DisbursementRequest;
import com.cognizant.greengov.dto.DisbursementResponse;

public interface DisbursementService {
    DisbursementResponse processDisbursement(DisbursementRequest request);
    DisbursementResponse getDisbursementById(Long id);
    List<DisbursementResponse> getAllDisbursements();
}