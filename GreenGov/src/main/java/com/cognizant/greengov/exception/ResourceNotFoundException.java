package com.cognizant.greengov.exception;

// Fixed: Added 'extends RuntimeException' and the constructor
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}