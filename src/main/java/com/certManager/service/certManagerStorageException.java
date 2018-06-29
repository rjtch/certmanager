package com.certManager.service;

public class certManagerStorageException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;

	public certManagerStorageException(String message) {
		super(message);
	}
	
	public certManagerStorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
