package com.sfb.systemgroups;

import java.util.Map;

// Shipboard systems are any group of SSD boxes that perform
// a number of functions (hull, weapons, controll, etc.) and
// are logically grouped together.
public interface Systems {

	// Initialization of the systems data.
	public void init(Map<String, Integer> data);
	
	// Get a count of all the system boxes on the original SSD.
	public int getOriginalTotalBoxes();
	
	// Get a count of all currently undamaged systems boxes.
	public int getTotalBoxes();
}
