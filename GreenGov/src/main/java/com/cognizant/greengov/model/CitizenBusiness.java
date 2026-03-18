package com.cognizant.greengov.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "citizen_business")
@Data
public class CitizenBusiness {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, length = 200)
    private String legalName;
 
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
 
    @Column(columnDefinition = "TEXT")
    private String address;
 
    @Column(columnDefinition = "JSON")
    private String contactInfo;
 
    @Enumerated(EnumType.STRING)
    private VerificationStatus status;
 
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntityDocument> documents = new ArrayList<>();
}