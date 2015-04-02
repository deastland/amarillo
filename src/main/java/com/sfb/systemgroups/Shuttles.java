package com.sfb.systemgroups;

import java.util.Map;


public class Shuttles implements Systems {

	private int shuttleBoxes;
	private int availableShuttleBoxes;
	
	public Shuttles() {}
	
	public void init(int numberOfShuttles) {
		availableShuttleBoxes = shuttleBoxes = numberOfShuttles;
	}
	
	@Override
	public void init(Map<String, Integer> values) {
		availableShuttleBoxes = shuttleBoxes = values.get("shuttle") == null ? 0 : values.get("shuttle");
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
