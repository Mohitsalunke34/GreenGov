package com.cognizant.greengov.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.IncentiveRequest;
import com.cognizant.greengov.dto.IncentiveResponse;
import com.cognizant.greengov.service.IncentiveService;

@RestController
@RequestMapping("/api/incentives")
public class IncentiveController {

    @Autowired
    private IncentiveService incentiveService;

    @PostMapping("/save")
    public ResponseEntity<IncentiveResponse> createIncentive(@RequestBody IncentiveRequest request) {
        return ResponseEntity.ok(incentiveService.createIncentive(request));
    }
    
    @GetMapping("/fetchById/{id}")
    public ResponseEntity<IncentiveResponse> getIncentive(@PathVariable Long id) {
        return ResponseEntity.ok(incentiveService.getIncentiveById(id));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<IncentiveResponse>> listAll() {
        return ResponseEntity.ok(incentiveService.getAllIncentives());
    }
    
    @PatchMapping("/updateStatus/{id}/status")
    public ResponseEntity<IncentiveResponse> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(incentiveService.updateStatus(id, status));
    }
    
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<IncentiveResponse> deleteIncentive(@RequestParam("id")){
//    	
//    }
    
}