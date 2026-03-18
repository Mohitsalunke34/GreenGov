package com.cognizant.greengov.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sustainability_projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SustainabilityProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
 
    @NotBlank(message = "Title is mandatory")
    @Column(nullable = false, length = 200)
    private String title;
 
    @Column(columnDefinition = "TEXT")
    private String description;
 
    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;
 
    private LocalDate endDate;
 
    @NotNull(message = "Budget is mandatory")
    @DecimalMin(value = "0.0")
    private BigDecimal budget;
 
    @NotBlank(message = "Status is mandatory")
    private String status;
}