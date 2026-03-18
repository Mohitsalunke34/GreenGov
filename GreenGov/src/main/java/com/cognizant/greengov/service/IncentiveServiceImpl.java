package com.cognizant.greengov.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
    private ModelMapper modelMapper; // Inject the bean

    @Override
    @Transactional
    public IncentiveResponse createIncentive(IncentiveRequest request) {
       
        Incentive incentive = modelMapper.map(request, Incentive.class);
        
        incentive.setDate(LocalDate.now());
        incentive.setStatus("PENDING");

        Incentive saved = incentiveRepository.save(incentive);
        return mapToResponse(saved, "Incentive created successfully.");
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
                .map(i -> mapToResponse(i, "Record found."))
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

        return mapToResponse(updatedIncentive, 
            "Status updated from " + oldStatus + " to " + status.toUpperCase() + ".");
    }


    private IncentiveResponse mapToResponse(Incentive incentive, String message) {
       
        IncentiveResponse response = modelMapper.map(incentive, IncentiveResponse.class);
        
      
        response.setMessage(message);
        
        return response;
    }

//	@Override
//	@Transactional
//	public IncentiveResponse createIncentive(IncentiveRequest request) {
//		Incentive incentive = new Incentive();
//		
//		incentive.setProgramId(request.getProgramId());
//		incentive.setEntityId(request.getEntityId());
//		incentive.setAmount(request.getAmount());
//		incentive.setDate(LocalDate.now());
//		incentive.setStatus("PENDING");
//
//		return mapToResponse(incentiveRepository.save(incentive), "Incentive created successfully.");
//	}
//
//	@Override
//
//	public IncentiveResponse getIncentiveById(Long id) {
//
//		Incentive incentive = incentiveRepository.findById(id)
//
//				.orElseThrow(() -> new RuntimeException("Incentive not found with ID: " + id));
//
//		return mapToResponse(incentive, "Incentive details retrieved successfully.");
//
//	}
//
//	@Override
//	public List<IncentiveResponse> getAllIncentives() {
//		return incentiveRepository.findAll().stream().map(i -> mapToResponse(i, "Record found."))
//				.collect(Collectors.toList());
//	}
//
//	@Override
//	@Transactional
//	public IncentiveResponse updateStatus(Long id, String status) {
//
//		Incentive incentive = incentiveRepository.findById(id)
//
//				.orElseThrow(() -> new RuntimeException("Incentive not found with ID: " + id));
//
//		String oldStatus = incentive.getStatus();
//
//		incentive.setStatus(status.toUpperCase());
//
//		Incentive updatedIncentive = incentiveRepository.save(incentive);
//
//		return mapToResponse(updatedIncentive,
//				"Status successfully updated from " + oldStatus + " to " + status.toUpperCase() + ".");
//
//	}
//
//	private IncentiveResponse mapToResponse(Incentive incentive, String message) {
//		return IncentiveResponse.builder()
//				.message(message)
//				.incentiveId(incentive.getIncentiveId())
//				.programId(incentive.getProgramId())
//				.entityId(incentive.getEntityId())
//				.amount(incentive.getAmount())
//				.date(incentive.getDate())
//				.status(incentive.getStatus())
//				.build();
//	}
}