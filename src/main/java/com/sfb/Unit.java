package com.sfb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class Unit extends Thing {

	Integer[] shields = {0,0,0,0,0,0};

	// Hull systems
	Map<String, Integer> hull = new HashMap<>();
	
	// Power systems
	Map<String, Integer> warp = new HashMap<>();
	Integer impulse = 0;
	Integer apr = 0;
	Integer awr = 0;
	Integer battery = 0;
	
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
	Integer speed = 0;
	
	// On spool-up, set initial value for all members.
	public Unit() {
		// Init hull values
		hull.put("forward", null);
		hull.put("aft", null);
		hull.put("center", null);
		
		// Init warp values
		warp.put("right", null);
		warp.put("left", null);
		warp.put("center", null);
		
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

	public Integer getRightWarp() {
		return warp.get("right");
	}

	public void setRightWarp(Integer rwarp) {
		this.warp.put("right", rwarp);
	}

	public Integer getLeftWarp() {
		return warp.get("left");
	}

	public void setLeftWarp(Integer lwarp) {
		this.warp.put("left", lwarp);
	}

	public Integer getCenterWarp() {
		return warp.get("center");
	}

	public void setCenterWarp(Integer cwarp) {
		this.warp.put("center", cwarp);
	}

	public Integer getImpulse() {
		return impulse;
	}

	public void setImpulse(Integer impulse) {
		this.impulse = impulse;
	}

	public Integer getApr() {
		return apr;
	}

	public void setApr(Integer apr) {
		this.apr = apr;
	}

	public Integer getAwr() {
		return awr;
	}

	public void setAwr(Integer awr) {
		this.awr = awr;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
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

	public Map<String, Integer> getWarp() {
		return warp;
	}

	public void setWarp(Map<String, Integer> warp) {
		this.warp = warp;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public void setBridge(Map<String, Integer> bridge) {
		this.bridge = bridge;
	}

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
