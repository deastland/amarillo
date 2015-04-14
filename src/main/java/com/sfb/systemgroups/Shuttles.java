package com.sfb.systemgroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sfb.objects.AdminShuttle;
import com.sfb.objects.Shuttle;
import com.sfb.objects.Unit;


public class Shuttles implements Systems {

	private int           shuttleBoxes			= 0;					// Total shuttle boxes on the SSD
	private int           availableShuttleBoxes	= 0;					// Current undamaged shuttle boxes
	private int           bays					= 1;					// Number of shuttle bays. Each can launch on it's own schedule.
	private List<Shuttle> shuttleInventory		= new ArrayList<>();	// List of the shuttles for this ship.

	private Unit owningUnit;
	
	//TODO: Launch bays and launch rate.
	
	public Shuttles(Unit owner) {
		this.owningUnit = owner;
	}
	
	public void init(int numberOfShuttles) {
		availableShuttleBoxes = shuttleBoxes = numberOfShuttles;
	}
	
	@Override
	public void init(Map<String, Object> values) {
		availableShuttleBoxes = shuttleBoxes = values.get("shuttle") == null ? 0 : (Integer)values.get("shuttle");
		// Create a new shuttle for each shuttle box on the ship.
		for (int i = 0; i < shuttleBoxes; i++) {
			Shuttle adminShuttle = new AdminShuttle();
			adminShuttle.setName("AdminShuttle" + i);
			shuttleInventory.add(adminShuttle);
		}
	}
	
	public List<Shuttle> getShuttleInventory() {
		return this.shuttleInventory;
	}
	
	public int getBays() {
		return this.bays;
	}
	
	//TODO: Implement shuttle/fighter stuff.
	
	@Override
	public int fetchOriginalTotalBoxes() {
		return shuttleBoxes;
	}
	
	@Override
	public int fetchRemainingTotalBoxes() {
		return availableShuttleBoxes;
	}
	
	/**
	 * Launch the specified shuttle. If an admin shuttle, it will exit the bay as normal.
	 * However, it could be a scatterpack or suicide shuttle, in which case I will need
	 * different logic.
	 * 
	 * @param shuttleNumber
	 * @param speed
	 * @param facing
	 * @return
	 */
	public Shuttle launchShuttle(int shuttleNumber, int speed, int facing) {
		if (shuttleInventory.get(shuttleNumber) instanceof AdminShuttle) {
			Shuttle newShuttle = shuttleInventory.remove(shuttleNumber);
			newShuttle.setSpeed(speed);
			newShuttle.setFacing(facing);
			
			return newShuttle;
		}

		return null;
	}
	
	public boolean landShuttle(Shuttle shuttle) {
		if (shuttleInventory.size() < availableShuttleBoxes) {
			// Clean up the shuttle real-time stats
			shuttle.setCurrentSpeed(0);
			shuttle.setLocation(null);
			shuttle.setFacing(0);
			// Add the shuttle to the ship's shuttle inventory.
			shuttleInventory.add(shuttle);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Apply arming energy to a suicide shuttle. After 3 turns of arming,
	 * it becomes a seeking weapon.
	 * 
	 * @param shuttleNumber
	 * @return
	 */
	public boolean armSuicideShuttle(int shuttleNumber) {
		//TODO: implement this!
		
		return false;
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit fetchOwningUnit() {
		return this.owningUnit;
	}
}
