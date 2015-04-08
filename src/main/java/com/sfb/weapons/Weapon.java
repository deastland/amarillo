package com.sfb.weapons;

/**
 * Parent class for all weapons. Contains common functionality shared by weapons of all types.
 * 
 * @author Daniel Eastland
 *
 */
public abstract class Weapon {
	
	private String  name;				// The name of the weapon ('phaser1', 'disruptor30', 'photon', 'esg', 'droneG', etc.)
	private String  dacHitLocaiton;		// What DAC 'hit' destroys  this weapon //TODO: should this be an enum?
	private int[]   arcs;				// The arcs into which the weapon can fire
	private boolean functional;			// True if the weapon is undamaged, false otherwise.
	
	/**
	 * Determine what value on the DAC ('torp', 'drone', etc.) will damage this weapon.
	 * 
	 * @return The DAC string that affects this weapon.
	 */
	public String getDacHitLocaiton() {
		return dacHitLocaiton;
	}
	
	/**
	 * Specifies which weapon type on the Damage Allocation Chart
	 * will destroy this weapon.
	 * 
	 * @param dacHitLocaiton A string representing the DAC weapon type.
	 */
	public void setDacHitLocaiton(String dacHitLocaiton) {
		this.dacHitLocaiton = dacHitLocaiton;
	}
	
	/**
	 * Specify int which arcs this weapon may fire.
	 * 
	 * @return An array of int representing all of the arcs into which this weapon may fire.
	 */
	public int[] getArcs() {
		return arcs;
	}
	
	/**
	 * Set all of the arcs into which this weapon my fire.
	 * 
	 * @param arcs An array of ints representing all of the arcs 
	 * into which this weapon may fire.
	 */
	public void setArcs(int[] arcs) {
		this.arcs = arcs;
	}
	
	/**
	 * Check to see if the weapon can hit a target
	 * within the provided arc.
	 * 
	 * @param targetArc The arc containing the target.
	 * @return True if the target is within the weapon arcs, false otherwise.
	 */
	public boolean inArc(int targetArc) {
		for (int i=0; i < arcs.length; i++) {
			if (targetArc == arcs[i]) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if the weapon is undamaged.
	 * @return True if weapon is undamaged, false otherwise.
	 */
	public boolean isFunctional() {
		return functional;
	}
	
	/**
	 * Apply damage to the weapon, rendering it non-functional.
	 */
	public void damage() {
		functional = false;
	}
	
	/**
	 * Repair a damaged weapon, rendering it functional again.
	 */
	public void repair() {
		functional = true;
	}
	/**
	 * Fire the weapon at a target and calculate the resultant
	 * damage, if any.
	 * 
	 * @param range The range to the target.
	 * @return The amount of damage to be applied to the target.
	 */
	public abstract int fire(int range);

	/**
	 * Get the name of the weapon (Phaser1, Photon, etc.).
	 * 
	 * @return The name of the weapon
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
