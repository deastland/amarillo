package com.sfb.weapons;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sfb.properties.WeaponArmingType;

public class DisruptorTest {

	@Test
	public void testArming() {
		// Get an unarmed disruptor.
		Disruptor disruptor = getUnarmedDisruptor();
		
		// Verify that it is unarmed
		assertFalse(disruptor.isArmed());
		
		// verify that STANDARD disruptor takes 2 energy to arm.
		assertEquals(disruptor.energyToArm(), 2);
		
		// Try to arm with the wrong energy
		assertFalse(disruptor.arm(3));
		
		// Arm the disruptor.
		assertTrue(disruptor.arm(2));
		int range = 12;
		
		// Verify that the disruptor is armed
		assertTrue(disruptor.isArmed());
		
		// Fire the disruptor, this should reneder it unarmed.
		disruptor.fire(range);

		// Verify that the disruptor is unarmed.
		assertFalse(disruptor.isArmed());

		// Arm the disruptor again.
		assertTrue(disruptor.arm(2));
		
		// Check that it is armed
		assertTrue(disruptor.isArmed());
		
		// Check that it is STANDARD
		assertEquals(WeaponArmingType.STANDARD, disruptor.getArmingType());
		
		// Arm it again, with 2 more points of energy.
		assertTrue(disruptor.arm(2));
		
		// Verify that the extra 2 energy overloaded it.
		assertEquals(WeaponArmingType.OVERLOAD, disruptor.getArmingType());
		
	}
	
	@Test
	public void testFiring() {
		
		// Get an armed standard disruptor.
		Disruptor standardDis = getStandardDisruptor();
		int range = 30;
		
		// Fire the disruptor
		int damage = standardDis.fire(range);
		
		// Damage will be 2 at range 30 (or 0 on a miss)
		assertTrue(damage == 2 || damage == 0);
		
		// Try to fire the disruptor again
		damage = standardDis.fire(range);
		
		// Verify that we got a -1, indicating an invalid fire request.
		assertEquals(damage, -1);
		
		// Get another standard armed disruptor
		standardDis = getStandardDisruptor();
		
		// Set the range to an illegal value 
		range = 0;
		
		// Fire the weapon
		damage = standardDis.fire(range);
		
		// Verify that we got a -1, indicating an invalid fire request.
		assertEquals(damage, -1);
		
		// Get an armed overloaded disruptor
		Disruptor ovDis = getOverloadedDisruptor();
		
		// Verify that it takes 4 energy to arm an OL disr
		assertEquals(ovDis.energyToArm(), 4);
		
		// Verify that it is overloaded.
		assertEquals(ovDis.getArmingType(), WeaponArmingType.OVERLOAD);
		
		// Fire the disruptor at range 8 target
		range = 8;
		damage = ovDis.fire(range);
		
		// Damage will be 6 at range 8 (or 0 on a miss).
		assertTrue(damage == 6 || damage == 0);
		
		// Try to fire the disruptor again.
		damage = ovDis.fire(range);
		
		// Verify that we got a -1 indicating an invalid fire request.
		assertEquals(damage, -1);
		
		ovDis = getOverloadedDisruptor();
		range = 20;
		
		damage = ovDis.fire(range);
		
		// Firing at a bad range results in -1 damage.
		assertEquals(damage, -1);
	}
	
	/// Object builders for tests
	
	private Disruptor getUnarmedDisruptor() {
		Disruptor disruptor = new Disruptor(30);
		
		return disruptor;
		
	}
	
	private Disruptor getStandardDisruptor() {
		Disruptor dis = new Disruptor(30);
		dis.setStandard();
		dis.arm(2);
		
		return dis;
	}
	
	private Disruptor getOverloadedDisruptor() {
		Disruptor dis = new Disruptor(30);
		dis.setOverload();
		dis.arm(4);
		
		return dis;
	}

}
