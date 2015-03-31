package com.sfb.objects;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class Ship extends Marker {

	Shields shields;

	// Hull systems
	Map<String, Integer> hull = new HashMap<>();
	Map<String, Integer> currentHull = new HashMap<>();
	
	// Power systems
	PowerSystems powerSystems = new PowerSystems();

	// Misc systems
	Integer tractor = 0;
	Integer trans = 0;
	Integer lab = 0;
	Integer probe = 0;
	Integer shuttle = 0;
	Integer cargo = 0;
	
	// Control
	Map<String, Integer> bridge = new HashMap<>();
	
	// Integral Systems
	//
	// Dam con, sensors, scanners
	Integer scanner = 0;
	Integer sensor = 0;
	Integer excess = 0;
	
	// Weapon systems
	
	// Traits
	Integer[] turnMode = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


	// "Real-time" data
	Integer sideslipCount = 0;
	Integer turnCount = 0;
	
	// On spool-up, set initial value for all members.
	public Ship() {
		// Init hull values
		hull.put("forward", null);
		hull.put("aft", null);
		hull.put("center", null);
		
		currentHull.putAll(hull);
		
		// Init warp values

		// Init control space values
		bridge.put("bridge", null);
		bridge.put("flag", null);
		bridge.put("emer", null);
		bridge.put("auxcon", null);
		bridge.put("security", null);
	}
	
	public void setShields(Integer[] shields) {
		this.shields = shields;
	}
	
	public Integer[] getShields() {
		return this.shields;
	}
	
	public Integer getShield(Integer shieldNumber) {
		return this.shields[shieldNumber - 1];
	}
	
	public void setShield(Integer shieldNumber, int value) {
		this.shields[shieldNumber - 1] = value;
	}

	public Integer getForwardHull() {
		Integer returnValue = null;
		returnValue = this.hull.get("forward");
		
		return returnValue;
	}

	public void setForwardHull(Integer fhull) {
		this.hull.put("forward", fhull);
	}

	public Integer getAftHull() {
		Integer returnValue = null;
		returnValue = this.hull.get("aft");
		
		return returnValue;
	}

	public void setAftHull(Integer ahull) {
		this.hull.put("forward", ahull);
	}

	public Integer getCenterHull() {
		Integer returnValue = null;
		returnValue = this.hull.get("center");
		
		return returnValue;
	}

	public void setCenterHull(Integer chull) {
		this.hull.put("center", chull);
	}

	public Integer getCargo() {
		return cargo;
	}

	public void setCargo(Integer cargo) {
		this.cargo = cargo;
	}

	public Integer getExcess() {
		return excess;
	}

	public void setExcess(Integer excess) {
		this.excess = excess;
	}

	public Integer getTractor() {
		return tractor;
	}

	public void setTractor(Integer tractor) {
		this.tractor = tractor;
	}

	public Integer getTrans() {
		return trans;
	}

	public void setTrans(Integer trans) {
		this.trans = trans;
	}

	public Integer getLab() {
		return lab;
	}

	public void setLab(Integer lab) {
		this.lab = lab;
	}

	public Integer getProbe() {
		return probe;
	}

	public void setProbe(Integer probe) {
		this.probe = probe;
	}

	public Integer getShuttle() {
		return shuttle;
	}

	public void setShuttle(Integer shuttle) {
		this.shuttle = shuttle;
	}

	public Integer getBridge() {
		return this.bridge.get("bridge");
	}

	public void setBridge(Integer bridge) {
		this.bridge.put("bridge", bridge);
	}

	public Integer getFlag() {
		return this.bridge.get("flag");
	}

	public void Flag(Integer flag) {
		this.bridge.put("flag", flag);
	}

	public Integer getEmer() {
		return this.bridge.get("emer");
	}

	public void setEmer(Integer emer) {
		this.bridge.put("emer", emer);
	}

	public Integer getAuxcon() {
		return this.bridge.get("auxcon");
	}

	public void setAuxcon(Integer auxcon) {
		this.bridge.put("auxcon", auxcon);
	}

	public Integer getSecurity() {
		return this.bridge.get("security");
	}

	public void setSecurity(Integer security) {
		this.bridge.put("security", security);
	}

	public Integer getScanner() {
		return scanner;
	}

	public void setScanner(Integer scanner) {
		this.scanner = scanner;
	}

	public Integer getSensor() {
		return sensor;
	}

	public void setSensor(Integer sensor) {
		this.sensor = sensor;
	}
	
	public Map<String, Integer> getHull() {
		return hull;
	}

	public void setHull(Map<String, Integer> hull) {
		this.hull = hull;
	}

	public void setBridge(Map<String, Integer> bridge) {
		this.bridge = bridge;
	}
	
	///////////////////////////////////////////////////////
	///
	/// Utility Functions
	///
	///////////////////////////////////////////////////////
	
	// This will take the dammageApplied and subtract it from
	// the shield. If there is still damage to apply after the
	// shield has been completely destroyed, it will return the
	// remaining damage amount. Otherwise it will return zero.
	public Integer damageShield(Integer shield, Integer damageApplied) {
		Integer remainingDamage = null;
		
		// Get the strength of the shield.
		int shieldStrength = getShield(shield);
		
		int remainingShield = shieldStrength - damageApplied;

		if (remainingShield >= 0) {
			setShield(shield, remainingShield);
			remainingDamage = 0;
		} else {
			setShield(shield, 0);
			remainingDamage = -remainingShield;
		}
		return remainingDamage;
	}
	
	// Return the JSON string of the Unit object
	@Override
	public String toString() {
		String jsonOutput = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			jsonOutput = ow.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonOutput;
	}
}
