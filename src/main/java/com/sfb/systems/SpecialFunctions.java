package com.sfb.systems;


public class SpecialFunctions {

	int[] damageControl = {0};			// Array of values representing the DamCon track on the SSD.
	int[] scanner = {0};				// Array of values representing the scanner track on the SSD.
	int[] sensor = {0};					// Array of values representing the sensor track on the SSD.
	int excessDamage = 0;				// Base amount of excess damage on the SSD.

	int availableDamageControl = 0;		// Pointer to the current DamageControl value. Moves with damage/repair.
	int availableScanner = 0;			// Pointer to the current scanner value. Moves with damage/repair.
	int availableSensor = 0;			// Pointer to the current sensor value. Moves with damage/repair.
	int availableExcessDamage = 0;		// Amount of excess damage remaining.
	
	public SpecialFunctions() {}
	
	// Initialize the values for special functions from the SSD
	public void init(int[] damageControlValues, int[] scannerValues, int[] sensorValues, int excessDamageValue) {
		System.arraycopy(damageControlValues, 0, this.damageControl, 0, damageControlValues.length);
		availableDamageControl = 0;
		System.arraycopy(scannerValues, 0, this.scanner, 0, scannerValues.length);
		availableDamageControl = 0;
		System.arraycopy(sensorValues, 0, this.sensor, 0, sensorValues.length);
		availableDamageControl = 0;
		excessDamage = excessDamageValue;
	}
	
	///// FETCH VALUES /////
	public int getDamageControl() {
		return damageControl[availableDamageControl];
	}
	
	public int getScanner() {
		return scanner[availableScanner];
	}
	
	public int getSensor() {
		return sensor[availableSensor];
	}
	
	public int getOriginalExcessDamage() {
		return excessDamage;
	}
	
	public int getExcessDamage() {
		return availableExcessDamage;
	}
	
	
	///// DAMAGE /////
	public boolean damageScanner() {
		// If we are at the last position in the track, no further damage can be done.
		if (availableScanner == this.scanner.length - 1) {
			return false;
		}
		
		// Move the pointer to the next value in the track.
		availableScanner++;
		return true;
	}
	
	public boolean damageSensor() {
		// If we are at the last position in the track, no further damage can be done.
		if (availableSensor == this.sensor.length - 1) {
			return false;
		}
		
		// Move the pointer to the next value in the track.
		availableSensor++;
		return true;
	}
	
	public boolean damageDamCon() {
		// If we are at the last position in the track, no further damage can be done.
		if (availableDamageControl == this.damageControl.length - 1) {
			return false;
		}
		
		// Move the pointer to the next value in the track.
		availableDamageControl++;
		return true;
	}
	
	// Apply excess damage. Return true if ship is still intact.
	// Return false if the ship is destroyed!!!!!
	public boolean damageExcessDamage() {
		if (availableExcessDamage == 0) {
			return false;
		}
		
		availableExcessDamage--;
		return true;
	}
	
	////// REPAIR /////
	public boolean repairDamCon() {
		// If we are at the first position in the track, no repairs can be made.
		if (availableDamageControl == 0) {
			return false;
		}
		
		// Move the pointer to the previous value in the track.
		availableDamageControl--;
		return true;
	}
	
	public boolean repairSensor() {
		// If we are at the first position in the track, no repairs can be made.
		if (availableSensor == 0) {
			return false;
		}
		
		// Move the pointer to the previous value in the track.
		availableSensor--;
		return true;
	}
	
	public boolean repairScanner() {
		// If we are at the first position in the track, no repairs can be made.
		if (availableScanner == 0) {
			return false;
		}
		
		// Move the pointer to the previous value in the track.
		availableScanner--;
		return true;
	}
	
	public boolean repairExcessDamage(int amount) {
		if (availableExcessDamage + amount > excessDamage) {
			return false;
		}
		
		availableExcessDamage += amount;
		return true;
	}
	
}
