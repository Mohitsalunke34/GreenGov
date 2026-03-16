package com.cognizant.greengov.service;

import java.util.List;

import com.cognizant.greengov.dto.IncentiveRequest;
import com.cognizant.greengov.dto.IncentiveResponse;

public interface IncentiveService {
    IncentiveResponse createIncentive(IncentiveRequest request);
    IncentiveResponse getIncentiveById(Long id);
    List<IncentiveResponse> getAllIncentives();
    IncentiveResponse updateStatus(Long id, String status);
}