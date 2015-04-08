package com.sfb.weapons;

import java.util.ArrayList;
import java.util.List;

import com.sfb.objects.Drone;

public class DroneRack extends Weapon {

	private List<Drone> ammo = new ArrayList<>();		// The drones in the rack.		
	private int addAmmo = 0;							// The number of ADD shots in the drone rack.
	
	private List<Drone> reloads = new ArrayList<>();	// The drone reloads available.
	private int addReloads		= 0;					// The number of ADD reloads available.
	
	/**
	 * Get a list of drones ready to fire.
	 * @return A list of drones in the rack.
	 */
	public List<Drone> getAmmo() {
		return ammo;
	}
	
	@Override
	public int fire(int range) {
		// TODO Auto-generated method stub
		return 0;
	}

}
