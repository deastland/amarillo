package com.sfb.objects;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.sfb.systemgroups.ControlSpaces;
import com.sfb.systemgroups.HullBoxes;
import com.sfb.systemgroups.OperationsSystems;
import com.sfb.systemgroups.PowerSystems;
import com.sfb.systemgroups.Probes;
import com.sfb.systemgroups.Shields;
import com.sfb.systemgroups.Shuttles;
import com.sfb.systemgroups.Weapons;
import com.sfb.systems.PerformanceData;
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

	/// All the stuff that goes into a ship ///
	
	Shields           shields           = new Shields();				// Shield systems
	HullBoxes         hullBoxes         = new HullBoxes();				// Hull boxes
	PowerSystems      powerSystems      = new PowerSystems();			// Power systems (warp, impulse, apr, awr, battery)
	ControlSpaces     controlSpaces     = new ControlSpaces();			// Control systems (bridge, flag, aux, emer, security)
	SpecialFunctions  specialFunctions  = new SpecialFunctions();		// Special functions
	OperationsSystems operationsSystems = new OperationsSystems();		// Operations Systems
	Probes            probes            = new Probes();					// Probes
	Shuttles          shuttles          = new Shuttles();				// Shuttles and shuttle bays.
	Weapons           weapons           = new Weapons();				// Weapons
	PerformanceData	  performance		= new PerformanceData();		// Base statistics for the frame.
	
	////////////////////////////////////////////////////////
	// Traits

	// Hull Data
	// "Real-time" data
	Integer sideslipCount = 0;			// Can't sideslip unless this value is 0.
	Integer turnCount     = 0;			// Turn count must reach turn mode value before the ship can turn.
	boolean crippled      = false;		// True if the ship is crippled.
	
	//TODO: Transporter bombs (romulan nuclear mine).
	//TODO: Boarding parties (minimum crew).
	//TODO: Crew Units
	//TODO: Turn Mode Chart (HET & Breakdown tracking)
	//TODO: Refits (different ships with parent/child relationship?)
	//TODO: Armor?
	/////////////////////////////////////////////////////////
	
	
	// On spool-up, set initial value for all members.
	public Ship() {	}
	
	// Fill in the values for all the systems.
	public void initShip(Map<String, Object> values) {
		initShields(values);
		initHullBoxes(values);
		initPowerSystems(values);
		initControlSpaces(values);
		initSpecialFunctions(values);
		initOperationsSystems(values);
		initProbes(values);
		initShuttles(values);
		initWeapons(values);
	}
	
	/// SHIELDS ///
	
	// Create shields
	public void initShields(Map<String, Object> values) {
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
	public void initHullBoxes(Map<String, Object> values) {
		this.hullBoxes.init(values);
	}
	
	public HullBoxes getHullBoxes() {
		return this.hullBoxes;
	}

	/// POWER SYSTEMS ///
	public void initPowerSystems(Map<String, Object> values) {
		powerSystems.init(values);
	}
	
	public PowerSystems getPowerSysetems() {
		return powerSystems;
	}
	
	/// CONTROL SPACES ///
	
	// Create control boxes.
	public void initControlSpaces(Map <String, Object> values) {
		this.controlSpaces.init(values);
	}
	
	public ControlSpaces getControlSpaces() {
		return this.controlSpaces;
	}
	
	/// SPECIAL FUNCITONS ///
	
	// Create special function tracks.
	public void initSpecialFunctions(Map<String, Object> values) {
		specialFunctions.init(values);
	}
	
//	public void initSpecialFunctions(int[] damageControlValues, int[] scannerValues, int[] sensorValues, int excessDamageValue) {
//		this.specialFunctions.init(damageControlValues, scannerValues, sensorValues, excessDamageValue);
//	}
	
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
	
	public boolean hasDerfacs() {
		return this.specialFunctions.hasDerfacs();
	}
	
	public boolean hasUim() {
		return this.specialFunctions.hasUim();
	}
	
	/// OPERATIONS SYSTEMS ///
	
	// Create Operations System Boxes
	public void initOperationsSystems(Map<String, Object> values) {
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
	
	/// PROBES ///
	
	// Create probe boxes.
	public void initProbes(Map<String, Object> values) {
		probes.init(values);
	}
	
	public Probes getProbes() {
		return this.probes;
	}
	
	/// WEAPONS ///
	
	// Create weapons.
	public void initWeapons(Map<String, Object> values) {
		weapons.init(values);
	}
	
	/// SHUTTLES ///

	//TODO: Shuttle operations
	
	public void initShuttles(Map<String, Object> values) {
		shuttles.init(values);
	}
	
//	public int getShuttle() {
//		return shuttle;
//	}
//
//	public void setShuttle(Integer shuttle) {
//		this.shuttle = shuttle;
//	}

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
	public boolean isCrippled() {
		if (getCurrentBoxes() >= getTotalSSDBoxes() / 2) {
			return true;
		}
		
		return false;
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
