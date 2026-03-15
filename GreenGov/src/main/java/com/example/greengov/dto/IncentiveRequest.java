package com.example.greengov.dto;


import lombok.Data;

@Data
public class IncentiveRequest {
    private Long programId;
    private Long entityId;
    private Double amount;
}