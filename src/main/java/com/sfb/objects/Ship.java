package com.sfb.objects;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.sfb.properties.ProbeArmingType;
import com.sfb.systemgroups.ControlSpaces;
import com.sfb.systemgroups.HullBoxes;
import com.sfb.systemgroups.OperationsSystems;
import com.sfb.systemgroups.PowerSystems;
import com.sfb.systemgroups.Probes;
import com.sfb.systemgroups.Shields;
import com.sfb.systemgroups.Shuttles;
import com.sfb.systemgroups.Weapons;
import com.sfb.systems.SpecialFunctions;

/**
 * 
 * This object describes an SFB ship. In particular it should represent
 * the contents of an SSD along with all boxes, ammo, ship traits, etc.
 * 
 * @author deastland
 *
 * @version 1.0
 */
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
	
	// Probes
	Probes probes = new Probes();
	
	// Shuttles
	Shuttles shuttles = new Shuttles();
	
	// Weapons systems
	Weapons weapons = new Weapons();
	
	////////////////////////////////////////////////////////
	int phaserCapaciter = 0;
	int availablePhaserCapaciter = 0;
	int phaserCapaciterPower = 0;
	
	// Traits
	Integer[] turnMode = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	

	// "Real-time" data
	Integer sideslipCount = 0;
	Integer turnCount = 0;
	boolean crippled = false;
	/////////////////////////////////////////////////////////
	
	
	// On spool-up, set initial value for all members.
	public Ship() {	}
	
	// Fill in the values for all the systems.
	public void initShip(Map<String, Integer> values) {
		
	}
	
	/// SHIELDS ///
	
	// Create shields
	public void initShields(Map<String, Integer> values) {
		this.shields.init(values);
	}
	
	// Get the strength of a particular shield (including specific reinforcement)
	public int getShield(Integer shieldNumber) {
		return this.shields.getShieldStrength(shieldNumber);
	}
	
	// Applies damage to given shield. If any damage remains, return the value.
	// Otherwise return 0.
	public int damageShield(Integer shieldNumber, Integer damageApplied) {
		return this.shields.damageShield(shieldNumber, damageApplied);
	}
	
	/// HULL BOXES ///
	
	// Create hull boxes
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
	
	// Create control boxes.
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
	
	// Create special function tracks.
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
	
	// Create Operations System Boxes
	public void initOperationsSystems(Map<String, Integer> values) {
		this.operationsSystems.init(values);
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
	
	/// CRIPPLE CALCULATIONS ////
	
	// Calculate the total number of boxes on the ship.
	private int getTotalSSDBoxes() {
		int totalBoxes = 0;
		totalBoxes += this.controlSpaces.getOriginalTotalBoxes();
		totalBoxes += this.powerSystems.getOriginalTotalBoxes();
		totalBoxes += this.hullBoxes.getOriginalTotalBoxes();
		totalBoxes += this.operationsSystems.getOriginalTotalBoxes();
		totalBoxes += this.probes.getOriginalTotalBoxes();
		totalBoxes += this.specialFunctions.getOriginalExcessDamage();
		totalBoxes += this.shuttles.getOriginalTotalBoxes();
		totalBoxes += this.weapons.getOriginalTotalBoxes();
		
		//TODO: Figure out what other data goes here. (for example, weapons)
		
		return totalBoxes;
	}
	
	private int getCurrentBoxes() {
		int totalBoxes = 0;
		totalBoxes += this.controlSpaces.getTotalBoxes();
		totalBoxes += this.powerSystems.getTotalBoxes();
		totalBoxes += this.hullBoxes.getTotalBoxes();
		totalBoxes += this.operationsSystems.getTotalBoxes();
		totalBoxes += this.probes.getTotalBoxes();
		totalBoxes += this.specialFunctions.getExcessDamage();
		totalBoxes += this.shuttles.getTotalBoxes();
		totalBoxes += this.weapons.getTotalBoxes();
		
		//TODO: Derive this from the above method.
		
		
		return totalBoxes;
	}
	
	// The ship may be crippled if half or more of its boxes
	// are destroyed.
	private boolean isCrippled() {
		if (getCurrentBoxes() >= getTotalSSDBoxes() / 2) {
			return true;
		}
		
		return false;
	}
	
	/// PROBES ///
	
	// Create probe boxes.
	public void initProbes(Map<String, Integer> values) {
		probes.init(values);
	}
	
	// Spend a turn arming a probe for information
	// Specify which probe box.
	// Return cost of arming or -1 if failure to arm.
	public int armInformationProbe(int probeNumber) {
		this.probes.setProbeType(probeNumber, ProbeArmingType.INFORMATION);
		int cost = this.probes.arm(probeNumber);
		
		return cost;
	}
	
	
	/// SHUTTLES ///

	//TODO: Shuttle operations
	
//	public int getShuttle() {
//		return shuttle;
//	}
//
//	public void setShuttle(Integer shuttle) {
//		this.shuttle = shuttle;
//	}

	
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
