package com.sfb.objects;

public class TestObjects {
	
	public static PowerSystems testPowerSystems = getTestPowerSystems();

	
	private static PowerSystems getTestPowerSystems() {
		PowerSystems ps = new PowerSystems();
		
		ps.setLwarp(15);
		ps.setAvailableLWarp(15);
		ps.setRwarp(15);
		ps.setAvailableRWarp(15);
		ps.setApr(4);
		ps.setAvailableApr(4);
		ps.setImpulse(4);
		ps.setAvailableImpulse(4);
		ps.setBattery(4);
		ps.setAvailableBattery(4);
		
		return ps;
	}
}
