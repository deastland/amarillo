package com.sfb.systemgroups;

import java.util.Map;

public class Shields implements Systems {

	// The strength of the various shields.
	private int[] shieldValues = new int[] {0,0,0,0,0,0};
	private int[] currentShieldValues = new int[] {0,0,0,0,0,0};
	
	// later include logic for shield that is 'lowered'
	private boolean[] shieldActive = new boolean[] {true, true, true, true, true, true};
	
	// reinforcement
	private int[] specificReinforcement = new int[] {0,0,0,0,0,0};
	private int generalReinforcement = 0;
	
	public Shields() {}
	
	// Returns the current strength of the specified shield, including specific reinforcement.
	public int getShieldStrength(int shieldNumber) {
		return currentShieldValues[shieldNumber - 1] + specificReinforcement[shieldNumber - 1];
	}
	
	// Set the value of a current shield to a fixed number.
	// NOTE: Not sure if this is a method that should be exposed.
	public boolean setShieldValue(int shieldNumber, int value) {
		if (value > shieldValues[shieldNumber - 1]) {
			return false;
		}
		
		currentShieldValues[shieldNumber - 1] = value;
		return true;
	}
	
	////////////////////////////////////
	//
	// Utilities
	//
	////////////////////////////////////

	// Initialize shield values.
	@Override
	public void init(Map<String, Object> values) {
		currentShieldValues[0] = shieldValues[0] = values.get("shield1") == null ? 0 : (Integer)values.get("shield1");
		currentShieldValues[1] = shieldValues[1] = values.get("shield2") == null ? 0 : (Integer)values.get("shield2");
		currentShieldValues[2] = shieldValues[2] = values.get("shield3") == null ? 0 : (Integer)values.get("shield3");
		currentShieldValues[3] = shieldValues[3] = values.get("shield4") == null ? 0 : (Integer)values.get("shield4");
		currentShieldValues[4] = shieldValues[4] = values.get("shield5") == null ? 0 : (Integer)values.get("shield5");
		currentShieldValues[5] = shieldValues[5] = values.get("shield6") == null ? 0 : (Integer)values.get("shield6");
		
		// All shields start active
		shieldActive = new boolean[] {true, true, true, true, true, true};
	}
	
	// At the end of the turn, all reinforcement is lost
	public void cleanUp() {
		specificReinforcement = new int[] {0,0,0,0,0,0};
		generalReinforcement = 0;
	}
	
	// If a shield has a positive strength value, add the reinforcement and return true.
	// Otherwise return false.
	public boolean reinforceShield(int shieldNumber, int amount) {
		if (!shieldActive[shieldNumber - 1]) {
			return false;
		}
			
		if (currentShieldValues[shieldNumber - 1]  == 0) {
			return false;
		}
		
		// Add the new amount to the current reinforcement for that shield.
		int currentReinforcement = specificReinforcement[shieldNumber - 1];
		specificReinforcement[shieldNumber - 1] = currentReinforcement + amount;
		
		return true;
	}
	
	// Add general reinforcement value.
	public void addGeneralRenforcement(int amount) {
		generalReinforcement += amount;
	}
	
	// Apply damage to the shield facing. If the shield is destroyed and there
	// are still damage points remaining, return the remainder. Otherwise return 0.
	public int damageShield(int shieldNumber, int amount) {
		int remainingDamage = amount;
		
		// Remove general reinforcement first. If reinforcement eliminates all
		// damage, return 0.
		if (remainingDamage > generalReinforcement) {
			remainingDamage -= generalReinforcement;
			generalReinforcement = 0;
		} else {
			generalReinforcement -= remainingDamage;
			remainingDamage = 0;
		}

		// Remove specific reinforcement. If reinforcement eliminates remaining 
		// damage, return 0.
		if (remainingDamage > specificReinforcement[shieldNumber - 1]) {
			remainingDamage -= specificReinforcement[shieldNumber - 1];
			specificReinforcement[shieldNumber - 1] = 0;
		} else {
			specificReinforcement[shieldNumber - 1] -= remainingDamage;
			remainingDamage = 0;
		}
		
		// Remove shield boxes from the facing shield. If this stops remaining damage,
		// return 0. Otherwise return the remaining damage.
		if (remainingDamage > currentShieldValues[shieldNumber - 1]) {
			remainingDamage -= currentShieldValues[shieldNumber - 1];
			currentShieldValues[shieldNumber - 1] = 0;
		} else {
			currentShieldValues[shieldNumber -1] -= remainingDamage;
			remainingDamage = 0;
		}
		
		return remainingDamage;
	}
	
	// Repair a number of shield boxes. If this would exceed the maximum
	// shield value, return false. Otherwise true.
	public boolean repairShield(int shieldNumber, int amount) {
		int currentValue = currentShieldValues[shieldNumber - 1];
		int maxValue = shieldValues[shieldNumber - 1];
		
		if (currentValue + amount > maxValue) {
			return false;
		}
		
		currentShieldValues[shieldNumber - 1] += amount;
		return true;
	}

	@Override
	public int getOriginalTotalBoxes() {
		int totalCount = 0;
		for (int i=0; i < shieldValues.length; i++) {
			totalCount += shieldValues[i];
		}
		
		return totalCount;
	}

	@Override
	public int getTotalBoxes() {
		int totalCount = 0;
		for (int i=0; i < currentShieldValues.length; i++) {
			totalCount += currentShieldValues[i];
		}
		
		return totalCount;
	}
	
}
