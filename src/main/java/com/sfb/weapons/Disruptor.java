package com.sfb.weapons;

import com.sfb.properties.DisruptorArmingType;
import com.sfb.properties.WeaponArmingType;

public class Disruptor extends Weapon implements HeavyWeapon {

	// STANDARD
	private final static int[] hitChart = 
		{0,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2};
	// OVERLOAD
	private final static int[] overloadHitChart =
		{6,5,5,4,4,4,4,4,4};
	// SPECIAL
	private final static int[] derfacsHitChart =
		{0,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
	private final static int[] uimHitChart =
		{0,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,2,2,2,2,2,2,2};
	private final static int[] uimOverloadHitChart =
		{6,5,5,5,5,5,5,5,5};

	private int maxRange = 0;											// Maximum range this weapon may be fired.
	private WeaponArmingType armingType = WeaponArmingType.STANDARD;
	private int armingTurn = 0;											// Number of turns the weapon has been arming.
	private double armingEnergy = 0;									// Amount of total energy stored in the weapon.
	private boolean armed = false;										// True if the weapon is armed and ready to fire.

	// Default disruptor range is 30.
	private Disruptor() {
		this.maxRange = 30;
	}
	
	// This is the only constructor we want to use.
	public Disruptor(int range) {
		this.maxRange = range;
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
	public boolean setSpecial() {
		this.armingType = WeaponArmingType.SPECIAL;
		return true;
	}
	
	@Override
	public boolean setSpecial2() {
		this.armingType = WeaponArmingType.SPECIAL2;
		return true;
	}
	
	@Override
	public boolean setSpecial3() {
		this.armingType = WeaponArmingType.SPECIAL3;
		return true;
	}
	/**
	 * Set arming type to DERFACS.
	 * 
	 * @return True.
	 */
	public boolean setDerfacs() {
		return setSpecial();
	}
	
	/**
	 * Set arming type to UIM
	 * 
	 * @return True
	 */
	public boolean setUim() {
		return setSpecial2();
	}
	
	/**
	 * Set arming type to UIM Overload
	 * 
	 * @return True
	 */
	public boolean setUimOverload() {
		return setSpecial3();
	}

	@Override
	public boolean hold(int energy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getArmingTurn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isArmed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WeaponArmingType getArmingType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public int fire(int range) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean arm(int energy) {
		// TODO Auto-generated method stub
		return false;
	}

}
