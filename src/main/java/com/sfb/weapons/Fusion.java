package com.sfb.weapons;

import com.sfb.properties.WeaponArmingType;
import com.sfb.utilities.DiceRoller;

public class Fusion extends VariableDamageWeapon implements HeavyWeapon {
	
	// The damage chart for this weapon.
	private static final int[][] hitChart = {
		// Range 
		//0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
		{13,8,6,4,4,4,4,4,4,4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // Roll 1
		{11,8,5,3,3,3,3,3,3,3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // Roll 2
		{10,7,4,2,2,2,2,2,2,2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Roll 3
		{9, 6,3,1,1,1,1,1,1,1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Roll 4
		{8, 5,3,1,1,1,1,1,1,1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Roll 5
		{8, 4,2,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // Roll 6
			};
	private static final int[][] overloadHitChart = {
		// Range
		//0  1 2 3 4 5 6 7 8
		{19,12,9,6,6,6,6,6,6}, // Roll 1
		{16,12,7,4,4,4,4,4,4}, // Roll 2
		{15,10,6,3,3,3,3,3,3}, // Roll 3
		{13, 9,4,1,1,1,1,1,1}, // Roll 6
		{12, 7,4,1,1,1,1,1,1}, // Roll 5
		{12, 6,3,0,0,0,0,0,0}  // Roll 4
	};
	
	private static final int[][] suicideOverloadHitChart = {
		// Range
		//0  1  2 3 4 5 6 7 8
		{26,16,12,8,8,8,8,8,8}, // Roll 1
		{22,16,10,6,6,6,6,6,6}, // Roll 2
		{20,14, 8,4,4,4,4,4,4}, // Roll 3
		{18,12, 6,2,2,2,2,2,2}, // Roll 6
		{16,10, 6,2,2,2,2,2,2}, // Roll 5
		{16,8,  4,0,0,0,0,0,0}  // Roll 4
	};

	private int              maxRange		= 24;							// Maximum range this weapon may be fired.
	private WeaponArmingType armingType		= WeaponArmingType.STANDARD;
	private double           armingEnergy 	= 0;							// Amount of total energy stored in the weapon.
	private int              armingTurn     = 0;
	private boolean          armed 			= false;						// True if the weapon is armed and ready to fire.
	private boolean          cooldown		= false;						// Weapon must have a cooldown turn between firing turns.


	public Fusion() {
		setDacHitLocaiton("torp");
		setName("Fusion");
	}

	@Override
	public boolean setStandard() {
		armingType = WeaponArmingType.STANDARD;
		return true;
	}

	@Override
	public boolean setOverload() {
		this.armingType = WeaponArmingType.OVERLOAD;
		return true;
	}

	/**
	 * Set to suicide overload.  
	 */
	@Override
	public boolean setSpecial() {
		this.armingType = WeaponArmingType.SPECIAL;
		return true;
	}

	/**
	 * Set the fusion to suicide overload mode.
	 * @return True if this is a valid request, false otherwise.
	 */
	public boolean setSuicideOverload() {
		return setSpecial();
	}

	@Override
	public boolean hold(int energy) {
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
		setStandard();
		armingEnergy = 0;
		armingTurn = 0;
		armed = false;
	}

	@Override
	public boolean arm(int energy) {

		// Can't arm a fusion when on cooldown.
		if (isOnCooldown()) {
			return false;
		}

		// If the weapon is already armed:
		// 1) It can be promoted from standard to overload or suicide
		// 2) It can be promoted from overload to suicide.
		// 3) It can't be promoted from suicide.
		if (isArmed()) {
			switch(armingType) {
			case STANDARD:
				if (energy == 2) {
					setOverload();
				} else if (energy == 5) {
					setSuicideOverload();
				} else {
					return false;
				}
				break;
			case OVERLOAD:
				if (energy == 3) {
					setSuicideOverload();
				} else {
					return false;
				}
				break;
			case SPECIAL:
				return false;
			default:
				break;
			}
		// If the weapon is not armed, arm to the correct
		// level given the energy provided. Otherwise exit with false.
		} else {
			if (energy == 2) {
				setStandard();
			} else if (energy == 4) {
				setOverload();
			} else if (energy == 7) {
				setSuicideOverload();
			} else {
				return false;
			}
		}

		armingEnergy += energy;
		armed = true;
		return true;
	}

	@Override
	public int energyToArm() {
		if (armingType == WeaponArmingType.STANDARD) {
			return 2;
		} else if (armingType == WeaponArmingType.OVERLOAD) {
			return 4;
		} else {
			return 7;
		}
	}

	@Override
	public int fire(int range) {

		// If the weapon isn't armed, it can't be fired.
		if (!isArmed()) {
			return -1;
		}
		
		int damage = 0;
		
		// Throw the dice!
		DiceRoller roller = new DiceRoller();
		int roll = roller.rollOneDie();
		
		switch(this.armingType) {
		case STANDARD:
			// Can't fire standard fusions beyond range 24.
			if (range > maxRange) {
				return -1;
			}
			damage = hitChart[roll - 1][range];
			break;
		case OVERLOAD:
			// Can't fire overloads beyond range 8.
			if (range > 8) {
				return -1;
			}
			damage = overloadHitChart[roll - 1][range];
			break;
		case SPECIAL:
			// Can't fire suicide overloads beyond range 8.
			if (range > 8) {
				return -1;
			}
			
			damage = suicideOverloadHitChart[roll - 1][range];
			// This weapon is destroyed after firing in suicide mode.
			damage();
			break;
		default:
			break;
		}
		
		armed = false;
		// The weapon goes into cooldown mode after firing and can't be fired next turn.
		reset();

		return damage;
	}
	
	public boolean isOnCooldown() {
		return cooldown;
	}
	
	public void setOnCooldown() {
		cooldown = true;
	}


}
