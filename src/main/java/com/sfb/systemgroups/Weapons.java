package com.sfb.systemgroups;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sfb.weapons.Weapon;

public class Weapons implements Systems {

	List<Weapon> weapons = new LinkedList<>();
	
	int phasers;				// Items hit on 'phaser' in the DAC
	int torps;					// Items hit on 'torp' in the DAC
	int drones;					// Items hit on 'drone' in the DAC
	double capacitor;			// Initial size of the phaser capacitor
	
	int availablePhasers;		// Items hit on 'phaser' in the DAC
	int availableTorps;			// Items hit on 'torp' in the DAC
	int availableDrones;		// Items hit on 'drone' in the DAC
	double availableCapacitor;	// Current size of the phaser capacitor.
	
	int capicitorEnergy;		// Energy currently in the phaser capacitor.
	
	// Get all the weapons from the map.
	// The String will need to be formatted in such a way as to pull all the properties of the weapon.
	// This could get complicated.
	@Override
	public void init(Map<String, Object> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOriginalTotalBoxes() {
		return phasers + torps + drones;
	}

	@Override
	public int getTotalBoxes() {
		return availablePhasers + availableTorps + availableDrones;
	}

}


/// WORKING ON FORMAT FOR WEAPON STRINGS

//  <weapon>,<type>,<arcs>
//  phaser,1,5|6|7|8|9								// Phaser 1, L arc
//  disruptor,30,21|22|23|24|1|2|3|4|5				// Disruptor (range 30), FA arc
//  photon,30,21|22|23|24|1|2|3|4|5					// Photon, FA arc
//  drone,B,0										// Drone B Rack, 360 Arc		 ??? MAYBE JUST THE RACK, THEN PASS IN AMMO IN ANOTHER METHOD?
//  plasma,S,23|24|1|2|3|4|5|6|7|8|9|10|11,1|5|9	// Plasma S Launcher, RP Arc, can launch in directions 1,2, or 3.
//  esg,7,0											// ESG, max capacitor 7, 360 Arc
//  fusion,
