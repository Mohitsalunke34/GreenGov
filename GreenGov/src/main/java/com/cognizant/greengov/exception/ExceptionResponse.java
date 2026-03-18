package com.cognizant.greengov.exception;

import java.time.LocalDate;

public class ExceptionResponse {
	private String errorMessage;
	private LocalDate dateOfException;
	private int stateCode;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDate getDateOfException() {
		return dateOfException;
	}
	public void setDateOfException(LocalDate dateOfException) {
		this.dateOfException = dateOfException;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public ExceptionResponse() {
		
	}
	public ExceptionResponse(String errorMessage, LocalDate dateOfException, int stateCode) {
		super();
		this.errorMessage = errorMessage;
		this.dateOfException = dateOfException;
		this.stateCode = stateCode;
	}
	
	
}
