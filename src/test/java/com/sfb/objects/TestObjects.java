package com.sfb.objects;

import java.util.HashMap;
import java.util.Map;

import com.sfb.systemgroups.PowerSystems;

public class TestObjects {
	
	public static PowerSystems testPowerSystems = getTestPowerSystems();

	
	private static PowerSystems getTestPowerSystems() {
		PowerSystems ps = new PowerSystems();
		
		Map<String, Integer> powerSettings = new HashMap<>();
		powerSettings.put("lwarp", 15);
		powerSettings.put("rwarp", 15);
		powerSettings.put("apr", 4);
		powerSettings.put("impulse", 4);
		powerSettings.put("battery", 4);
		
		ps.init(powerSettings);
		return ps;
	}
}
