package com.sfb.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ShipTest {
	
	@Test
	public void initTest() {
		// Create a new ship
		Ship testShip = new Ship();
		
		// Load the ship with the values from the map.
		testShip.init(getInitMap());
		
		// Verify number of fhull
		assertEquals(4, testShip.getHullBoxes().getAvailableFhull());
		
		// Verify ahull
		assertEquals(7, testShip.getHullBoxes().getAvailableAhull());
		
		// Do a point of damage to the FHull
		testShip.getHullBoxes().damageFhull();
		
		// Verify the new smaller fhull total
		assertEquals(3, testShip.getHullBoxes().getAvailableFhull());
		
		// Verify number of chull
		assertEquals(0, testShip.getHullBoxes().getAvailableChull());
		
		// Verify lwarp
		assertEquals(15, testShip.getPowerSysetems().getAvailableLWarp());
		
		// Verify impulse
		assertEquals(5, testShip.getPowerSysetems().getAvailableImpulse());
		
		// Do some damage to the impulse.
		assertTrue(testShip.getPowerSysetems().damageImpulse());
		
		// Check that the impulse power is reduced.
		assertEquals(4, testShip.getPowerSysetems().getAvailableImpulse());
		
		// Repair impulse
		assertTrue(testShip.getPowerSysetems().repairImpulse(1));
		// Verify impulse is back to original value.
		assertEquals(5, testShip.getPowerSysetems().getAvailableImpulse());
		
		// Try to repair again, and it fails because there are no undamaged systems.
		assertFalse(testShip.getPowerSysetems().repairImpulse(1));
		
		// Get security stations
		assertEquals(2, testShip.getControlSpaces().getAvailableSecurity());
		
		// Get aux control
		assertEquals(2, testShip.getControlSpaces().getAvailableAuxcon());
		
		// Get probes
		assertEquals(1, testShip.getProbes().availableProbes());
		
		// Arm the probe for information.
		testShip.getProbes().get(0).setToInformation();
		assertTrue(testShip.getProbes().get(0).arm(1));
		
		// Not fully armed, so return -1 when trying to fire.
		assertEquals(-1, testShip.getProbes().get(0).fire(3));
		
		// Arm a second time
		assertTrue(testShip.getProbes().get(0).arm(1));
		
		// Fire fully armed probe.
		assertEquals(20, testShip.getProbes().get(0).fire(3));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Let's experiment with making an init map for a D7.
	private Map<String, Object> getInitMap() {
		Map<String, Object> shipMap = new HashMap<>();
		
		// HullBoxes
		shipMap.put("fhull", new Integer(4));
		shipMap.put("ahull", new Integer(7));
		
		// ControlSpaces
		shipMap.put("bridge", new Integer(2));
		shipMap.put("flag", new Integer(0));
		shipMap.put("emer", new Integer(1));
		shipMap.put("auxcon", new Integer(2));
		shipMap.put("security", new Integer(2));
		
		// Operations Systems
		shipMap.put("trans", new Integer(5));
		shipMap.put("tractor", new Integer(3));
		shipMap.put("lab", new Integer(4));

		// Power Systems
		shipMap.put("lwarp", new Integer(15));
		shipMap.put("rwarp", new Integer(15));
		shipMap.put("cwarp", new Integer(0));
		shipMap.put("impulse", new Integer(5));
		shipMap.put("apr", new Integer(4));
		shipMap.put("awr", new Integer(0));
		shipMap.put("battery", new Integer(3));

		// Probes
		shipMap.put("probe", new Integer(1));
		
		// Shields
		shipMap.put("shield1", new Integer(30));
		shipMap.put("shield2", new Integer(22));
		shipMap.put("shield3", new Integer(15));
		shipMap.put("shield4", new Integer(13));
		shipMap.put("shield5", new Integer(15));
		shipMap.put("shield6", new Integer(22));

		int[] sensor = {6,6,5,3,1,0};
		int[] scanner = {0,0,1,3,5,9};
		int[] damcon = {4,4,2,2,2,0};
		Integer excess = 5;

		shipMap.put("sensor", sensor);
		shipMap.put("scanner", scanner);
		shipMap.put("damcon", damcon);
		shipMap.put("excess", excess);
		
		shipMap.put("movecost", new Double(1));
		shipMap.put("breakdown", new Integer(5));
		shipMap.put("nimble", new Boolean(false));
		shipMap.put("bonushets", new Integer(1));
		shipMap.put("shieldCost", new Integer(2));
		shipMap.put("lifesupport", new Integer(1));

		return shipMap;
	}

}
