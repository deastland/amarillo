package com.sfb.objects;


/**
 * This object represents an base shuttle.
 * @author Daniel Eastland
 *
 */
public abstract class Shuttle extends Unit {
	
	private int maxSpeed 		= 6;	// The maximum speed this shuttle can go
	private int currentSpeed 	= 6;	// The speed the shuttle is currently travelling
	private int hull			= 6;	// The maximum hull value of the shuttle	
	private int currentHull		= 6;	// The number of undamaged hull remaining.
	
	public Shuttle() {
		
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public int getCurrentSpeed() {
		return currentSpeed;
	}
	
	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	
	public int getHull() {
		return hull;
	}
	
	public void setHull(int maxHull) {
		this.hull = maxHull;
	}
	
	public int getCurrentHull() {
		return currentHull;
	}
	
	public void setCurrentHull(int currentHull) {
		this.currentHull = currentHull;
	}
	
	
}
