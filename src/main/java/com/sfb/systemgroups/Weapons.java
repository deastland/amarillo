package com.sfb.systemgroups;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sfb.exceptions.CapacitorException;
import com.sfb.objects.Unit;
import com.sfb.utilities.MapUtils;
import com.sfb.weapons.Phaser1;
import com.sfb.weapons.Phaser2;
import com.sfb.weapons.Phaser3;
import com.sfb.weapons.Weapon;

/**
 * The collection of weapons on a ship.
 * 
 * @author Daniel Eastland
 *
 */
public class Weapons implements Systems {

	List<Weapon> weapons = new LinkedList<>();						// A list of all the weapons.
	
	private double       capacitor			= 0;					// Initial size of the phaser capacitor
	
	private List<Weapon> phaserList			= new ArrayList<>();	// List of all phasers
	private List<Weapon> torpList			= new ArrayList<>();	// List of all 'torp' type weapons (usually heavy weapons)
	private List<Weapon> droneList			= new ArrayList<>();	// List of all 'drone' type weapons (usually torps)
	
	private int          availablePhasers;							// Items hit on 'phaser' in the DAC
	private int          availableTorps;							// Items hit on 'torp' in the DAC
	private int          availableDrones;							// Items hit on 'drone' in the DAC
	private double       availableCapacitor;						// Current size of the phaser capacitor.
	
	private int          capacitorEnergy;							// Energy currently in the phaser capacitor.
	
	private Unit         owningShip;								// The ship on which this weapons system is mounted.
	
	private Weapons() {}
	
	public Weapons(Unit owningShip) {
		this.owningShip = owningShip;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(Map<String, Object> values) {
		weapons = values.get("weapons") == null ? new LinkedList<Weapon>() : (List<Weapon>)values.get("weapons");
		if (weapons != null) {
			for (Weapon weapon : weapons) {
				// Make sure the weapon knows what unit owns it.
				weapon.setOwningShip(owningShip);
				// Register a new phaser weapon
				if ("phaser".equals(weapon.getDacHitLocaiton())) {
					phaserList.add(weapon);
					
					// Increase the size of the capacitor to match 
					if (weapon instanceof Phaser1 || weapon instanceof Phaser2) {
						capacitor++;
					}
					
					if (weapon instanceof Phaser3) {
						capacitor += 0.5;
					}
				}
				// Register a new 'torp' type weapon
				if ("torp".equals(weapon.getDacHitLocaiton())) {
					torpList.add(weapon);
				}
				// Register a new 'drone' type weapon
				if ("drone".equals(weapon.getDacHitLocaiton())) {
					droneList.add(weapon);
				}
			}
		}		
		availablePhasers = phaserList.size();
		availableTorps = torpList.size();
		availableDrones = droneList.size();
		availableCapacitor = capacitor;
	}
	
	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Given a shooter and a target, fetch all weapons that have range and arc on the target. 
	 * @param source The shooter
	 * @param target The target
	 * @return All weapons on the shooter that have arc and range on the target.
	 */
	public List<Weapon> getAllBearingWeapons(Unit source, Unit target) {
		List<Weapon> bearingWeapons = new LinkedList<>();
		
		// Loop through all weapons, finding which ones are good to fire.
		for (Weapon weapon : weapons) {
			// Check to see that the target is in range.
			boolean inRange = MapUtils.getRange(source, target) <= weapon.getMaxRange();

			// Check to see that the target is in arc.
			int trueBearingOfTarget = MapUtils.getBearing(source, target);
			int relativeBearingToTarget = MapUtils.getRelativeBearing(trueBearingOfTarget, source.getFacing());
			boolean inArc = weapon.inArc(relativeBearingToTarget);
			
			// If it is in range AND in arc, add it to the list of weapons.
			if (inRange && inArc) {
				bearingWeapons.add(weapon);
			}
		}

		return bearingWeapons;
	}
	
	public List<Weapon> getWeapons() {
		return this.weapons;
	}

	@Override
	public int getOriginalTotalBoxes() {
		return phaserList.size() + torpList.size() + droneList.size();
	}

	@Override
	public int getTotalBoxes() {
		return availablePhasers + availableTorps + availableDrones;
	}
	
	public double getCapacitorEnergy() {
		return capacitorEnergy;
	}
	
	public double getCapacitor() {
		return capacitor;
	}
	
	public void drainCapacitor(double energy) throws CapacitorException {
		if (energy > this.capacitorEnergy) {
			throw new CapacitorException("Not enough capacitor energy.");
		} else {
			this.capacitorEnergy -= energy;
		}
	}
	
	public void chargeCapacitor(double energy) throws CapacitorException {
		if (this.capacitorEnergy + energy > this.availableCapacitor) {
			throw new CapacitorException("Too much energy for capacitor size.");
		} else {
			this.capacitorEnergy += energy;
		}
	}
	public double getAvailableCapacitor() {
		return availableCapacitor;
	}
	
	public int getAvailableTorps() {
		return availableTorps;
	}
	
	public int getAvailableDrones() {
		return availableDrones;
	}

	@Override
	public Unit getOwningUnit() {
		return this.owningShip;
	}


}

