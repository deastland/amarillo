package com.sfb.systems;

public class Shuttles {

	private int shuttleBoxes;
	private int availableShuttleBoxes;
	
	public Shuttles() {}
	
	public void init(int numberOfShuttles) {
		availableShuttleBoxes = shuttleBoxes = numberOfShuttles;
	}
	
	//////
	
	public int getOriginalTotalBoxes() {
		return shuttleBoxes;
	}
	
	public int getTotalBoxes() {
		return availableShuttleBoxes;
	}
}
