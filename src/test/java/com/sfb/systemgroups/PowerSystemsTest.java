package com.sfb.systemgroups;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sfb.objects.TestObjects;
import com.sfb.systemgroups.PowerSystems;

public class PowerSystemsTest {

	@Test
	public void test() {
		// Get the test power system (Fed CA)
		// CA has 15 LWarp, 15RWarp, 4 Impulse, 4 APR, and 4 Batteries
		PowerSystems testPs = TestObjects.testPowerSystems;
		
		// CA has 4 APR
		assertEquals(testPs.getAvailableApr(), 4);
		// CA has 38 total power
		assertEquals(testPs.getTotalPower(), 38);
		
		// No CWarp on the ship, this should return false
		assertFalse(testPs.damageCWarp());

		// Damage the left warp engine 3 times
		assertTrue(testPs.damageLWarp());
		assertTrue(testPs.damageLWarp());
		assertTrue(testPs.damageLWarp());

		// Check to see that there are 12 remaining boxes
		assertEquals(testPs.getAvailableLWarp(), 12);
		
		// Check that total available power is 3 less
		assertEquals(testPs.getTotalPower(), 35);
		
		// Damage APR 4 times
		assertTrue(testPs.damageApr());
		assertTrue(testPs.damageApr());
		assertTrue(testPs.damageApr());
		assertTrue(testPs.damageApr());

		// Try to damage a 5th APR.
		// Should faile because there are only 4
		assertFalse(testPs.damageApr());
	}
	
	@Test
	public void testBatteries() {
		// Get the test power system (Fed CA)
		// CA has 15 LWarp, 15RWarp, 4 Impulse, 4 APR, and 4 Batteries
		PowerSystems testPs = TestObjects.testPowerSystems;

		// Put 3 power into battery
		assertTrue(testPs.chargeBattery(3));
		// Check battery power
		assertEquals(testPs.getBatteryPower(), 3);
		// Put 1 more power into battery
		assertTrue(testPs.chargeBattery(1));
		// Check battery power
		assertEquals(testPs.getBatteryPower(), 4);
		// Put 1 more power into battery (fail because no more room in batteries)
		assertFalse(testPs.chargeBattery(1));
		
		// Use 3 battery power
		assertTrue(testPs.useBattery(3));
		// Remaining battery power should be 1
		assertEquals(testPs.getBatteryPower(), 1);

	}

}
