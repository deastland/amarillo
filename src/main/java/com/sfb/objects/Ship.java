package com.sfb.objects;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sfb.constants.Constants;
import com.sfb.exceptions.CapacitorException;
import com.sfb.properties.Faction;
import com.sfb.properties.ShieldStatus;
import com.sfb.systemgroups.ControlSpaces;
import com.sfb.systemgroups.Crew;
import com.sfb.systemgroups.HullBoxes;
import com.sfb.systemgroups.OperationsSystems;
import com.sfb.systemgroups.PowerSystems;
import com.sfb.systemgroups.ProbeLaunchers;
import com.sfb.systemgroups.Shields;
import com.sfb.systemgroups.Shuttles;
import com.sfb.systemgroups.Weapons;
import com.sfb.systems.Energy;
import com.sfb.systems.PerformanceData;
import com.sfb.systems.SpecialFunctions;
import com.sfb.systems.Tractors;
import com.sfb.weapons.HeavyWeapon;
import com.sfb.weapons.Weapon;

/**
 * 
 * This object describes an SFB ship. In particular it should represent
 * the contents of an SSD along with all boxes, ammo, ship traits, etc.
 * 
 * @author Daniel Eastland
 *
 * @version 1.0
 */
public class Ship extends Unit {

	/// All the stuff that goes into a ship ///
	
	private Shields           shields           = new Shields(this);			// Shield systems
	private HullBoxes         hullBoxes         = new HullBoxes(this);			// Hull boxes
	private PowerSystems      powerSystems      = new PowerSystems(this);		// Power systems (warp, impulse, apr, awr, battery)
	private ControlSpaces     controlSpaces     = new ControlSpaces(this);		// Control systems (bridge, flag, aux, emer, security)
	private SpecialFunctions  specialFunctions  = new SpecialFunctions();		// Special functions
	private OperationsSystems operationsSystems = new OperationsSystems(this);	// Operations Systems (transporter, lab)
	private Tractors          tractors          = new Tractors(this);			// Tractor beam systems.
	private ProbeLaunchers    probes            = new ProbeLaunchers(this);		// Probes
	private Shuttles          shuttles          = new Shuttles(this);			// Shuttles and shuttle bays.
	private Weapons           weapons           = new Weapons(this);			// Weapons
	private PerformanceData	  performanceData	= new PerformanceData();		// Base statistics for the frame.
	private Crew              crew				= new Crew(this);				// Crew
	
	private Energy            energyAllocated	= new Energy();					// Where all the ship's energy is allocated
	
	// WHERE SHOULD THIS GO?
	private int               armor             = 0;							// Some early ships have armor.
	private double            lifeSupportCost	= 0;							// cost to have life support active.
	private int               activeShieldCost	= 0;							// Cost to have shields active.
	private double            minimumShieldCost = 0;							// Cost to have shields at minimum.
	private int               fireControlCost	= 1;							// Cost for active fire control (always 1).
	
	// Other data
	private int               yearInService		= 0;							// The minimum year this ship can be deployed.
	private String            hullType			= null;							// Descriptor of the type of ship (i.e. "CA", "FFG", "D7K", etc.)
	private Faction			  faction			= Faction.Federation;			// The faction to which this ship belongs.
	private int               battlePointValue	= 0;							// BPV, a measure of how powerful the ship is in combat.
	
	// Real-time data
	private boolean           activeFireControl = false;						// True if active fire control is up, false otherwise.
	private ShieldStatus      shieldsStatus		= ShieldStatus.Inactive;		// Status of shields. Active is normal shields. Minimal is 5-point shields. Inactive is no shields at all.
	private boolean           lifeSupportActive = false;						// True if life support is active, false otherwise.
	
	
	//TODO: Transporter bombs (Romulan nuclear mine).
	//TODO: Armor?
	
	
	/**
	 * Constructor
	 */
	public Ship() {
	}

	/**
	 * Initialize all ship statistics through the values
	 * passed in the Map.
	 */
	public void init(Map<String, Object> values) {
		// Unit values
		super.init(values);
		
		// Explicit Ship values
		faction          = values.get("faction")     == null ? null : (Faction)values.get("faction");
		hullType         = values.get("hull")        == null ? null : (String)values.get("hull");
		yearInService    = values.get("serviceyear") == null ? 0    : (Integer)values.get("serviceyear");
		battlePointValue = values.get("bpv")         == null ? 0    : (Integer)values.get("bpv");
		
		// Calculated Ship Values
		lifeSupportCost  = Constants.LIFE_SUPPORT_COST[getSizeClass()];
		activeShieldCost = Constants.ACTIVE_SHIELD_COST[getSizeClass()];
		minimumShieldCost = Constants.MINIMUM_SHIELD_COST[getSizeClass()];
		
		// Subsystem values
		shields.init(values);
		hullBoxes.init(values);
		powerSystems.init(values);
		controlSpaces.init(values);
		specialFunctions.init(values);
		operationsSystems.init(values);
		tractors.init(values);
		probes.init(values);
		shuttles.init(values);
		weapons.init(values);
		crew.init(values);
		performanceData.init(values);
	}
	
