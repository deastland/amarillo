package com.sfb.systemgroups;

import java.util.Map;

import com.sfb.objects.Unit;

public class OperationsSystems implements Systems {

	private int trans;
	private int lab;
	
	private int availableTrans;
	private int availableLab;
	
	private Unit owningUnit;
	
	public OperationsSystems(Unit owner) {
		this.owningUnit = owner;
	}
	
	// Initialize the operations systems to the SSD values.
	@Override
	public void init(Map<String, Object> values) {
		availableTrans   = trans   = values.get("trans")   == null ? 0 : (Integer)values.get("trans");
		availableLab     = lab     = values.get("lab")     == null ? 0 : (Integer)values.get("lab");
	}
	
	/// FETCH ///
	public int getAvailableTrans() {
		return availableTrans;
	}
	
	public int getAvailableLab() {
		return availableLab;
	}
	
	// Total operations boxes on the SSD (cripple calculations).
	@Override
	public int getOriginalTotalBoxes() {
		return trans + lab;
	}
	
	// Total current operations boxes (cripple calculations).
	@Override
	public int getTotalBoxes() {
		return availableTrans + availableLab;
	}
	
	/// DAMAGE ///
	public boolean damageTrans() {
		if (availableTrans == 0) {
			return false;
		}
		
		availableTrans--;
		return true;
	}
	
	public boolean damageLab() {
		if (availableLab == 0) {
			return false;
		}
		
		availableLab--;
		return true;
	}
	
	/// REPAIR ///
	public boolean repairTrans(int value) {
		if (availableTrans + value > trans) {
			return false;
		}
		
		availableTrans += value;
		return true;
	}
	
	public boolean repairLab(int value) {
		if (availableLab + value > lab) {
			return false;
		}
		
		availableLab += value;
		return true;
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit getOwningUnit() {
		return this.owningUnit;
	}
	
}
