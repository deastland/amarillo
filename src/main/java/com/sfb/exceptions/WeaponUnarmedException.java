package com.sfb.exceptions;

public class WeaponUnarmedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WeaponUnarmedException() {}
	
	public WeaponUnarmedException(String message) {
		super(message);
	}
}
