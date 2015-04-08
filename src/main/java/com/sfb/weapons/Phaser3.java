package com.sfb.weapons;

import com.sfb.utilities.DiceRoller;

/**
 * Type-3 Defensive Phaser
 * 
 * @author Daniel Eastland
 *
 */
public class Phaser3 extends VariableDamageWeapon {

	// The damage chart for this weapon.
	private static final int[][] hitChart = {
			// Ranges 0-15
			{4,4,4,3,1,1,1,1,1,1,1,1,1,1,1,1}, // Roll 1
			{4,4,4,2,1,1,1,1,1,0,0,0,0,0,0,0}, // Roll 2
			{4,4,4,1,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 3
			{4,4,3,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 4
			{4,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 5
			{3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 6
			};
	
	public Phaser3() {
		setDacHitLocaiton("phaser");
		setName("Phaser-3");
	}

	/**
	 * @param range The range from the shooter to the target
	 * 
	 * @return The damage done by the weapon at that range.
	 */
	@Override
	public int fire(int range) {
		// Weapon can not hit anything past range 15
		if (range > 15) {
			return -1;
		}
		
		DiceRoller diceRoller = new DiceRoller();
		int roll = diceRoller.rollOneDie();
		
		// Return the value that matches the die roll and the range.
		return hitChart[roll - 1][range];
	}

}
