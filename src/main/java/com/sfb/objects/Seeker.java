package com.sfb.objects;

public class Seeker extends Unit {
	
	private String type;					// The name of the seeker.
	private int speed;						// How fast the drone will move.
	private int warheadDamage;				// How much damage it will do if it impacts.
	private boolean selfGuiding = false;	// True if the drone is self-guiding, false otherwise.
	
	private int endurance;					// The number of impulses (and therefore turns) the drone will last before running out of fuel. //TODO: This may be drone only, as warheadDamage is the endurance of a plasma torp.
	private int launchImpulse;				// The impulse in which the drone was launched.
	
	// The turn mode at each speed from 0 to 32.
	private static final int[] turnMode = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	public Seeker() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isSelfGuiding() {
		return selfGuiding;
	}

	public void setSelfGuiding(boolean selfGuiding) {
		this.selfGuiding = selfGuiding;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getLaunchImpulse() {
		return launchImpulse;
	}

	public void setLaunchImpulse(int launchImpulse) {
		this.launchImpulse = launchImpulse;
	}

	public static int[] getTurnmode() {
		return turnMode;
	}

	public int getWarheadDamage() {
		return warheadDamage;
	}

	public void setWarheadDamage(int warheadDamage) {
		this.warheadDamage = warheadDamage;
	}
	
	/**
	 * The seeker impacts its target, doing damage.
	 * @return The damage done by the seeker to its target.
	 */
	public int impact() {
		//TODO: Figure out how to do this. Does it need a target? If so you will need to track location and last location to figure out shield impact?
		
		return warheadDamage;
	}
}
