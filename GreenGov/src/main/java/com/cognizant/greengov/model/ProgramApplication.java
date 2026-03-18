package com.cognizant.greengov.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "program_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId; // [cite: 52, 105]
 
    @NotNull(message = "Entity ID is required")
    private Long entityId; // The Citizen or Business ID [cite: 52, 105]
 
    @NotNull(message = "Program ID is required")
    private Long programId; // The Energy Program ID [cite: 52, 105]
 
    @NotNull(message = "Submission date is required")
    private LocalDate submittedDate; // [cite: 52, 105]
 
    @NotBlank(message = "Status is required")
    private String status; // e.g., PENDING, VALIDATED, REJECTED [cite: 53, 105]
}