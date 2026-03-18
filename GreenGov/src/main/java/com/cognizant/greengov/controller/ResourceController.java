package com.cognizant.greengov.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.greengov.dto.ResourceDTO;
import com.cognizant.greengov.dto.ResourceStatusDTO;
import com.cognizant.greengov.model.Resources;
import com.cognizant.greengov.service.ResourceServiceInterface;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourceController {

    private final ResourceServiceInterface service;

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("This is Resource Management Module");
    }

    @PostMapping("/create-resource")
    public ResponseEntity<Resources> addResource(@RequestBody ResourceDTO resource) {
        // Service returns Resources object, so we wrap it in OK
        return ResponseEntity.ok(service.addResource(resource));
    }

    @PutMapping("/update-resource/{id}")
    public ResponseEntity<Resources> updateResource(@PathVariable("id") long resourceId,
            @RequestBody ResourceDTO resource) {
        return ResponseEntity.ok(service.updateResource(resourceId, resource));
    }

    @GetMapping("/get-resource/{id}")
    public ResponseEntity<Resources> getResource(@PathVariable("id") long resourceId) {
        return ResponseEntity.ok(service.getResource(resourceId));
    }

    @DeleteMapping("/delete-resource/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable("id") long resourceId) {
        service.deleteResource(resourceId);
        return ResponseEntity.noContent().build(); 
    }

    @GetMapping("/get-all-resources")
    public ResponseEntity<List<Resources>> getAllResources() {
        return ResponseEntity.ok(service.getAllResources());
    }

    @PatchMapping("/update-resource-status")
    public ResponseEntity<Resources> updateResourceStatus(@RequestBody ResourceStatusDTO res) {
        return ResponseEntity.ok(service.updateStatus(res));
    }
}