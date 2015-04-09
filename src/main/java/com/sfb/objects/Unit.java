package com.sfb.objects;

import java.util.Map;

import com.sfb.properties.TurnMode;
import com.sfb.utilities.TurnModeUtil;

// Units are any thing on the map that is more than
// a simple dumb object. It can be a ship, a missile,
// a monster...anything that does more than simply exist.

// In addition to a location, a unit has a facing and a speed.
public class Unit extends Marker {

	// Facing is a value representing
	// a direction that the thing is facing, relative
	// to the hex map. (1 is "due north" and 4 is "due south).
	//
	//    1
	// 2    5
	//    X
	// 17   9
	//    3
	//
	private int facing			= 0;	// Direction the unit is facing (1 through 6)
	private int speed			= 0;	// Speed the unit is moving (0 through 32)
	private int sizeClass		= 0;	// Size class of the unit (0 through 6...I think?)
	private int sideslipCount	= 0;	// Track number of moves since last sideslip.
	private int turnCount		= 0;	// Track number of moves since last turn.

	// TODO: Point Value

	private TurnMode turnMode;

	public Unit() {

	}

	/**
	 * Initialize a basic unit by setting its turn mode.
	 * @param values
	 */
	//TODO: Should I do an "init" or just have these values explicitly set on instantiation?
	public void init(Map<String, Object> values) {
		turnMode = values.get("turnmode") == null ? null : (TurnMode) values.get("turnmode");
	}

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
	// Give the relative bearing, with the front of the source as the "1"
	// bearing.
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
	 * @return The number of hexes the unit must move before it can turn.
	 */
	public TurnMode getTurnMode() {
		return this.turnMode;
	}

	public void setTurnMode(TurnMode mode) {
		turnMode = mode;
	}
	
	/**
	 * Get the number of hexes the unit must move before it can turn.
	 * 
	 * @return The number of hexes that must be moved before a turn.
	 */
	public int getTurnHexes() {
		return TurnModeUtil.getTurnMode(this.turnMode, this.speed);
	}

	// / MOVEMENT ///

	/**
	 * Sideslip the unit to the left. This is only possible if the unit
	 * has moved at least one hex since the last sideslip.
	 * The unit will move to the adjacent hed in (relative) direction 21 wihtout changing
	 * its facing.
	 * @return True if the sideslip was possible, false otherwise.
	 */
	public boolean sideslipLeft() {
		if (sideslipCount == 0) {
			return false;
		}
		// TODO: Work out logic for this. Will need to change unit position to
		// the hex at range 1 in the '21' direction (or hex 6 direction) wthiout
		// changing the unit facing.
		sideslipCount = 0;
		return true;
	}

	/**
	 * Sideslip the unit to the right. This is only possible if the unit
	 * has moved at least one hex since the last sideslip.
	 * The unit will move to the adjacent hex in (relative) direction 5 without changing
	 * its facing. 
	 * @return True if the sideslip was possible, false otherwise.
	 */
	public boolean sideslipRight() {
		if (sideslipCount == 0) {
			return false;
		}
		// TODO: Work out logic for this. Will need to change unit position to
		// the hex at range 1 in the '5' direction (or hex 2 direction) wthiout
		// changing the unit facing.
		sideslipCount = 0;
		return true;
	}

	/**
	 * Turn the unit to the left and move one hex forward. This will change the
	 * facing of the unit to (relative) direction 21 and then move it into the adjacent
	 * hex in (relative) direction 1.
	 * This is only possible if the unit has fulfilled its turn mode.
	 * @return True if the turn was possible, false otherwise.
	 */
	public boolean turnLeft() {
		if (turnCount < TurnModeUtil.getTurnMode(this.turnMode, this.speed)) {
			return false;
		}
		// TODO: Chagne unit facing and position to match.

		turnCount = 1;
		return true;
	}

	/**
	 * Turn the unit to the right and move one hex forward. This will change the
	 * facing of the unit to (relative) direction 5 and then move it into the adjacent
	 * hex in (relative) direction 1.
	 * This is only possible if the unit has fulfilled its turn mode.
	 * @return True if the turn was possible, false otherwise.
	 */
	public boolean turnRight() {
		if (turnCount < TurnModeUtil.getTurnMode(turnMode, speed)) {
			return false;
		}
		// TODO: Change unit facing and position to match.

		turnCount = 1;
		return true;
	}

	/**
	 * Move the unit a single hex forward, placing it in the adjacent hex
	 * in (relative) direction 1 without changing facing.
	 * @return
	 */
	public boolean goStraight() {

		// TODO: Change unit position to match.
		sideslipCount++;
		turnCount++;

		return true;
	}

	/**
	 * Change the facing of the unit without moving it.
	 * 
	 * @param absoluteFacing
	 *            The new facing of the unit with respect to the map.
	 * @return True if the maneuver is possible, false otherwise.
	 */
	public boolean performHet(int absoluteFacing) {

		return true;
	}
}
