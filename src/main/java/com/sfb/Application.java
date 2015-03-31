package com.sfb;

import com.sfb.objects.Marker;
import com.sfb.objects.Ship;

public class Application {

	public static void main(String[] args) {

		Marker thing1 = new Marker(14,10);
		Marker thing2 = new Marker(14,9);
		
		int facing = 1;
		
		int range = HexMap.getDistance(thing1, thing2);
		
		FiringArc firingArc = FiringArc.values()[HexMap.getAbsoluteArc(thing1, thing2)];
		
		ShieldFacing shieldFacing = ShieldFacing.values()[HexMap.getAbsoluteShieldFacing(thing1, thing2)];

		int trueBearing = HexMap.getBearing(thing1, thing2);
		
		System.out.println("Source: " + thing1.getLocation());
		System.out.println("Target: " + thing2.getLocation());
		System.out.println("----------------------------");
		
		System.out.println("RelativeBearing: " + HexMap.getRelativeBearing(trueBearing, facing));

		System.out.println("Distance: " + range);
		System.out.println("ARC: " + firingArc);
		System.out.println("SHLD: " + shieldFacing);
		System.out.println("Bearing: " + trueBearing);
		
		System.out.println("---------------------------");
		
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
		
		
//		System.out.println(new Ship());
	}
	
}
