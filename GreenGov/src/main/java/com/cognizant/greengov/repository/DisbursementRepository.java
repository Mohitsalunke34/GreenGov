package com.cognizant.greengov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.greengov.model.Disbursement;

@Repository
public interface DisbursementRepository extends JpaRepository<Disbursement, Long> {
    // save, findById, and findAll are inherited from JpaRepository automatically.
}