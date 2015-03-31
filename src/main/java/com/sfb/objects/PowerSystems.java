package com.sfb.objects;

public class PowerSystems {

	private int lwarp = 0;
	private int rwarp = 0;
	private int cwarp = 0;
	private int impulse = 0;
	private int apr = 0;
	private int awr = 0;
	private int battery = 0;
	
	private int availableLWarp = 0;
	private int availableRWarp = 0;
	private int availableCWarp = 0;
	private int availableImpulse = 0;
	private int availableApr = 0;
	private int availableAwr = 0;
	private int availableBattery = 0;
	
	private int batteryPower = 0;
	private int reserveWarp = 0;
	private int reservePower = 0;
	
	public PowerSystems() {}
	
	public int getLwarp() {
		return lwarp;
	}
	public void setLwarp(int lwarp) {
		this.lwarp = lwarp;
	}
	public int getRwarp() {
		return rwarp;
	}
	public void setRwarp(int rwarp) {
		this.rwarp = rwarp;
	}
	public int getCwarp() {
		return cwarp;
	}
	public void setCwarp(int cwarp) {
		this.cwarp = cwarp;
	}
	public int getImpulse() {
		return impulse;
	}
	public void setImpulse(int impulse) {
		this.impulse = impulse;
	}
	public int getApr() {
		return apr;
	}
	public void setApr(int apr) {
		this.apr = apr;
	}
	public int getAwr() {
		return awr;
	}
	public void setAwr(int awr) {
		this.awr = awr;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public int getAvailableLWarp() {
		return availableLWarp;
	}
	public void setAvailableLWarp(int availableLWarp) {
		this.availableLWarp = availableLWarp;
	}
	public int getAvailableRWarp() {
		return availableRWarp;
	}
	public void setAvailableRWarp(int availableRWarp) {
		this.availableRWarp = availableRWarp;
	}
	public int getAvailableCWarp() {
		return availableCWarp;
	}
	public void setAvailableCWarp(int availablecWarp) {
		this.availableCWarp = availablecWarp;
	}
	public int getAvailableApr() {
		return availableApr;
	}
	public void setAvailableApr(int availableApr) {
		this.availableApr = availableApr;
	}
	public int getAvailableAwr() {
		return availableAwr;
	}
	public int getAvailableImpulse() {
		return availableImpulse;
	}

	public void setAvailableImpulse(int availableImpulse) {
		this.availableImpulse = availableImpulse;
	}

	public void setAvailableAwr(int availableAwr) {
		this.availableAwr = availableAwr;
	}
	public int getAvailableBattery() {
		return availableBattery;
	}
	public void setAvailableBattery(int availableBattery) {
		this.availableBattery = availableBattery;
	}
	public int getBatteryPower() {
		return batteryPower;
	}
	public void setBatteryPower(int batteryPower) {
		this.batteryPower = batteryPower;
	}
	public int getReserveWarp() {
		return reserveWarp;
	}
	public void setReserveWarp(int reserveWarp) {
		this.reserveWarp = reserveWarp;
	}
	public int getReservePower() {
		return reservePower;
	}
	public void setReservePower(int reservePower) {
		this.reservePower = reservePower;
	}
	
	//////////
	// utility
	/////////
	
	public int getAvailablePower() {
		
		return availableLWarp + availableRWarp + availableCWarp + availableImpulse + availableApr + availableAwr;
	}
	
	// Removes the specified amount from battery power.
	// If the power requested doesn't exceed the 
	// available battery power, return true.
	// Otherwise return false.
	public boolean useBattery(int amount) {
		int remainingBattery = this.batteryPower - amount;
		
		if (remainingBattery >= 0) {
			this.batteryPower = remainingBattery;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean useReserveWarp(int amount) {
		if (reserveWarp < amount) {
			return false;
		}
		
		reserveWarp -= amount;
		return true;
	}
	
	public boolean userReservePower(int amount) {
		if (reservePower < amount) {
			return false;
		}
		reservePower -= amount;
		return true;
	}
	
	public boolean chargeBattery(int amount) {
		int finalBatteryPower = this.batteryPower + amount;
		
		if (finalBatteryPower > this.availableBattery) {
			return false;
		}
		
		this.batteryPower = finalBatteryPower;
		return true;
	}
	
	///////////////////////////////////////////
	//
	// DAMAGE SYSTEMS
	//
	///////////////////////////////////////////
	
	// If there is still a battery, destroy one and return true.
	// Otherwise return false.
	public boolean damageBattery() {
		if (availableBattery < 1) {
			return false;
		}

		// Remove a battery from the ship
		availableBattery--;
		
		// if all batteries were charged, 
		// lose 1 point of battery power.
		if (batteryPower > availableBattery) {
			batteryPower = availableBattery;
		}
		
		return true;
	}
	
	public boolean damageLWarp() {
		if (availableLWarp < 1) {
			return false;
		}
		
		availableLWarp--;
		return true;
	}

	public boolean damageRWarp() {
		if (availableRWarp < 1) {
			return false;
		}
		
		availableRWarp--;
		return true;
	}

	public boolean damageCWarp() {
		if (availableCWarp < 1) {
			return false;
		}
		
		availableCWarp--;
		return true;
	}
	
	public boolean damageImpulse() {
		if (availableImpulse < 1) {
			return false;
		}
		
		availableImpulse--;
		return true;
	}
	
	public boolean damageAwr() {
		if (availableAwr < 1) {
			return false;
		}
		
		availableAwr--;
		return true;
	}
	
	public boolean damageApr() {
		if (availableApr < 1) {
			return false;
		}
		
		availableApr--;
		return true;
	}
	
	
}
