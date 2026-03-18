package com.cognizant.greengov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.greengov.model.Resources;


public interface ResourceRepository extends JpaRepository<Resources, Long> {
	
}
