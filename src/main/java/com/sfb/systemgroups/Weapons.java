package com.sfb.systemgroups;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	List<Weapon> weapons = new LinkedList<>();	// A list of all the weapons.
	
	private double       capacitor			= 0;					// Initial size of the phaser capacitor
	
	private List<Weapon> phaserList			= new ArrayList<>();	// List of all phasers
	private List<Weapon> torpList			= new ArrayList<>();	// List of all 'torp' type weapons (usually heavy weapons)
	private List<Weapon> droneList			= new ArrayList<>();	// List of all 'drone' type weapons (usually torps)
	
	private int          availablePhasers;							// Items hit on 'phaser' in the DAC
	private int          availableTorps;							// Items hit on 'torp' in the DAC
	private int          availableDrones;							// Items hit on 'drone' in the DAC
	private double       availableCapacitor;						// Current size of the phaser capacitor.
	
	private int          capacitorEnergy;							// Energy currently in the phaser capacitor.
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(Map<String, Object> values) {
		weapons = values.get("weapons") == null ? new LinkedList<Weapon>() : (List<Weapon>)values.get("weapons");
		if (weapons != null) {
			for (Weapon weapon : weapons) {
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
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	

}

