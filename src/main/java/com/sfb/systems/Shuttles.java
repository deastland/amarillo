package com.sfb.systems;

public class Shuttles implements Systems {

	private int shuttleBoxes;
	private int availableShuttleBoxes;
	
	public Shuttles() {}
	
	public void init(int numberOfShuttles) {
		availableShuttleBoxes = shuttleBoxes = numberOfShuttles;
	}
	
	//////
	
	@Override
	public int getOriginalTotalBoxes() {
		return shuttleBoxes;
	}
	
	@Override
	public int getTotalBoxes() {
		return availableShuttleBoxes;
	}
}
