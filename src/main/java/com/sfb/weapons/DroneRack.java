package com.sfb.weapons;

import java.util.ArrayList;
import java.util.List;

import com.sfb.objects.Drone;

public class DroneRack extends Weapon {
	
	private int         spaces 		= 0;					// The number of spaces in the rack (usually 4 or 6)

	private List<Drone> ammo 		= new ArrayList<>();	// The drones in the rack.		
	private int         addAmmo 	= 0;					// The number of ADD shots in the drone rack.
	
	private List<Drone> reloads		= new ArrayList<>();	// The drone reloads available.
	private int         addReloads	= 0;					// The number of ADD reloads available.
	
	public DroneRack() {
		setArcs(new int[] {0});
	}
	
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

	public int getSpaces() {
		return spaces;
	}

	public void setSpaces(int spaces) {
		this.spaces = spaces;
	}

	public int getAddAmmo() {
		return addAmmo;
	}

	public void setAddAmmo(int addAmmo) {
		this.addAmmo = addAmmo;
	}

	public List<Drone> getReloads() {
		return reloads;
	}

	public void setReloads(List<Drone> reloads) {
		this.reloads = reloads;
	}

	public int getAddReloads() {
		return addReloads;
	}

	public void setAddReloads(int addReloads) {
		this.addReloads = addReloads;
	}

	public void setAmmo(List<Drone> ammo) {
		this.ammo = ammo;
	}

	/**
	 * Checks if the drone rack is empty.
	 * @return True if there is no ammo in the rack, false otherwise.
	 */
	public boolean isEmpty() {
		return ammo.size() == 0;
	}
}
