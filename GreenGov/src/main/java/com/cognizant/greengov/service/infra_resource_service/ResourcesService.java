//Tracks funds / materials
//Linked to projects
package com.cognizant.greengov.service.infra_resource_service;

import com.cognizant.greengov.dto.infra_resource_dto.ResourcesCreateRequestDTO;
import com.cognizant.greengov.dto.infra_resource_dto.ResourcesResponseDTO;

public interface ResourcesService {

    ResourcesResponseDTO allocateResource(ResourcesCreateRequestDTO dto);
}
