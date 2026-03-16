package com.cognizant.greengov.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "incentives")
@Data
public class Incentive {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incentiveId;

    private Long programId;
    private Long entityId;
    private Double amount;
    private LocalDate date;
    private String status; // e.g., PENDING, APPROVED, DISBURSED, REJECTED
}