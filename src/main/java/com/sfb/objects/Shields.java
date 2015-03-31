package com.sfb.objects;

public class Shields {

	// The strength of the various shields.
	private int[] shieldValues = new int[] {0,0,0,0,0,0};
	private int[] currentShieldValues = new int[] {0,0,0,0,0,0};
	
	// later include logic for shield that is 'lowered'
	private boolean[] shieldActive = new boolean[] {true, true, true, true, true, true};
	
	// reinforcement
	private int[] specificReinforcement = new int[] {0,0,0,0,0,0};
	private int generalReinforcement = 0;
	
	// Returns the current strength of the specified shield, including specific reinforcement.
	public int getShieldStrength(int shieldNumber) {
		return currentShieldValues[shieldNumber - 1] + specificReinforcement[shieldNumber - 1];
	}
	
	
	////////////////////////////////////
	//
	// Utilities
	//
	////////////////////////////////////

	// Initialize shield values. If a proper array isn't passed in,
	// return false. Otherwise return true.
	public boolean initialize(int[] values) {
		if (values.length != 6) {
			return false;
		}
		
		System.arraycopy(values, 0, shieldValues, 0, values.length);
		System.arraycopy(values, 0, currentShieldValues, 0, values.length);
		return true;
	}
	
	// At the end of the turn, all reinforcement is lost
	public void cleanUp() {
		specificReinforcement = new int[] {0,0,0,0,0,0};
		generalReinforcement = 0;
	}
	
	// If a shield has a positive strength value, add the reinforcement and return true.
	// Otherwise return false.
	public boolean reinforceShield(int shieldNumber, int amount) {
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
	
}
