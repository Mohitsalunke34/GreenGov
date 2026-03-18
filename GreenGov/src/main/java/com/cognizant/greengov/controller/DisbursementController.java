package com.cognizant.greengov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.DisbursementRequest;
import com.cognizant.greengov.dto.DisbursementResponse;
import com.cognizant.greengov.service.DisbursementService;

@RestController
@RequestMapping("/api/disbursements")
public class DisbursementController {

    @Autowired
    private DisbursementService disbursementService;


    @PostMapping("create")
    public ResponseEntity<DisbursementResponse> createDisbursement(@RequestBody DisbursementRequest request) {
        DisbursementResponse response = disbursementService.processDisbursement(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    
    @GetMapping("fetchById/{id}")
    public ResponseEntity<DisbursementResponse> getDisbursement(@PathVariable Long id) {
        return ResponseEntity.ok(disbursementService.getDisbursementById(id));
    }

   
    @GetMapping("fetchAll")
    public ResponseEntity<List<DisbursementResponse>> listAllDisbursements() {
        return ResponseEntity.ok(disbursementService.getAllDisbursements());
    }
}