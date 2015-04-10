package com.sfb.weapons;

import com.sfb.exceptions.CapacitorException;
import com.sfb.exceptions.TargetOutOfRangeException;
import com.sfb.exceptions.WeaponUnarmedException;

public interface DirectFire {

	/**
	 * Fire the weapon, returning the damage done if a hit, 
	 * 0 if a miss, and -1 if the fire request was not legal.
	 * 
	 * @param range The range from the shooter to the target
	 * @param firingShip The ship that is firing the weapon
	 * @return The damage done by the weapon at that range
	 * @throws WeaponUnarmedException 
	 * @throws TargetOutOfRangeException 
	 */
	public abstract int fire(int range) throws WeaponUnarmedException, TargetOutOfRangeException, CapacitorException;
}
