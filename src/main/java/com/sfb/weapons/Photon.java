package com.sfb.weapons;

import com.sfb.properties.WeaponArmingType;
import com.sfb.utilities.DiceRoller;

public class Photon extends HitOrMissWeapon implements HeavyWeapon {

	// STANDARD
	private final static int[] hitChart = 
		{0,0,5,4,4,3,3,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	// OVERLOAD
	private final static int[] overloadHitChart =
		{6,6,5,4,4,3,3,3,3};
	// SPECIAL
	private final static int[] proximityHitChart = 
		{0,0,0,0,0,0,0,0,0,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
	
	private WeaponArmingType armingType = WeaponArmingType.STANDARD;
	private int armingTurn = 0;											// Number of turns the weapon has been arming.
	private double armingEnergy = 0;									// Amount of total energy stored in the weapon.
	private boolean armed = false;										// True if the weapon is armed and ready to fire.
	
	public Photon() {
		setDacHitLocaiton("torp");
	}
	
	/**
	 * @param range The range to the target of the weapon.
	 * 
	 * @return The amount of damage done by the weapon (0 on a miss).
	 * Returns -1 if the weapon can not be fired at the target due
	 * to range or arming restrictions.
	 */
	@Override
	public int fire(int range) {
		if (!isArmed()) {
			return -1;
		}

		int damage = 0;
		// Roll to hit.
		DiceRoller diceRoller = new DiceRoller();

		// Based on arming type, calculate damage (0 on a miss).
		switch(armingType) {
		case STANDARD:
			// Can't fire at targets below range 2.
			if (range < 2) {
				return -1;
			}
			if (diceRoller.rollOneDie() <= hitChart[range]) {
				damage = 8;
			}
			break;
		case OVERLOAD:
			// Can't fire at targets above range 8.
			if (range > 8) {
				return -1;
			}
			if (diceRoller.rollOneDie() <= overloadHitChart[range]) {
				damage = (int)(armingEnergy * 2);
			}
			break;
		case SPECIAL:
			// Can't fire at targets below range 9.
			if (range < 9) {
				return -1;
			}
			if (diceRoller.rollOneDie() <= proximityHitChart[range]) {
				damage = 4;
			}
			break;
		default:
			break;
		}
		
		// Once fired, the weapon is no longer armed.
		armed = false;
		armingEnergy = 0;
		
		return damage;
	}
	
	/**
	 * 
	 * @return True if the weapon is armed. False otherwise.
	 */
	@Override
	public boolean isArmed() {

		return armed;
	}
	
	@Override
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

	@Override
	public boolean arm(int energy) {
		boolean okayToArm= false;

		// If the photon has already had 2 turns of arming,
		// no more arming can be done. Exit with false.
		if (isArmed()) {
			return false;
		}
		
		// Check what the arming type is for the weapon.
		// Apply the energy and increment the arming turn if it matches the profile.
		switch(armingType) {
		case STANDARD:
			if (energy == 2) {
				armingEnergy += energy;
				armingTurn++;
				okayToArm = true;
			} else {
				okayToArm = false;
			}
			break;
		case OVERLOAD:
			if (energy >= 2 && energy <= 4) {
				armingEnergy += energy;
				armingTurn++;
				okayToArm = true;
			} else {
				okayToArm = false;
			}
			break;
		case SPECIAL:
			if (energy == 2) {
				armingEnergy += energy;
				armingTurn++;
				okayToArm = true;
			} else {
				okayToArm = false;
			}
			break;
		default:
			break;
		}
		
		if (okayToArm && armingTurn == 2) {
			armed = true;
		}
		
		return okayToArm;
		
	}
	
//	/**
//	 * 
//	 * Compound method to allow setting a weapon arming type and
//	 * begin arming it.
//	 * 
//	 * @param armingType Set the weapon to this armingType
//	 * @param energy Apply this energy to arm the weapon.
//	 * @return True if this is a legal arming request, false otherwise.
//	 */
//	public boolean arm(WeaponArmingType armingType, int energy) {
//		if ()
//	}

	@Override
	public WeaponArmingType getArmingType() {
		return armingType;
	}

	@Override
	public boolean setOverload() {
		// Can't switch from PROXIMITY to OVERLOAD. But you can 
		// switch from STANDARD to OVERLOAD.
		if (armingType == WeaponArmingType.SPECIAL) {
			return false;
		}
		
		// Set the arming type.
		armingType = WeaponArmingType.OVERLOAD;
		return true;
	}
	
	/**
	 * Set the photons to PROXIMITY which will do half damage but is much more likely to hit at long range.
	 * Proximity photons can not be used under range 9.
	 * 
	 * @return True if the weapon is in a valid state to be proximity armed, false otherwise.
	 */
	public boolean setProximity() {
		return setSpecial();
	}
	
	@Override
	public boolean setStandard() {
		// If arming type is already STANDARD, no harm no foul.
		if (armingType == WeaponArmingType.STANDARD) {
			return true;
		}
		
		// If arming has already commenced, it is too late
		// to change back to standard. Instead, go reset().
		if (armingTurn > 0) {
			return false;
		}
		
		// Set the arming type.
		armingType = WeaponArmingType.STANDARD;
		return true;
	}
	
	@Override
	public boolean setSpecial() {
		
		// If arming type is already SPECIAL, no harm no foul.
		if (armingType == WeaponArmingType.SPECIAL) {
			return true;
		}
		
		// If arming has already commenced, it is too late
		// to change back to standard.
		if (armingTurn > 0) {
			return false;
		}
		
		armingType = WeaponArmingType.SPECIAL;
		return true;
		
	}

	@Override
	public int getArmingTurn() {
		return armingTurn;
	}
	
	@Override
	public void reset() {
		armingType = WeaponArmingType.STANDARD;
		armingTurn = 0;
		armingEnergy = 0;
		armed = false;
	}
	
	/**
	 * Find out how much energy with which the weapon is currently armed.
	 * @return The amount of arming energy in the weapon.
	 */
	public double getArmingEnergy() {
		return this.armingEnergy;
	}

	@Override
	public int energyToArm() {
		int energyRequired = 0;
		
		switch(armingType) {
		case STANDARD:
			energyRequired = 2;
			break;
		case SPECIAL:
			energyRequired = 2;
			break;
		case OVERLOAD:
			energyRequired = 4;	// Later, this may change to reflect advanced Photon arming rules
			break;
		default:
			break;
		}
		
		return energyRequired;
	}

}
