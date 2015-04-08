package com.sfb.weapons;

import com.sfb.properties.WeaponArmingType;
import com.sfb.utilities.DiceRoller;

public class Disruptor extends Weapon implements HeavyWeapon {
	
	// STANDARD
	private final static int[] hitChart = 
		{0,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
	// OVERLOAD
	private final static int[] overloadHitChart =
		{6,5,5,4,4,4,4,4,4};
	
	// SPECIAL FIRE MODES
	private final static int[] derfacsHitChart =
		{0,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2};
	private final static int[] uimHitChart =
		{0,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
	private final static int[] uimOverloadHitChart =
		{6,5,5,5,5,5,5,5,5};
	
	// STANDARD DAMAGE
	private final static int[] damageChart =
		{0,5,4,4,4,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1};
	// OVERLOAD DAMAGE
	private final static int[] overloadDamageChart =
		{10,10,8,8,8,6,6,6,6};

	private int              maxRange		= 0;							// Maximum range this weapon may be fired.
	private WeaponArmingType armingType		= WeaponArmingType.STANDARD;
	private int              armingTurn		= 0;							// Number of turns the weapon has been arming.
	private double           armingEnergy 	= 0;							// Amount of total energy stored in the weapon.
	private boolean          armed 			= false;						// True if the weapon is armed and ready to fire.

	// Default will have range 30
	public Disruptor() {
		this(30);
	}
	
	// This is the only constructor we want to use.
	public Disruptor(int maxRange) {
		this.maxRange = maxRange;
		setDacHitLocaiton("torp");
		setName("Disruptor-" + maxRange);
	}
	
	@Override
	public boolean setStandard() {
		this.armingType = WeaponArmingType.STANDARD;
		return true;
	}

	@Override
	public boolean setOverload() {
		this.armingType = WeaponArmingType.OVERLOAD;
		return true;
	}

	@Override
	public boolean hold(int energy) {
		// Disruptors can't be held.
		return false;
	}

	@Override
	public int getArmingTurn() {
		return armingTurn;
	}

	@Override
	public boolean isArmed() {
		return armed;
	}

	@Override
	public WeaponArmingType getArmingType() {
		return armingType;
	}

	@Override
	public void reset() {
		armingType = WeaponArmingType.STANDARD;
		armingEnergy = 0;
		armingTurn = 0;
		armed = false;

	}

	/**
	 * Fire the diruptors using the default targeting system.
	 * 
	 * @param range The range to the target.
	 * @return Damage dealt by the weapon to the target (0 if a miss) or -1 if illegal condition (range, arming, etc.).
	 */
	@Override
	public int fire(int range) {
		// If the dirutptor isn't armed, it can't fire.
		if (!isArmed()) {
			return -1;
		}
		
		// If the target is out of range, it can't fire.
		if (range > maxRange) {
			return -1;
		}

		int damage = 0;
		// Roll to hit.
		DiceRoller diceRoller = new DiceRoller();

		// Based on arming type, calculate damage (0 on a miss).
		switch(armingType) {
		case STANDARD:
			// Can't fire at range 0.
			if (range < 1) {
				return -1;
			}
			
			// Can't fire beyond maximum range for this weapon.
			if (range > maxRange) {
				return -1;
			}
			
			// Calculate hit/damage for the range.
			if (diceRoller.rollOneDie() <= hitChart[range]) {
				damage = damageChart[range];
			}
			break;
		case OVERLOAD:
			// Can't fire at targets beyond range 8.
			if (range > 8) {
				return -1;
			}
			
			// Calculate hit/damage for the range
			if (diceRoller.rollOneDie() <= overloadHitChart[range]) {
				damage = overloadDamageChart[range];
			}
			break;
		default:
			break;
		}
		
		// Once fired, the weapon is no longer armed.
		reset();
		
		return damage;
	}

	/**
	 * Fire the disruptors using the UIM targeting system.
	 * 
	 * @param range The range to the target.
	 * @return Damage dealt by the weapon to the target (0 if a miss) or -1 if illegal condition (range, arming, etc.).
	 */
	public int fireUim(int range) {
		// If the dirutptor isn't armed, it can't fire.
		if (!isArmed()) {
			return -1;
		}

		// If the target is out of range, it can't fire.
		if (range > maxRange) {
			return -1;
		}

		int damage = 0;
		// Roll to hit.
		DiceRoller diceRoller = new DiceRoller();

		// Based on arming type, calculate damage (0 on a miss).
		switch(armingType) {
		case STANDARD:
			// Standard disruptors can't fire at range 0.
			if (range < 1) {
				return -1;
			}
			
			// Can't fire beyond maximum range for this weapon.
			if (range > maxRange) {
				return -1;
			}
			
			// Calculate hit/damage for the range.
			if (diceRoller.rollOneDie() <= uimHitChart[range]) {
				damage = damageChart[range];
			}
			break;
		case OVERLOAD:
			// Overloads can't fire at targets beyond range 8.
			if (range > 8) {
				return -1;
			}
			
			// Calculate hit/damage for the range
			if (diceRoller.rollOneDie() <= uimOverloadHitChart[range]) {
				damage = overloadDamageChart[range];
			}
			break;
		default:
			break;
		}
		
		// Once fired, the weapon is no longer armed.
		reset();
		
		return damage;
	}

	/**
	 * Fire the disruptors using the DERFACS targeting system.
	 * 
	 * @param range The range to the target.
	 * @return Damage dealt by the weapon to the target (0 if a miss) or -1 if illegal condition (range, arming, etc.).
	 */
	public int fireDerfacs(int range) {
		// If the dirutptor isn't armed, it can't fire.
		if (!isArmed()) {
			return -1;
		}

		// If the target is out of range, it can't fire.
		if (range > maxRange) {
			return -1;
		}

		int damage = 0;
		// Roll to hit.
		DiceRoller diceRoller = new DiceRoller();

		// Based on arming type, calculate damage (0 on a miss).
		switch(armingType) {
		case STANDARD:
			// Standard disruptors can't fire at range 0.
			if (range < 1) {
				return -1;
			}
			
			// Can't fire beyond maximum range for this weapon.
			if (range > maxRange) {
				return -1;
			}
			
			// Calculate hit/damage for the range.
			if (diceRoller.rollOneDie() <= derfacsHitChart[range]) {
				damage = damageChart[range];
			}
			break;
		case OVERLOAD:
			// Overloads can't fire at targets beyond range 8.
			// Also must have 4 arming energy.
			if (range > 8 || armingEnergy != 4) {
				return -1;
			}
			
			// Calculate hit/damage for the range
			if (diceRoller.rollOneDie() <= overloadHitChart[range]) {
				damage = overloadDamageChart[range];
			}
			break;
		default:
			break;
		}
		
		// Once fired, the weapon is no longer armed.
		reset();
		
		return damage;
	}

	@Override
	public boolean arm(int energy) {
		// Putting 2 more energy into an armed standard 
		// disruptor will overload it. But otherwise you can
		// not arm an armed disruptor.
		if (isArmed()) {
			if (armingEnergy == 2 && energy == 2) {
				setOverload();
			} else {
				return false;
			}
			// If the weapon is not armed, arm to the proper level for the energy.
		} else if (energy == 2) {
			setStandard();
		} else if (energy == 4) {
			setOverload();
		} else {
			return false;
		}

		// If we've made it this far, the weapon is good to arm.
		armingEnergy += energy;
		armingTurn++;
		armed = true;
		return true;
	}

	/**
	 * No special mode for disruptors, just standard and overload.
	 * @return False.
	 */
	@Override
	public boolean setSpecial() {
		return false;
	}

	@Override
	public int energyToArm() {
		// Standard disruptors require 2 energy to arm.
		if (armingType == WeaponArmingType.STANDARD) {
			return 2;
		// Overloaded disruptors require 4 energy to arm.
		} else {
			return 4;
		}
	}

}
