package com.example.greengov.service;

import java.util.List;

import com.example.greengov.dto.IncentiveRequest;
import com.example.greengov.dto.IncentiveResponse;

public interface IncentiveService {
    IncentiveResponse createIncentive(IncentiveRequest request);
    IncentiveResponse getIncentiveById(Long id);
    List<IncentiveResponse> getAllIncentives();
    IncentiveResponse updateStatus(Long id, String status);
}