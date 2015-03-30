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
	Integer cargo = 0;
	Integer excess = 0;
	
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
	
	// Control
	Integer bridge = 0;
	Integer fbridge = 0;
	Integer ebridge = 0;
	Integer auxcon = 0;
	Integer security = 0;
	
	// Integral Systems
	//
	// Dam con, sensors, scanners
	Integer scanner = 0;
	Integer sensor = 0;
	
	// Weapon systems
	
	// Traits
	Integer speed = 0;
	
	// On spool-up, set initial value for all members.
	public Unit() {
		// Init hull values
		hull.put("forward", 0);
		hull.put("aft", 0);
		hull.put("center", 0);
		
		// Init warp values
		warp.put("right", 0);
		warp.put("left", 0);
		warp.put("center", 0);
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

	public Integer getFhull() {
		return fhull;
	}

	public void setFhull(Integer fhull) {
		this.fhull = fhull;
	}

	public Integer getAhull() {
		return ahull;
	}

	public void setAhull(Integer ahull) {
		this.ahull = ahull;
	}

	public Integer getChull() {
		return chull;
	}

	public void setChull(Integer chull) {
		this.chull = chull;
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

	public Integer getRwarp() {
		return rwarp;
	}

	public void setRwarp(Integer rwarp) {
		this.rwarp = rwarp;
	}

	public Integer getLwarp() {
		return lwarp;
	}

	public void setLwarp(Integer lwarp) {
		this.lwarp = lwarp;
	}

	public Integer getCwarp() {
		return cwarp;
	}

	public void setCwarp(Integer cwarp) {
		this.cwarp = cwarp;
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
		return bridge;
	}

	public void setBridge(Integer bridge) {
		this.bridge = bridge;
	}

	public Integer getFbridge() {
		return fbridge;
	}

	public void setFbridge(Integer fbridge) {
		this.fbridge = fbridge;
	}

	public Integer getEbridge() {
		return ebridge;
	}

	public void setEbridge(Integer ebridge) {
		this.ebridge = ebridge;
	}

	public Integer getAuxcon() {
		return auxcon;
	}

	public void setAuxcon(Integer auxcon) {
		this.auxcon = auxcon;
	}

	public Integer getSecurity() {
		return security;
	}

	public void setSecurity(Integer security) {
		this.security = security;
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
