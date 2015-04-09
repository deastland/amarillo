package com.sfb.objects;

/**
 * Seeking weapons are units that have particular behaviors and properties. This is the interface to represent that.
 * 
 * @author deastland
 *
 */
public interface Seeker {
	
	public String getType();
	
	public void setType(String type);

	public boolean isSelfGuiding();
	public void setSelfGuiding(boolean selfGuiding);

	public int getEndurance();
	public void setEndurance(int endurance);
	public int getLaunchImpulse();
	public void setLaunchImpulse(int launchImpulse);

	public int getWarheadDamage();

	public void setWarheadDamage(int warheadDamage);
	
	/**
	 * The seeker impacts its target, doing damage.
	 * @return The damage done by the seeker to its target.
	 */
	public int impact();
}
