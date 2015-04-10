package com.sfb.exceptions;

public class TargetOutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TargetOutOfRangeException() {}
	
	public TargetOutOfRangeException(String message) {
		super(message);
	}
}
