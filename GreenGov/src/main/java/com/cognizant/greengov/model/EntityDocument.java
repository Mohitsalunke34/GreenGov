package com.cognizant.greengov.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.cognizant.greengov.model.Enums.DocumentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "entity_document")
@Data
public class EntityDocument {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private CitizenBusiness entity;
 
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
 
    @Column(nullable = false, length = 500)
    private String fileUri;
 
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime uploadedDate;
 
    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;
}