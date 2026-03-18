package com.cognizant.greengov.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "disbursements")
@Data
public class Disbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disbursementId;

//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "incentive_id") 
//    private Incentive incentive; 
    private Long incentiveId;
    
//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "officer_id") 
//    private User officer; 
    
    private Long officerId;
    private LocalDate paymentDate;
    private String status;
}