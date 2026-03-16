package com.cognizant.greengov.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisbursementResponse {
    private String message;
    private Long disbursementId;
    private Long incentiveId;
    private Long officerId;
    private LocalDate paymentDate;
    private String status;
}