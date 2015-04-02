package com.sfb.weapons;

import com.sfb.properties.WeaponArmingType;

public interface HeavyWeapon {

	
	public boolean setStandard();
	
	public boolean setOverload();
	
	public boolean setSpecial();
	
	public boolean hold(int energy);
	
	public int getArmingTurn();
	
	public boolean isArmed();
	
	public WeaponArmingType getArmingType();
	
	public void setArmingType(WeaponArmingType armingType);
	
}
