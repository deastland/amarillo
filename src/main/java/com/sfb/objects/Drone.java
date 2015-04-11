package com.sfb.objects;

import com.sfb.properties.TurnMode;

/**
 * A drone is a seeking weapon that is essentially a missile. It consists of a warhead and an engine.
 * 
 * @author Daniel Eastland
 * @version 1.0
 */
public class Drone extends Unit implements Seeker {
	
	private Unit    target;				// The target of the drone.
	private Unit	controller;			// The ship controlling this drone.
	private String  type;				// The type of drone. //TODO: Maybe an enum?
	private boolean selfGuiding;		// True if the weapon does not need control channels to operate, false otherwise.
	private int     endurance;			// The number of impulses this weapon will continue to operate.
	private int     launchImpulse;		// The (absolute) impulse this drone was launched.
	private int     warheadDamage;		// The damage dealt if the weapon hits its target.
	private int     rackSize;			// The number of spaces the drone takes up in a rack.
	private int     hull;				// The hull damage needed to kill the drone.
	
	public Drone() {
		setTurnMode(TurnMode.Seeker);
	}
	
	public void setTarget(Unit target) {
		this.target = target;
	}
	
	public Unit getTarget() {
		return this.target;
	}

	public void setController(Unit controllingUnit) {
		this.controller = controllingUnit;
	}
	
	public Unit getController() {
		return this.controller;
	}
	
	public int getRackSize() {
		return rackSize;
	}

	public void setRackSize(int rackSize) {
		this.rackSize = rackSize;
	}

	public int getHull() {
		return hull;
	}

	public void setHull(int hull) {
		this.hull = hull;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean isSelfGuiding() {
		return selfGuiding;
	}

	@Override
	public void setSelfGuiding(boolean selfGuiding) {
		this.selfGuiding = selfGuiding;
	}

	@Override
	public int getEndurance() {
		return endurance;
	}

	@Override
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	@Override
	public int getLaunchImpulse() {
		return this.launchImpulse;
	}

	@Override
	public void setLaunchImpulse(int launchImpulse) {
		this.launchImpulse = launchImpulse;
	}

	@Override
	public int getWarheadDamage() {
		return this.warheadDamage;
	}

	@Override
	public void setWarheadDamage(int warheadDamage) {
		this.warheadDamage = warheadDamage;
	}

	@Override
	public int impact() {
		// TODO Work out what happens here. We will need to destroy this object.
		
		return this.warheadDamage;
	}
	
}
