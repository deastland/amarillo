package com.sfb.objects;

import java.util.HashMap;
import java.util.Map;

import com.sfb.systemgroups.PowerSystems;
import com.sfb.weapons.Photon;

public class TestObjects {
	
	public static PowerSystems testPowerSystems = getTestPowerSystems();

	private static PowerSystems getTestPowerSystems() {
		PowerSystems ps = new PowerSystems(new Unit());
		
		Map<String, Object> powerSettings = new HashMap<>();
		powerSettings.put("lwarp", new Integer(15));
		powerSettings.put("rwarp", new Integer(15));
		powerSettings.put("apr", new Integer(4));
		powerSettings.put("impulse", new Integer(4));
		powerSettings.put("battery", new Integer(4));
		
		ps.init(powerSettings);
		return ps;
	}
	
}
