package com.sfb.weapons;

import com.sfb.utilities.DiceRoller;

public class Phaser1 extends VariableDamageWeapon {

	// The damage chart for this weapon.
	private static final int[][] hitChart = {
			// Ranges 0-75
			{9,8,7,6,5,5,4,4,4,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, // Roll 1
			{8,7,6,5,5,4,3,3,3,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 2
			{7,5,5,4,4,4,3,3,3,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 3
			{6,4,4,4,4,3,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 4
			{5,4,4,4,3,3,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, // Roll 5
			{4,4,3,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}  // Roll 6
			};
	
	/**
	 * Constructor
	 */
	public Phaser1() {
		setDacHitLocaiton("phaser");
		setName("Phaser-1");
	}

	/**
	 * @param range The range from the shooter to the target
	 * 
	 * @return The damage done by the weapon at that range.
	 */
	@Override
	public int fire(int range) {
		// Can not damage targets beyond range 75
		if (range > 75) {
			return -1;
		}
		// Roll the 1d6 to determing damage
		DiceRoller diceRoller = new DiceRoller();
		int roll = diceRoller.rollOneDie();
		
		// Return the value that matches the die roll and the range.
		return hitChart[roll - 1][range];
	}

}
