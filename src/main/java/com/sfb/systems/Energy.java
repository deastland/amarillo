package com.sfb.systems;

import java.util.HashMap;
import java.util.Map;

import com.sfb.properties.WeaponArmingType;
import com.sfb.weapons.Weapon;

/**
 * This will be the energy allocation for a ship. It will indicate where all power generated is to be sent.
 * 
 * @author Daniel Eastland
 *
 */
public class Energy {

	// Housekeeping
	private double lifeSupport;
	private double fireControl;
	private double activateShields;
	
	// Shields
	private int generalReinforcement;
	private int[] specificReinforcement = new int[6];
	
	// Movement
	private double movement;
	private double highEnergyTurns;
	private double erraticManuvers;
	private double warpTacticalTurns;
	private int    impulseTacticalTurn;
	
	// Phasers
	private double phaserCapacitor;
	
	// Probes
	private int probes;
	
	// Tractors
	private int tractors;

	// Operations
	private double transporters;
	private int damageControl;
	
	// Reserve
	private double rechargeBatteries;
	
	// Weapons Map with <Weapon, Energy for Weapon>
	Map<Weapon, Double> armingEnergy = new HashMap<>();
	Map<Weapon, WeaponArmingType> armingType = new HashMap<>();
	//TODO: May be better to have a map of <Weapon, CustomObject> where custom object holds both arming energy AND armingType.
	
	//TODO: dang, how will I do this?

	public Energy() {
		
	}

	public double getLifeSupport() {
		return lifeSupport;
	}

	public void setLifeSupport(double lifeSupport) {
		this.lifeSupport = lifeSupport;
	}

	public double getFireControl() {
		return fireControl;
	}

	public void setFireControl(double fireControl) {
		this.fireControl = fireControl;
	}

	public double getActivateShields() {
		return activateShields;
	}

	public void setActivateShields(double activateShields) {
		this.activateShields = activateShields;
	}

	public int getGeneralReinforcement() {
		return generalReinforcement;
	}

	public void setGeneralReinforcement(int generalReinforcement) {
		this.generalReinforcement = generalReinforcement;
	}

	public int[] getSpecificReinforcement() {
		return specificReinforcement;
	}

	public void setSpecificReinforcement(int[] specificReinforcement) {
		this.specificReinforcement = specificReinforcement;
	}

	public double getMovement() {
		return movement;
	}

	public void setMovement(double movement) {
		this.movement = movement;
	}

	public double getHighEnergyTurns() {
		return highEnergyTurns;
	}

	public void setHighEnergyTurns(double highEnergyTurns) {
		this.highEnergyTurns = highEnergyTurns;
	}

	public double getErraticManuvers() {
		return erraticManuvers;
	}

	public void setErraticManuvers(double erraticManuvers) {
		this.erraticManuvers = erraticManuvers;
	}

	public double getWarpTacticalTurns() {
		return warpTacticalTurns;
	}

	public void setWarpTacticalTurns(double warpTacticalTurns) {
		this.warpTacticalTurns = warpTacticalTurns;
	}

	public int getImpulseTacticalTurn() {
		return impulseTacticalTurn;
	}

	public void setImpulseTacticalTurn(int impulseTacticalTurn) {
		this.impulseTacticalTurn = impulseTacticalTurn;
	}

	public double getPhaserCapacitor() {
		return phaserCapacitor;
	}

	public void setPhaserCapacitor(double phaserCapacitor) {
		this.phaserCapacitor = phaserCapacitor;
	}

	public int getProbes() {
		return probes;
	}

	public void setProbes(int probes) {
		this.probes = probes;
	}

	public double getTransporters() {
		return transporters;
	}

	public void setTransporters(double transporters) {
		this.transporters = transporters;
	}

	public int getTractors() {
		return tractors;
	}

	public void setTractors(int tractors) {
		this.tractors = tractors;
	}

	public int getDamageControl() {
		return damageControl;
	}

	public void setDamageControl(int damageControl) {
		this.damageControl = damageControl;
	}

	public double getRechargeBatteries() {
		return rechargeBatteries;
	}

	public void setRechargeBatteries(double rechargeBatteries) {
		this.rechargeBatteries = rechargeBatteries;
	}

	public Map<Weapon, Double> getArmingEnergy() {
		return armingEnergy;
	}

	public void setArmingEnergy(Map<Weapon, Double> armingEnergy) {
		this.armingEnergy = armingEnergy;
	}

	public Map<Weapon, WeaponArmingType> getArmingType() {
		return armingType;
	}

	public void setArmingType(Map<Weapon, WeaponArmingType> armingType) {
		this.armingType = armingType;
	}
	
}
