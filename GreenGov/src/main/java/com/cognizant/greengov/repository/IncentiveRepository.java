package com.cognizant.greengov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.greengov.model.Incentive;

@Repository
public interface IncentiveRepository extends JpaRepository<Incentive, Long> {
    List<Incentive> findByEntityId(Long entityId);
    List<Incentive> findByStatus(String status);
}