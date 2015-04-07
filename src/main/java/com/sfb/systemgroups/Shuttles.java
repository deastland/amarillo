package com.sfb.systemgroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sfb.objects.Shuttle;


public class Shuttles implements Systems {

	private int           shuttleBoxes			= 0;					// Total shuttle boxes on the SSD
	private int           availableShuttleBoxes	= 0;					// Current undamaged shuttle boxes
	private List<Shuttle> shuttles				= new ArrayList<>();	// List of the shuttles for this ship.

	//TODO: Launch bays and launch rate.
	
	public Shuttles() {}
	
	public void init(int numberOfShuttles) {
		availableShuttleBoxes = shuttleBoxes = numberOfShuttles;
	}
	
	@Override
	public void init(Map<String, Object> values) {
		availableShuttleBoxes = shuttleBoxes = values.get("shuttle") == null ? 0 : (Integer)values.get("shuttle");
		// Create a new shuttle for each shuttle box on the ship.
		for (int i = 0; i < shuttleBoxes; i++) {
			shuttles.add(new Shuttle());
		}
	}
	
	//TODO: Implement shuttle/fighter stuff.
	
	@Override
	public int getOriginalTotalBoxes() {
		return shuttleBoxes;
	}
	
	@Override
	public int getTotalBoxes() {
		return availableShuttleBoxes;
	}
}
