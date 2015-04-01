package com.sfb.systems;

import com.sfb.properties.ProbeArmingType;

public class Probes {

	private Probe[] probeArray;
	
	public Probes() {}
	
	// Create probe boxes equal to the number
	// of probes specified in the argument.
	public void init(int numberOfProbes) {
		probeArray = new Probe[numberOfProbes];
		for (int i=0; i < probeArray.length; i++) {
			probeArray[i] = new Probe();
		}
	}
	
	// return the number of non-destroyed probe boxes
	public int availableProbes() {
		int counter = 0;
		
		for (Probe probe : probeArray) {
			if (probe.isFunctional()) {
				counter++;
			}
		}
		
		return counter;
	}
	
	public int getOriginalTotalBoxes() {
		return probeArray.length;
	}
	
	public int getTotalBoxes() {
		return availableProbes();
	}
	
	public void setProbeType(int probeNumber, ProbeArmingType armingType) {
		probeArray[probeNumber].setProbeType(armingType);
	}
	
	public int arm(int probeNumber) {
		return probeArray[probeNumber].arm();
	}
	
	public boolean fire(int probeNumber) {
		return probeArray[probeNumber].fire();
	}
	
	public int getAvailableAmmo(int probeNumber) {
		return probeArray[probeNumber].getAvailableAmmo();
	}
	
	public boolean isReady(int probeNumber) {
		return probeArray[probeNumber].isReady();
	}
	
	public ProbeArmingType getArmingType(int num) {
		return probeArray[num].getArmingType();
	}

	public int getArmingTurn(int probeNumber) {
		return probeArray[probeNumber].getArmingTurn();
	}
	
	public boolean isFunctional(int num) {
		return probeArray[num].isFunctional();
	}
	
}
