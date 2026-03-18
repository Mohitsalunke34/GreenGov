package com.cognizant.greengov.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "incentives")
@Data
public class Incentive {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incentiveId;
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "application_id") 
//	private ProgramApplication application; // optional but recommended 
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "program_id") 
//	private EnergyProgram program;
	
    private Long programId;
    
//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "entity_id") 
//    private CitizenBusiness entity; 
    
    private Long entityId;
    private Double amount;
    private LocalDate date;
    private String status;
    
//    @OneToMany(mappedBy = "incentive") 
//    private List<Disbursement> disbursements = new ArrayList<>(); 
}