package com.cognizant.greengov.service.sustainability_service;

import com.cognizant.greengov.dto.sustainability_dto.ProgramApplicationRequestDTO;

public interface ProgramApplicationService {

    void apply(Long participantId, ProgramApplicationRequestDTO dto);
}