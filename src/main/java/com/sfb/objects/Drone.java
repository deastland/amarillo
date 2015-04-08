package com.sfb.objects;

public class Drone extends Seeker {
	
	private int rackSize = 1;				// The number of spaces the drone takes up in a rack.
	private int hull = 4;					// The hull damage needed to kill the drone.
	
	public Drone() {
		
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

}
