package com.sfb;

import com.sfb.objects.Marker;
import com.sfb.objects.Ship;
import com.sfb.properties.FiringArc;
import com.sfb.properties.ShieldFacing;
import com.sfb.sampleships.SampleShips;
import com.sfb.utilities.DAC;
import com.sfb.utilities.MapUtils;

public class Application {

	private static DAC myDac = new DAC();
	public static void main(String[] args) {

//		Marker thing1 = new Marker(14,10);
//		Marker thing2 = new Marker(14,9);
//		
//		int facing = 1;
//		
//		int range = MapUtils.getRange(thing1, thing2);
//		
//		FiringArc firingArc = FiringArc.values()[MapUtils.getAbsoluteArc(thing1, thing2)];
//		
//		ShieldFacing shieldFacing = ShieldFacing.values()[MapUtils.getAbsoluteShieldFacing(thing1, thing2)];
//
//		int trueBearing = MapUtils.getBearing(thing1, thing2);
//		
//		System.out.println("Source: " + thing1.getLocation());
//		System.out.println("Target: " + thing2.getLocation());
//		System.out.println("----------------------------");
//		
//		System.out.println("RelativeBearing: " + MapUtils.getRelativeBearing(trueBearing, facing));
//
//		System.out.println("Distance: " + range);
//		System.out.println("ARC: " + firingArc);
//		System.out.println("SHLD: " + shieldFacing);
//		System.out.println("Bearing: " + trueBearing);
//		
//		System.out.println("---------------------------");
		
//		DAC dac = new DAC();
//		int roll = 2;
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//
//		roll = 7;
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		
//		roll = 9;
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
//		System.out.println("Roll: " + roll + " |Result: " + dac.fetchNextHit(roll));
		
		Ship newShip = new Ship();
		newShip.init(SampleShips.getFedCa());
		
		System.out.println(new Ship());
	}
	
}