	/**
	 * Set up the energy profile for this ship for the current turn.
	 * 
	 * @param allocation Object that will contain all instructions for
	 * allocation of the ship's energy for the turn.
	 */
	public void allocateEnergy(Energy allocation) {
		this.energyAllocated = allocation;
		
	}
	
	@Override
	public void startTurn() {
		// Speed = Movement energy / movement cost
		setSpeed((int)(energyAllocated.getMovement() / performanceData.getMovementCost()));
		
		// Life support
		if (energyAllocated.getLifeSupport() >= lifeSupportCost) {
			this.lifeSupportActive = true;
		} else if (isCrippled()) {
			this.lifeSupportActive = true;
		} else {
			this.lifeSupportActive = false;
		}

		// Fire control
		if (energyAllocated.getFireControl() >= fireControlCost) {
			this.activeFireControl = true;
		} else {
			this.activeFireControl = false;
		}
		
		// Shields
		
		// General Reinforcement (1 point for every 2 energy)
		if (energyAllocated.getGeneralReinforcement() > 0) {
			this.shields.addGeneralRenforcement(energyAllocated.getGeneralReinforcement() / 2);
		}
		// Specific reinforcement
		shields.reinforceAllShields(energyAllocated.getSpecificReinforcement());
		// Shield activation.
		if (energyAllocated.getActivateShields() == activeShieldCost) {
			this.shieldsStatus = ShieldStatus.Active;
		} else if (energyAllocated.getActivateShields() == minimumShieldCost) {
			this.shieldsStatus = ShieldStatus.Minimum;
		} else {
			this.shieldsStatus = ShieldStatus.Inactive;
		}
		
		// Damage Control
		//TODO: Damage Control - will probably need a list of systems repaired in the energy allocation
		
		// Phaser Capacitor
		try {
			chargeCapacitor(energyAllocated.getPhaserCapacitor());
		} catch (CapacitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Weapons
		for (Weapon weapon : weapons.getWeapons()) {
			// For heavy weapons, apply the arming type and energy
			if (weapon instanceof HeavyWeapon) {
				((HeavyWeapon) weapon).applyAllocationEnergy(energyAllocated.getArmingEnergy().get(weapon), energyAllocated.getArmingType().get(weapon));
			}
		}
		//TODO: Need to figure this out.
	}
	
	/**
	 * Perform end-of-turn activities needed to prepare for the next energy allocation phase.
	 */
	public void cleanUp() {
		
		//TODO: Figure out if there is any Ship object level cleanup needed.
		// For example, if there is recharge energy remaining - put it into the batteries
		
		shields.cleanUp();
		hullBoxes.cleanUp();
		powerSystems.cleanUp();
		controlSpaces.cleanUp();
		specialFunctions.cleanUp();
		operationsSystems.cleanUp();
		tractors.cleanUp();
		probes.cleanUp();
		shuttles.cleanUp();
		weapons.cleanUp();
		crew.cleanUp();
		performanceData.cleanUp();
	}
	
	/// BASIC SHIP DATA ///
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
	
	public double getLifeSupportCost() {
		return this.lifeSupportCost;
	}
	
	public boolean isLifeSupportActive() {
		return this.lifeSupportActive;
	}
	
	public int getActiveShieldCost() {
		return this.activeShieldCost;
	}
	
	/**
	 * Indicates if the shields are in Active mode
	 * @return True if the shields are Active, false otherwise.
	 */
	public boolean shieldsActive() {
		return this.shieldsStatus == ShieldStatus.Active;
	}
	
	/**
	 * Indicates if the shields are in Inactive mode
	 * @return True if the sheilds are Inactive, false otherwise.
	 */
	public boolean shieldsInactive() {
		return this.shieldsStatus == ShieldStatus.Inactive;
	}
	
	// Cleanup tasks for the end of the turn.
	public void endOfTurn() {
		shields.cleanUp();
		
	}
	
	/// SHIELDS ///
	public Shields getShields() {
		return this.shields;
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
	
	/**
	 * Checks on the status of the shields: 
	 * Active) Full shields
	 * Minimal) 5-point shields
	 * Inactive) No shields
	 * 
	 * @return ShieldStatus of the ship's shields.
	 */
	public ShieldStatus getShieldStatus() {
		return this.shieldsStatus;
	}
	
	/**
	 * Checks to see if fire control is active.
	 * 
	 * @return True if fire control is active, false otherwise.
	 */
	public boolean isActiveFireControl() {
		return this.activeFireControl;
	}
	
	/// HULL BOXES ///
	
	public HullBoxes getHullBoxes() {
		return this.hullBoxes;
	}

	/// POWER SYSTEMS ///
	public PowerSystems getPowerSysetems() {
		return powerSystems;
	}
	
	/// CONTROL SPACES ///
	
	// Create control boxes.
	public ControlSpaces getControlSpaces() {
		return this.controlSpaces;
	}
	
	/// SPECIAL FUNCITONS ///
	public boolean hasDerfacs() {
		return this.specialFunctions.hasDerfacs();
	}
	
	public boolean hasUim() {
		return this.specialFunctions.hasUim();
	}
	
	/// OPERATIONS SYSTEMS ///
	public OperationsSystems getOperationsSystems() {
		return this.operationsSystems;
	}
	
	/// PROBES ///
	public ProbeLaunchers getProbes() {
		return this.probes;
	}
	
	/// WEAPONS ///
	public Weapons getWeapons() {
		return this.weapons;
	}
	
	/// SHUTTLES ///

	//TODO: Shuttle operations
	public Shuttles getShuttles() {
		return this.shuttles;
	}

	/// CREW ///
	public Crew getCrew() {
		return this.crew;
	}
	
	/// PERFORMANCE DATA ///
	public PerformanceData getPerformanceData() {
		return this.performanceData;
	}
	
	@Override
	public boolean performHet(int absoluteFacing) {
		boolean result = false;
		
		return result;
	}

	/**
	 * Calculate the total number of boxes on the ship SSD.
	 * 
	 * @return The number of boxes on the ship SSD
	 */
	private int getTotalSSDBoxes() {
		int totalBoxes = 0;
		totalBoxes += this.controlSpaces.fetchOriginalTotalBoxes();
		totalBoxes += this.powerSystems.fetchOriginalTotalBoxes();
		totalBoxes += this.hullBoxes.fetchOriginalTotalBoxes();
		totalBoxes += this.operationsSystems.fetchOriginalTotalBoxes();
		totalBoxes += this.tractors.fetchOriginalTotalBoxes();
		totalBoxes += this.probes.fetchOriginalTotalBoxes();
		totalBoxes += this.specialFunctions.getOriginalExcessDamage();
		totalBoxes += this.shuttles.fetchOriginalTotalBoxes();
		totalBoxes += this.weapons.fetchOriginalTotalBoxes();
		
		return totalBoxes;
	}
	
	/**
	 * Calculate the total number of remaining undamaged boxes on the ship.
	 * 
	 * @return The number of undamaged boxes on the ship.
	 */
	private int getCurrentBoxes() {
		int totalBoxes = 0;
		totalBoxes += this.controlSpaces.fetchRemainingTotalBoxes();
		totalBoxes += this.powerSystems.fetchRemainingTotalBoxes();
		totalBoxes += this.hullBoxes.fetchRemainingTotalBoxes();
		totalBoxes += this.operationsSystems.fetchRemainingTotalBoxes();
		totalBoxes += this.tractors.fetchRemainingTotalBoxes();
		totalBoxes += this.probes.fetchRemainingTotalBoxes();
		totalBoxes += this.specialFunctions.getExcessDamage();
		totalBoxes += this.shuttles.fetchRemainingTotalBoxes();
		totalBoxes += this.weapons.fetchRemainingTotalBoxes();
		
		return totalBoxes;
	}
	
	/**
	 * Find all weapons on the ship that have a chance of hitting the target.
	 * 
	 * @param target The target which weapons must hit.
	 * 
	 * @return A list of weapons with the right arcs and range to hit the target.
	 */
	public List<Weapon> fetchAllBearingWeapons(Unit target) {
		return weapons.fetchAllBearingWeapons(this, target);
	}
	
	/// PHASER CAPACITORS ///
	public void drainCapacitor(double energy) throws CapacitorException {
		this.weapons.drainPhaserCapacitor(energy);
	}
	
	public void chargeCapacitor(double energy) throws CapacitorException {
		this.weapons.chargePhaserCapacitor(energy);
	}
	
	// The ship may be crippled if half or more of its boxes
	// are destroyed.
	public boolean isCrippled() {
		if (getCurrentBoxes() <= (getTotalSSDBoxes() / 2)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Another unit attempts to tractor this ship.
	 * 
	 * @param energy The amount of energy applied to the tractor attempt.
	 * 
	 * @param tractoringUnit The unit attempting to tractor this ship.
	 * 
	 * @return True if the attempt is successful, false otherwise.
	 */
	@Override
	public boolean applyTractor(int energy, Unit tractoringUnit) {
		if (energy > this.tractors.getNegativeTractorEnergy()) {
			//TODO: Probably a tractor auction?
			
			setTractoringUnit(tractoringUnit);
			setTractored(true);
			return true;
		} else {
			return false;
		}
	}

	
	
	
	// Return the JSON string of the Unit object
	@Override
	public String toString() {
		
		String outputString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
//			mapper.writeValue(new File("e:\\ship.txt"), this);
			
			outputString = mapper.writeValueAsString(this);
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
		
		
		return outputString;
		
//		String jsonOutput = null;
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		try {
//			jsonOutput = ow.writeValueAsString(this);
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return jsonOutput;
	}
}
