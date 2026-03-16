package com.cognizant.greengov.dto;



import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncentiveResponse {
    private String message; // To provide feedback in Postman
    private Long incentiveId;
    private Long programId;
    private Long entityId;
    private Double amount;
    private LocalDate date;
    private String status;
}