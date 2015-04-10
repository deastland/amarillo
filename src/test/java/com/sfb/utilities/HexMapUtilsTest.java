package com.sfb.utilities;

import org.junit.Test;

import com.sfb.properties.Location;

public class HexMapUtilsTest {

	@Test
	public void testTrueBearing() {
		int facing = 5;
		int shipBearing = 13;
		
		System.out.println(HexMapUtils.getTrueBearing(shipBearing, facing));
	}
	
	@Test
	public void testAdjacentHex() {
		Location sourceLocation = new Location(27, 15);
		int facing = 5;
		int relativeBearing = 21;
		
		Location destinationLocation = HexMapUtils.getAdjacentHex(sourceLocation, HexMapUtils.getTrueBearing(relativeBearing, facing));
		
		System.out.println(destinationLocation);
	}
	
	@Test
	public void testFacingRightChange() {
		int facing = 21;
		
		int newFacing = HexMapUtils.getTrueBearing(9, facing);
		
		System.out.println("Turn right from " + facing + " has you now facing " + newFacing + ".");
		
	}
}
