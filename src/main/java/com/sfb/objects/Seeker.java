package com.sfb.objects;

/**
 * Seeking weapons are units that have particular behaviors and properties. This is the interface to represent that.
 * 
 * @author deastland
 *
 */
public interface Seeker {
	
	/**
	 * Set the target that the seeker will pursue
	 * @param target The unit that the seeker will attempt to impact.
	 */
	public void setTarget(Unit target);
	
	/**
	 * Get the target of the seeker.
	 * @return The unit that is the target of the seeker.
	 */
	public Unit getTarget();
	
	public String getType();
	
	public void setType(String type);

	public boolean isSelfGuiding();
	public void setSelfGuiding(boolean selfGuiding);

	public int getEndurance();
	public void setEndurance(int endurance);
	public int getLaunchImpulse();
	public void setLaunchImpulse(int launchImpulse);

	/**
	 * Find out the strength of the seeker's warhead
	 * @return The amount of damage the seeker will do on impact.
	 */
	public int getWarheadDamage();

	public void setWarheadDamage(int warheadDamage);	//TODO: Should this be exposed or hidden in each implementation?
	
	public void setController(Unit controllingUnit);
	public Unit getController();
	
	/**
	 * The seeker impacts its target, doing damage.
	 * @return The damage done by the seeker to its target.
	 */
	public int impact();
}
