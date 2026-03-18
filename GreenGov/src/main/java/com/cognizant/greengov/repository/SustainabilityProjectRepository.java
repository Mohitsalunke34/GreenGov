package com.cognizant.greengov.repository;

import com.cognizant.greengov.model.SustainabilityProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SustainabilityProjectRepository extends JpaRepository<SustainabilityProject, Long> {
    Optional<SustainabilityProject> findByTitle(String title); // For duplicate validation
}