package com.sfb.systemgroups;

import java.util.Map;

public class OperationsSystems implements Systems {

	private int trans;
	private int tractor;
	private int lab;
	
	private int availableTrans;
	private int availableTractor;
	private int availableLab;
	
	public OperationsSystems() {}
	
	// Initialize the operations systems to the SSD values.
//	public void init(int transValue, int tractorValue, int labValue) {
//		availableTrans   = trans   = transValue;
//		availableTractor = tractor = tractorValue;
//		availableLab     = lab     = labValue;
//	}
	
	// Initialize the operations systems to the SSD values.
	@Override
	public void init(Map<String, Object> values) {
		availableTrans   = trans   = values.get("trans")   == null ? 0 : (Integer)values.get("trans");
		availableTractor = tractor = values.get("tractor") == null ? 0 : (Integer)values.get("tractor");
		availableLab     = lab     = values.get("lab")     == null ? 0 : (Integer)values.get("lab");
	}
	
	/// FETCH ///
	public int getAvailableTrans() {
		return availableTrans;
	}
	
	public int getAvailableTractor() {
		return availableTractor;
	}
	
	public int getAvailableLab() {
		return availableLab;
	}
	
	// Total operations boxes on the SSD (cripple calculations).
	@Override
	public int getOriginalTotalBoxes() {
		return trans + tractor + lab;
	}
	
	// Total current operations boxes (cripple calculations).
	@Override
	public int getTotalBoxes() {
		return availableTrans + availableTractor + availableLab;
	}
	
	/// DAMAGE ///
	public boolean damageTrans() {
		if (availableTrans == 0) {
			return false;
		}
		
		availableTrans--;
		return true;
	}
	
	public boolean damageTractor() {
		if (availableTractor == 0) {
			return false;
		}
		
		availableTractor--;
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
	
	public boolean repairTractor(int value) {
		if (availableTractor + value > tractor) {
			return false;
		}
		
		availableTractor += value;
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
	
}
