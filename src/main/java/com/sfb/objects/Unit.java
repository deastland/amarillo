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
	private int sizeClass = 0;
	
	//TODO: Point Value
	
	private int[] turnMode;
	
	private int sideslipCount = 0;			// Can't sideslip unless this value is 1. Reset to 0 with every sideslip.
	private int turnCount     = 99;			// Turn count must reach turn mode value before the ship can turn. Reset to 0 with every turn.

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

	public int getSizeClass() {
		return sizeClass;
	}

	public void setSizeClass(int sizeClass) {
		this.sizeClass = sizeClass;
	}
	
	/**
	 * Return the turn mode for the unit at its current speed.
	 * 
	 * @return The number of hexes the ship must move before it can turn.
	 */
	public int getTurnMode() {
		return this.turnMode[speed];
	}
	
	/// MOVEMENT ///
	
	public boolean sideslipLeft() {
		if (sideslipCount == 0) {
			return false;
		}
		//TODO: Work out logic for this. Will need to change ship position to the hex at range 1 in the '21' direction (or hex 6 direction) wthiout changing the ship facing.
		sideslipCount = 0;
		return true;
	}

	public boolean sideslipRight() {
		if (sideslipCount == 0) {
			return false;
		}
		//TODO: Work out logic for this. Will need to change ship position to the hex at range 1 in the '5' direction (or hex 2 direction) wthiout changing the ship facing.
		sideslipCount = 0;
		return true;
	}
	
	public boolean turnLeft() {
		if (turnCount < turnMode[speed]) {
			return false;
		}
		//TODO: Chagne ship facing and position to match.
		
		
		turnCount = 1;
		return true;
	}

	public boolean turnRight() {
		if (turnCount < turnMode[speed]) {
			return false;
		}
		//TODO: Change ship facing and position to match.
		
		
		turnCount = 1;
		return true;
	}
	
	public boolean goStraight() {
		
		//TODO: Change ship position to match.
		sideslipCount++;
		turnCount++;
		
		return true;
	}
	
	/**
	 * Change the facing of the unit without moving it.
	 * @param absoluteFacing The new facing of the unit with respect to the map.
	 * @return True if the maneuver is possible, false otherwise.
	 */
	public boolean het(int absoluteFacing) {
		
		
		
		return true;
	}
}
