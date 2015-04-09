package com.sfb.objects;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.sfb.properties.Faction;
import com.sfb.systemgroups.ControlSpaces;
import com.sfb.systemgroups.Crew;
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
public class Ship extends Unit {

	/// All the stuff that goes into a ship ///
	
	private Shields           shields           = new Shields();				// Shield systems
	private HullBoxes         hullBoxes         = new HullBoxes();				// Hull boxes
	private PowerSystems      powerSystems      = new PowerSystems();			// Power systems (warp, impulse, apr, awr, battery)
	private ControlSpaces     controlSpaces     = new ControlSpaces();			// Control systems (bridge, flag, aux, emer, security)
	private SpecialFunctions  specialFunctions  = new SpecialFunctions();		// Special functions
	private OperationsSystems operationsSystems = new OperationsSystems();		// Operations Systems
	private Probes            probes            = new Probes();					// Probes
	private Shuttles          shuttles          = new Shuttles();				// Shuttles and shuttle bays.
	private Weapons           weapons           = new Weapons();				// Weapons
	private PerformanceData	  performanceData	= new PerformanceData();		// Base statistics for the frame.
	private Crew              crew				= new Crew();					// Crew
	
	// WHERE SHOULD THIS GO?
	private int               armor             = 0;							// Some early ships have armor.
	
	// Other data
	private int               yearInService		= 0;							// The minimum year this ship can be deployed.
	private String            hullType			= null;							// Descriptor of the type of ship (i.e. "CA", "FFG", "D7K", etc.)
	private Faction			  faction			= Faction.Federation;			// The faction to which this ship belongs.
	private String            name				= null;						// Name of the ship.
	private int               battlePointValue	= 0;							// BPV, a measure of how powerful the ship is in combat.
	
	//TODO: Transporter bombs (Romulan nuclear mine).
	//TODO: Turn Mode Chart (HET & Breakdown tracking)
	//TODO: Refits (different ships with parent/child relationship?)
	//TODO: Armor?
	//TODO: Point Value
	
	
	// On spool-up, set initial value for all members.
	public Ship() {	}

	/**
	 * Initialize all ship statistics through the values
	 * passed in the Map.
	 */
	public void init(Map<String, Object> values) {
		// Unit values
		super.init(values);
		
		// Ship values
		faction          = values.get("faction")     == null ? null : (Faction)values.get("faction");
		hullType         = values.get("hull")        == null ? null : (String)values.get("hull");
		yearInService    = values.get("serviceyear") == null ? 0    : (Integer)values.get("serviceyear");
		battlePointValue = values.get("bpv")         == null ? 0    : (Integer)values.get("bpv");
		
		// Subsystem values
		initShields(values);
		initHullBoxes(values);
		initPowerSystems(values);
		initControlSpaces(values);
		initSpecialFunctions(values);
		initOperationsSystems(values);
		initProbes(values);
		initShuttles(values);
		initWeapons(values);
		initCrew(values);
		initPerformanceData(values);
	}
	
	public void cleanUp() {
		
		//TODO: Figure out if there is any Ship object level cleanup needed.
		
		shields.cleanUp();
		hullBoxes.cleanUp();
		powerSystems.cleanUp();
		controlSpaces.cleanUp();
		specialFunctions.cleanUp();
		operationsSystems.cleanUp();
		probes.cleanUp();
		shuttles.cleanUp();
		weapons.cleanUp();
		crew.cleanUp();
		performanceData.cleanUp();
	}
	
	/// BASIC SHIP DATA ///
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setType(String type) {
		this.hullType = type;
	}
	
	public String getType() {
		return this.hullType;
	}
	
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	public Faction getFacation() {
		return this.faction;
	}
	
	public int getYearInService() {
		return this.yearInService;
	}
	
	public int getBpv() {
		return this.battlePointValue;
	}
	
	// Cleanup tasks for the end of the turn.
	public void endOfTurn() {
		shields.cleanUp();
		
	}
	
	/// SHIELDS ///
	
	// Create shields
	private void initShields(Map<String, Object> values) {
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
	private void initHullBoxes(Map<String, Object> values) {
		this.hullBoxes.init(values);
	}
	
	public HullBoxes getHullBoxes() {
		return this.hullBoxes;
	}

	/// POWER SYSTEMS ///
	private void initPowerSystems(Map<String, Object> values) {
		powerSystems.init(values);
	}
	
	public PowerSystems getPowerSysetems() {
		return powerSystems;
	}
	
	/// CONTROL SPACES ///
	
	// Create control boxes.
	private void initControlSpaces(Map <String, Object> values) {
		this.controlSpaces.init(values);
	}
	
	public ControlSpaces getControlSpaces() {
		return this.controlSpaces;
	}
	
	/// SPECIAL FUNCITONS ///
	
	// Create special function tracks.
	private void initSpecialFunctions(Map<String, Object> values) {
		specialFunctions.init(values);
	}
	
	public boolean hasDerfacs() {
		return this.specialFunctions.hasDerfacs();
	}
	
	public boolean hasUim() {
		return this.specialFunctions.hasUim();
	}
	
	/// OPERATIONS SYSTEMS ///
	
	// Create Operations System Boxes
	private void initOperationsSystems(Map<String, Object> values) {
		this.operationsSystems.init(values);
	}
	
	public OperationsSystems getOperationsSystems() {
		return this.operationsSystems;
	}
	
	/// PROBES ///
	
	// Create probe boxes.
	private void initProbes(Map<String, Object> values) {
		probes.init(values);
	}
	
	public Probes getProbes() {
		return this.probes;
	}
	
	/// WEAPONS ///
	
	// Create weapons.
	private void initWeapons(Map<String, Object> values) {
		weapons.init(values);
	}
	
	public Weapons getWeapons() {
		return this.weapons;
	}
	
	/// SHUTTLES ///

	//TODO: Shuttle operations
	
	private void initShuttles(Map<String, Object> values) {
		shuttles.init(values);
	}
	
	public Shuttles getShuttles() {
		return this.shuttles;
	}
//	public int getShuttle() {
//		return shuttle;
//	}
//
//	public void setShuttle(Integer shuttle) {
//		this.shuttle = shuttle;
//	}
	
	/// CREW ///
	private void initCrew(Map<String, Object> values) {
		crew.init(values);
	}
	
	public Crew getCrew() {
		return this.crew;
	}
	
	/// PERFORMANCE DATA ///
	
	private void initPerformanceData(Map<String, Object> values) {
		performanceData.init(values);
	}
	
	public PerformanceData getPerformanceData() {
		return this.performanceData;
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
	public boolean isCrippled() {
		if (getCurrentBoxes() <= (getTotalSSDBoxes() / 2)) {
			return true;
		}
		
		return false;
	}
	
	
	
	// Return the JSON string of the Unit object
	@Override
	public String toString() {
		String jsonOutput = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			jsonOutput = ow.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonOutput;
	}
}
