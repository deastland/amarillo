package com.sfb.weapons;

import com.sfb.exceptions.TargetOutOfRangeException;
import com.sfb.exceptions.WeaponUnarmedException;
import com.sfb.properties.WeaponArmingType;
import com.sfb.utilities.DiceRoller;

public class Photon extends HitOrMissWeapon implements DirectFire, HeavyWeapon {

	// Hit Charts are the chance (on a d6) that the weapon will hit at a given range.
	// Array index is the range. For example hitChart[3] will return the chance the weapon will hit at range 3.
	// STANDARD
	private final static int[] hitChart = 
		{0,0,5,4,4,3,3,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	// OVERLOAD
	private final static int[] overloadHitChart =
		{6,6,5,4,4,3,3,3,3};
	// SPECIAL
	private final static int[] proximityHitChart = 
		{0,0,0,0,0,0,0,0,0,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
	
	private WeaponArmingType armingType = WeaponArmingType.STANDARD;	// By default, photons are armed normally.
	private int armingTurn = 0;											// Number of turns the weapon has been arming.
	private double armingEnergy = 0;									// Amount of total energy stored in the weapon.
	private boolean armed = false;										// True if the weapon is armed and ready to fire.
	private boolean held  = false;										// True if the weapon is in 'hold' mode.
	//TODO: Figure out logic for holding weapon and losing it if you do not hold it.
	
	/**
	 * Constructor for a new Photon object.
	 */
	public Photon() {
		setDacHitLocaiton("torp");
		setName("Photon");
		reset();
	}
	
	/**
	 * @param range The range to the target of the weapon.
	 * 
	 * @return The amount of damage done by the weapon (0 on a miss).
	 * Returns -1 if the weapon can not be fired at the target due
	 * to range or arming restrictions.
	 * @throws WeaponUnarmedException 
	 * @throws TargetOutOfRangeException 
	 */
	@Override
	public int fire(int range) throws WeaponUnarmedException, TargetOutOfRangeException {
		// If the weapon isn't armed, it can't fire.
		if (!isArmed()) {
			throw new WeaponUnarmedException("Weapon is unarmed.");
		}
		
		// If the weapon is out of range, it can't fire.
		if (range > getMaxRange() || range < getMinRange()) {
			throw new TargetOutOfRangeException("Target not in weapon range.");
		}
		
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
			// Overloaded photons can't fire at targets above range 8.
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
		
		// Once fired, the weapon is no longer armed.
		reset();
		
		registerFire();
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
	public boolean hold(int energy) throws WeaponUnarmedException {
		boolean result = false;
		
		if (!isArmed()) {
			throw new WeaponUnarmedException("Weapon is not armed.");
		}
		
		switch(armingType) {
		case STANDARD:
			if (energy == 1) {
				result = true;
			}
			break;
		case OVERLOAD:
			if (energy == 2) {
				result = true;
			// If excess energy is put into holding, add 
			// it to the total overload torp energy.
			// This allows gradual arming of overloaded photons.
			} else if (energy > 2) {
				armingEnergy = armingEnergy + energy - 2;
				result = true;
			}
			break;
		case SPECIAL:
			if (energy == 1) {
				result = true;
			}
			break;
		default:
			break;
		}
		
		held = result;
		return result;
	}

	@Override
	public boolean arm(int energy) {
		boolean okayToArm= false;

		// If the photon is already armed then
		// no more arming can be done. Exit with false.
		if (isArmed()) {
			return false;
		}
		
		// Check what the arming type is for the weapon.
		// Apply the energy and increment the arming turn if it matches the profile.
		switch(armingType) {
		// Standard photons (2 energy per turn)
		case STANDARD:
			if (energy == 2) {
				armingEnergy += energy;
				armingTurn++;
				okayToArm = true;
			} else {
				okayToArm = false;
			}
			break;
		// Overloaded photons (from 2 to 4 energy per turn)
		case OVERLOAD:
			if (energy >= 2 && energy <= 4) {
				armingEnergy += energy;
				armingTurn++;
				okayToArm = true;
			} else {
				okayToArm = false;
			}
			break;
		// Proximity photons (2 energy per turn)
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
		
		// If everything worked out and this is the second turn
		// of arming, mark the weapon as armed.
		if (okayToArm && armingTurn == 2) {
			armed = true;
		}
		
		// Return success/failure of the attempted arming.
		return okayToArm;
		
	}
	
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
		setMinRange(0);
		setMaxRange(8);
		return true;
	}
	
	/**
	 * Set the photons to proximity (arming type SPECIAL) which will do half damage but is 
	 * much more likely to hit at long range. Proximity photons can not be used under range 9.
	 * 
	 * @return True if the weapon is in a valid state to be proximity armed, false otherwise.
	 */
	public boolean setProximity() {
		return setSpecial();
	}
	
	@Override
	public boolean setStandard() {
		// If arming has already commenced, it is too late
		// to change back to standard. Instead, try reset().
		if (armingTurn > 0) {
			return false;
		}
		
		// Set the arming type.
		armingType = WeaponArmingType.STANDARD;
		setMinRange(2);
		setMaxRange(30);
		return true;
	}
	
	@Override
	public boolean setSpecial() {
		// If arming has already commenced, it is too late
		// to change back to standard.
		if (armingTurn > 0) {
			return false;
		}
		
		armingType = WeaponArmingType.SPECIAL;
		setMinRange(9);
		setMaxRange(30);
		return true;
		
	}

	@Override
	public int getArmingTurn() {
		return armingTurn;
	}
	
	@Override
	public void reset() {
		armingTurn = 0;
		armingEnergy = 0;
		held = false;
		armed = false;
		setStandard();
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
	
	public boolean isHeld() {
		return held;
	}
	
	@Override
	public void cleanUp() {
		//TODO: figure this out.
	}

}
