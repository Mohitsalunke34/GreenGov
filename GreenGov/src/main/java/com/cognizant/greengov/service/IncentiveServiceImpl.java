package com.cognizant.greengov.service;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.greengov.dto.IncentiveRequest;
import com.cognizant.greengov.dto.IncentiveResponse;
import com.cognizant.greengov.model.Incentive;
import com.cognizant.greengov.repository.IncentiveRepository;

@Service
public class IncentiveServiceImpl implements IncentiveService {

    @Autowired
    private IncentiveRepository incentiveRepository;



    @Override
    @Transactional
    public IncentiveResponse createIncentive(IncentiveRequest request) {
        // 1. Map Request to Entity
        Incentive incentive = new Incentive();
        incentive.setProgramId(request.getProgramId());
        incentive.setEntityId(request.getEntityId());
        incentive.setAmount(request.getAmount());
        incentive.setDate(LocalDate.now());
        incentive.setStatus("PENDING");

        // 2. Save to Database
        Incentive savedIncentive = incentiveRepository.save(incentive);

        // 3. Map to Response with success message
        return IncentiveResponse.builder()
                .message("Incentive created successfully and is now pending approval.")
                .incentiveId(savedIncentive.getIncentiveId())
                .programId(savedIncentive.getProgramId())
                .entityId(savedIncentive.getEntityId())
                .amount(savedIncentive.getAmount())
                .date(savedIncentive.getDate())
                .status(savedIncentive.getStatus())
                .build();
    }
    
    
    @Override
    public IncentiveResponse getIncentiveById(Long id) {
        Incentive incentive = incentiveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incentive not found with ID: " + id));
        
        return mapToResponse(incentive, "Incentive details retrieved successfully.");
    }

    @Override
    public List<IncentiveResponse> getAllIncentives() {
        return incentiveRepository.findAll().stream()
                .map(incentive -> mapToResponse(incentive, "Incentive record found."))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public IncentiveResponse updateStatus(Long id, String status) {
        Incentive incentive = incentiveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incentive not found with ID: " + id));
        
        String oldStatus = incentive.getStatus();
        incentive.setStatus(status.toUpperCase());
        Incentive updatedIncentive = incentiveRepository.save(incentive);

        return mapToResponse(updatedIncentive, "Status successfully updated from " + oldStatus + " to " + status.toUpperCase() + ".");
    }

    // Helper method to keep code DRY (Don't Repeat Yourself)
    private IncentiveResponse mapToResponse(Incentive incentive, String message) {
        return IncentiveResponse.builder()
                .message(message)
                .incentiveId(incentive.getIncentiveId())
                .programId(incentive.getProgramId())
                .entityId(incentive.getEntityId())
                .amount(incentive.getAmount())
                .date(incentive.getDate())
                .status(incentive.getStatus())
                .build();
    }
}