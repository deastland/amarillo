package com.sfb.objects;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.sfb.systems.ControlSpaces;
import com.sfb.systems.HullBoxes;
import com.sfb.systems.OperationsSystems;
import com.sfb.systems.PowerSystems;
import com.sfb.systems.Shields;
import com.sfb.systems.SpecialFunctions;

public class Ship extends Marker {

	Shields shields;

	// Hull systems
	HullBoxes hullBoxes = new HullBoxes();

	// Power systems (warp, impulse, ap/wr, battery)
	PowerSystems powerSystems = new PowerSystems();

	// Control systems (bridge, flag, aux, emer, security)
	ControlSpaces controlSpaces = new ControlSpaces();
	
	// Special functions
	SpecialFunctions specialFunctions = new SpecialFunctions();
	
	// Operations systems
	OperationsSystems operationsSystems = new OperationsSystems();
	
	
	
	Integer probe = 0;
	Integer shuttle = 0;

	// Weapon systems
	
	// Traits
	Integer[] turnMode = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


	// "Real-time" data
	Integer sideslipCount = 0;
	Integer turnCount = 0;
	
	// On spool-up, set initial value for all members.
	public Ship() {	}
	
	/// SHIELDS ///
	public void initShields(int[] shields) {
		this.shields.init(shields);
	}
	
	public int getShield(Integer shieldNumber) {
		return this.shields.getShieldStrength(shieldNumber);
	}
	
	// Applies damage to given shield. If any damage remains, return the value.
	// Otherwise return 0.
	public int damageShield(Integer shieldNumber, Integer damageApplied) {
		return this.shields.damageShield(shieldNumber, damageApplied);
	}
	
	/// HULL BOXES ///
	public void initHullBoxes(Map<String, Integer> values) {
		this.hullBoxes.init(values);
	}
	
	public int getForwardHull() {
		return this.hullBoxes.getAvailableFhull();
	}

	public int getAftHull() {
		return this.hullBoxes.getAvailableAhull();
	}

	public int getCenterHull() {
		return this.hullBoxes.getAvailableChull();
	}

	public int getCargo() {
		return this.hullBoxes.getAvailableCargo();
	}

	/// CONTROL SPACES ///
	public void initControlSpaces(Map <String, Integer> values) {
		this.controlSpaces.init(values);
	}
	
	public int getBridge() {
		return this.controlSpaces.getAvailableBridge();
	}

	public int getFlag() {
		return this.controlSpaces.getAvailableFlag();
	}

	public int getEmer() {
		return this.controlSpaces.getAvailableEmer();
	}

	public int getAuxcon() {
		return this.controlSpaces.getAvailableAuxcon();
	}

	public int getSecurity() {
		return this.controlSpaces.getAvailableSecurity();
	}

	/// SPECIAL FUNCITONS ///
	public void initSpecialFunctions(int[] damageControlValues, int[] scannerValues, int[] sensorValues, int excessDamageValue) {
		this.specialFunctions.init(damageControlValues, scannerValues, sensorValues, excessDamageValue);
	}
	
	public int getSensor() {
		return this.specialFunctions.getSensor();
	}
	
	public int getScanner() {
		return this.specialFunctions.getScanner();
	}
	
	public int getDamageControl() {
		return this.specialFunctions.getDamageControl();
	}
	
	public int getExcessDamage() {
		return this.specialFunctions.getDamageControl();
	}
	
	/// OPERATIONS SYSTEMS ///
	public void initOperationsSystems(int transValue, int tractorValue, int labValue) {
		this.operationsSystems.init(transValue, tractorValue, labValue);
	}
	
	public int getTrans() {
		return this.operationsSystems.getAvailableTrans();
	}
	
	public int getTractor() {
		return this.operationsSystems.getAvailableTractor();
	}
	
	public int getLab() {
		return this.operationsSystems.getAvailableLab();
	}
	
	
	
	
	
	public int getProbe() {
		return probe;
	}

	public void setProbe(Integer probe) {
		this.probe = probe;
	}

	public int getShuttle() {
		return shuttle;
	}

	public void setShuttle(Integer shuttle) {
		this.shuttle = shuttle;
	}

	
	///////////////////////////////////////////////////////
	///
	/// Utility Functions
	///
	///////////////////////////////////////////////////////
	
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
