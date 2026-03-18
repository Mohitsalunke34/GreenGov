package com.cognizant.greengov.service;

import java.util.List;

import com.cognizant.greengov.dto.ResourceDTO;
import com.cognizant.greengov.dto.ResourceStatusDTO;
import com.cognizant.greengov.model.Resources;

public interface ResourceServiceInterface {

	public abstract Resources addResource(ResourceDTO res);

	public abstract Resources updateResource(long resourceId ,ResourceDTO res);

	public abstract Resources getResource(long resouceId);

	public abstract void deleteResource(long resourceId);

	public abstract List<Resources> getAllResources();
	
	public abstract Resources updateStatus(ResourceStatusDTO res);
	
}
