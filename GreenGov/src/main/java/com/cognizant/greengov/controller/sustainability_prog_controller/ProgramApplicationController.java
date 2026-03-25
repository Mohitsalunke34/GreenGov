package com.cognizant.greengov.controller.sustainability_prog_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.sustainability_dto.ProgramApplicationRequestDTO;
import com.cognizant.greengov.service.sustainability_service.ProgramApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class ProgramApplicationController {

	private final ProgramApplicationService service;

	public ProgramApplicationController(ProgramApplicationService service) {
		this.service = service;
	}
	//apply to a Program
	@PostMapping
	public ResponseEntity<Void> apply(@RequestParam Long participantId,
			@RequestBody @Valid ProgramApplicationRequestDTO dto) {

		service.apply(participantId, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}