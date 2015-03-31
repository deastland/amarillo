package com.sfb.objects;

// Units are any thing on the map that is more than
// a simple dumb object. It can be a ship, a missile,
// a monster...anything that does more than simply exist.

// In addition to a location, a unit has a facing and a speed.
public class Unit extends Marker {
	
	// Facing is a value representing
	// a direction that the thing is facing, relative
	// to the hex map. (1 is "due north" and 4 is "due south).
	//
	//      1
	//  21     5
	//      X
	//  17     9
	//     13
	//
	private int facing = 1;
	private int speed = 0;
	
	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
	}
	
	// Given the true map-based bearing of a target
	// from this thing, adjust the bearing so that
	// it instead gives the bearing relative to the
	// front of the Thing.
	// Given the true (map-oriented) bearing and the facing of the source
	// Give the relative bearing, with the front of the source as the "1" bearing.
	public int getRelativeBearing(int trueBearing, int facing) {
		if (facing == 1) {
			return trueBearing;
		}

		int adjustDown = facing - 1;
		int adjustUp = 24 - adjustDown;
		
		if (trueBearing >= facing) {
			return trueBearing - adjustDown;
		} else {
			return trueBearing + adjustUp;
		}
		
	}

}
