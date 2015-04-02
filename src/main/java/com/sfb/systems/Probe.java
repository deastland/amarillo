package com.sfb.systems;

import com.sfb.properties.ProbeArmingType;

public class Probe {

	private int ammo;
	private int availableAmmo;
	
	private ProbeArmingType armingType;
	private int armingTurn;					// The turn in the arming cycle (takes 2 turns to arm)
	private boolean ready;					// True if the probe is ready to fire.
	private boolean functional;				// True if the probe box is not destroyed.
	
	// Create a new probe box with the proper initial values.
	public Probe() {
		ammo = 5;
		availableAmmo = ammo;
		armingType = ProbeArmingType.INFORMATION;
		armingTurn = 0;
		ready = false;
	}
	
	/// OPERATIONS ///
	
	// Set the arming mode of the probe.
	// Also resets arming cycle, assuming you aren't allowed to change mid-stream.
	public void setProbeType(ProbeArmingType armingType) {
		this.armingType = armingType;
		this.armingTurn = 0;
		this.ready = false;
	}
	
	// Performs a turn of arming the weapon.
	// Returns the energy cost to perform the arming.
	// Returns -1 if there is no more ammo.
	public int arm() {
		if (ammo == 0) {
			return -1;
		}
		
		armingTurn++;
		if (armingTurn > 1) {
			ready = true;
		} else {
			ready = false;
		}
		
		if (this.armingType == ProbeArmingType.INFORMATION) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public boolean fire() {
		if (!ready) {
			return false;
		}

		armingTurn = 0;
		ready = false;
		availableAmmo--;
		return true;
	}
	
	/// GETTERS ////
	
	public int getAvailableAmmo() {
		return this.availableAmmo;
	}
	
	public boolean isReady() {
		return this.ready;
	}
	
	public ProbeArmingType getArmingType() {
		return this.armingType;
	}
	
	public int getArmingTurn() {
		return this.armingTurn;
	}
	
	public boolean isFunctional() {
		return functional;
	}
}
