package com.sfb.systemgroups;

import java.util.Map;

// Shipboard systems are any group of SSD boxes that perform
// a number of functions (hull, weapons, controll, etc.) and
// are logically grouped together.
public interface Systems {

	/**
	 * Initialization of the systems data. 
	 * @param data A mapping containing the system name and an object with the system data.
	 */
	public void init(Map<String, Object> data);
	
	/**
	 * Get the total number of SSD boxes for this system group.
	 * @return The number of SSD boxes in this group before any damage.
	 */
	public int getOriginalTotalBoxes();
	
	/**
	 * Get the total number of remaining SSD boxes for this system group.
	 * @return The number of remaining, undamaged SSD boxes in this group.
	 */
	public int getTotalBoxes();
	
	/**
	 * Perform end-of-turn housekeeping duties, if any, so the ship will be ready for the
	 * next energy allocation.
	 */
	public void cleanUp();
}
