package com.sfb.weapons;

import com.sfb.DiceRoller;
import com.sfb.properties.WeaponArmingType;

public class Photon extends HitOrMissWeapon {

	private final static int[] hitChart = 
		{0,0,5,4,4,3,3,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	private final static int[] overloadHitChart =
		{6,6,5,4,4,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private final static int[] proximityHitChart = 
		{0,0,0,0,0,0,0,0,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
	
	private WeaponArmingType armingType = WeaponArmingType.STANDARD;
	private int armingTurn = 0;
	private double armingEnergy = 0;
	
	/**
	 * @param range The range to the target of the weapon.
	 * 
	 * @return The amount of damage done by the weapon on a hit, otherwise 0.
	 */
	@Override
	public int rollDamage(int range) {
		int damage = 0;
		// Roll to hit.
		DiceRoller diceRoller = new DiceRoller();

		// Based on arming type, calculate damage (0 on a miss).
		switch(armingType) {
		case STANDARD:
			if (diceRoller.rollOneDie() <= hitChart[range]) {
				damage = 8;
			}
			break;
		case OVERLOAD:
			if (diceRoller.rollOneDie() <= overloadHitChart[range]) {
				damage = (int)(armingEnergy * 2);
			}
			break;
		case SPECIAL:
			if (diceRoller.rollOneDie() <= proximityHitChart[range]) {
				damage = 4;
			}
			break;
		default:
			break;
		}
		
		return damage;
	}
	
	/**
	 * 
	 * @return True if the weapon is armed. False otherwise.
	 */
	public boolean isArmed() {

		return armingTurn == 2;
	}
	
	/**
	 * 
	 * @param energy The energy being used to hold the weapon
	 * @return True if the energy is sufficient and the weapon is armed. False otherwise.
	 */
	public boolean hold(int energy) {
		boolean result = false;
		
		if (!isArmed()) {
			return result;
		}
		
		switch(armingType) {
		case STANDARD:
			if (energy >= 1) {
				result = true;
			}
			break;
		case OVERLOAD:
			if (energy == 2) {
				result = true;
			// If excess energy is put into holding, add 
			// it to the total overload torp energy.
			} else if (energy > 2) {
				armingEnergy = armingEnergy + energy - 2;
			}
			break;
		case SPECIAL:
			if (energy >= 1) {
				result = true;
			}
			break;
		default:
			break;
		}
		
		return result;
	}

	public WeaponArmingType getArmingType() {
		return armingType;
	}

	public void setArmingType(WeaponArmingType armingType) {
		this.armingType = armingType;
	}
	
	public boolean setToOverload() {
		// Can't switch from PROXIMITY to OVERLOAD
		if (armingType == WeaponArmingType.SPECIAL) {
			return false;
		}
		
		armingType = WeaponArmingType.OVERLOAD;
		return true;
	}
	
	public boolean setToProximity() {
		if (armingTurn > 0) {
			return false;
		}
		
		armingType = WeaponArmingType.SPECIAL;
		return true;
	}
	
	public boolean setToStandard() {
		if (armingTurn > 0) {
			return false;
		}
		
		armingType = WeaponArmingType.STANDARD;
		return true;
	}

}
