package com.sfb.systemgroups;

import java.util.Map;

import com.sfb.objects.Unit;

public class Transporters implements Systems {

	private int trans;
	private int availableTrans;

	private Unit owningShip;
	
	public Transporters(Unit owner) {
		this.owningShip = owner;
	}
	
	@Override
	public void init(Map<String, Object> values) {
		availableTrans = trans = values.get("trans") == null ? 0 : (Integer)values.get("trans");
		
	}

	public int getAvailableTrans() {
		return availableTrans;
	}
	
	@Override
	public int fetchOriginalTotalBoxes() {
		return trans;
	}

	@Override
	public int fetchRemainingTotalBoxes() {
		return availableTrans;
	}

	public boolean damage() {
		if (availableTrans == 0) {
			return false;
		}
		
		availableTrans++;
		return true;
	}
	
	public boolean repair(int numberToRepair) {
		if (availableTrans + numberToRepair > trans) {
			return false;
		}
		
		availableTrans += numberToRepair;
		return true;
	}
	
	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit fetchOwningUnit() {
		return this.owningShip;
	}

}
