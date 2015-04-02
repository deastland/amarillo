package com.sfb.weapons;

public abstract class Weapon {
	
	private String dacHitLocaiton;		// What DAC 'hit' destroys  this weapon //TODO: should this be an enum?
	private int[] arcs;					// What arcs can the weapon fire into?
	
	public String getDacHitLocaiton() {
		return dacHitLocaiton;
	}
	
	public void setDacHitLocaiton(String dacHitLocaiton) {
		this.dacHitLocaiton = dacHitLocaiton;
	}
	
	public int[] getArcs() {
		return arcs;
	}
	
	public void setArcs(int[] arcs) {
		this.arcs = arcs;
	}
	
	public abstract int rollDamage(int range);

}
