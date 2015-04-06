package com.sfb.weapons;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeaponTest {
	
	@Test
	public void testArc() {
		Weapon wep = getWeapon();
		int[] arcList = {1,2,3,4,5};
		
		wep.setArcs(arcList);
		
		// Verify that arc 1 is available.
		assertTrue(wep.inArc(3));
		
		// Verify that arc 8 is not available.
		assertFalse(wep.inArc(8));
	}

	private Weapon getWeapon() {
		Weapon weapon = new Photon();
		
		return weapon;
	}

}
