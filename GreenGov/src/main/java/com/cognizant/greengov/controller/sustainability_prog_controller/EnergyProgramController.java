package com.cognizant.greengov.controller.sustainability_prog_controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.sustainability_dto.EnergyProgramDTO;
import com.cognizant.greengov.service.sustainability_service.EnergyProgramService;

@RestController
@RequestMapping("/api/programs")
public class EnergyProgramController {

    private final EnergyProgramService service;

    public EnergyProgramController(EnergyProgramService service) {
        this.service = service;
    }

    @GetMapping
    public List<EnergyProgramDTO> getAllPrograms() {
        return service.getAllPrograms();
    }
    @PostMapping("/create")
    public EnergyProgramDTO createProgram(@RequestBody EnergyProgramDTO EnPr) {
    	return service.createprogram(EnPr);
    }
}
