package com.cognizant.greengov.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "disbursements")
@Data
public class Disbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disbursementId;

    private Long incentiveId;
    private Long officerId; // The Environmental Officer authorizing the payment
    private LocalDate paymentDate;
    private String status; // e.g., COMPLETED, FAILED
}