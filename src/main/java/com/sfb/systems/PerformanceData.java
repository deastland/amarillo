package com.sfb.systems;

public class PerformanceData {

	// Performance statistics for this spaceframe.
	
	// Base values
	double movementCost = 0;
	boolean nimble = false;	// Nimble ships can do cheap EM
	int breakdownChance = 0;	
	int[] turnMode       = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int bonusHets = 0;	// Number of times this ship get's a -2 to the HET breakdown roll.
	int shieldCost = 0;	// Cost to have shields active.
	
	// Derived values
	double hetCost = 0;
	double erraticCost = 0;
}
