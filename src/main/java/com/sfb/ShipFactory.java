package com.sfb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sfb.objects.Ship;
import com.sfb.properties.TurnMode;
import com.sfb.weapons.Phaser1;
import com.sfb.weapons.Photon;
import com.sfb.weapons.Weapon;

public class ShipFactory {

	private static ShipFactory shipFactory = null;
	
	private ShipFactory() {
		
	}
	
	public static ShipFactory getInstance() {
		if (shipFactory != null) {
			return shipFactory;
		} else {
			shipFactory = new ShipFactory();
			return shipFactory;
		}
	}
	
	public Ship buildShip(String shipType, String shipName) {
		Ship theShip = null;
		
		
		//////////////////////////////
		// Get a test version of the Fed CA
		
		theShip = getTestShip();
		theShip.setName("USS Merrimac");
		//////////////////////////////
		
		
		
		return theShip;
	}
	
	// For now, let's create a test ship.
	private Ship getTestShip() {
		Map<String, Object> shipSpecs = new HashMap<String, Object>();
		Ship theShip = new Ship();
		// Turn mode D
		theShip.setTurnMode(TurnMode.D);

		shipSpecs.put("shield1", new Integer(30));
		shipSpecs.put("shield2", new Integer(24));
		shipSpecs.put("shield3", new Integer(24));
		shipSpecs.put("shield4", new Integer(22));
		shipSpecs.put("shield5", new Integer(22));
		shipSpecs.put("shield6", new Integer(22));
		shipSpecs.put("fhull", new Integer(12));
		shipSpecs.put("ahull", new Integer(4));
		shipSpecs.put("lwarp", new Integer(15));
		shipSpecs.put("rwarp", new Integer(15));
		shipSpecs.put("impulse", new Integer(4));
		shipSpecs.put("apr", new Integer(4));
		shipSpecs.put("battery", new Integer(4));
		shipSpecs.put("bridge", new Integer(2));
		shipSpecs.put("emer", new Integer(1));
		shipSpecs.put("auxcon", new Integer(2));
		shipSpecs.put("damcon", new int[] {4,4,2,2,0});
		shipSpecs.put("scanner", new int[] {0,0,2,3,6});
		shipSpecs.put("sensor", new int[] {6,4,2,1,0});
		shipSpecs.put("excess", new Integer(5));
		shipSpecs.put("trans", new Integer(3));
		shipSpecs.put("tractor", new Integer(3));
		shipSpecs.put("lab", new Integer(4));
		shipSpecs.put("probe", new Integer(1));
		shipSpecs.put("shuttle", new Integer(4));
		shipSpecs.put("crew", new Integer(40));
		shipSpecs.put("boardingparties", new Integer(8));
		shipSpecs.put("minimumcrew", new Integer(4));
		
		List<Weapon> weaponList = new LinkedList<>();
		// FH Phasers
		Phaser1 phaser1 = new Phaser1();
		phaser1.setArcs(new int[] {19,20,21,22,23,24,1,2,3,4,5,6,7});
		weaponList.add(phaser1);
		Phaser1 phaser2 = new Phaser1();
		phaser2.setArcs(new int[] {19,20,21,22,23,24,1,2,3,4,5,6,7});
		weaponList.add(phaser2);
		// LH Phasers
		Phaser1 phaser3 = new Phaser1();
		phaser3.setArcs(new int[] {13,14,15,16,17,18,19,20,21,22,23,24,1});
		weaponList.add(phaser3);
		Phaser1 phaser4 = new Phaser1();
		phaser4.setArcs(new int[] {13,14,15,16,17,18,19,20,21,22,23,24,1});
		weaponList.add(phaser4);
		// RH Phasers
		Phaser1 phaser5 = new Phaser1();
		phaser5.setArcs(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser5);
		Phaser1 phaser6 = new Phaser1();
		phaser6.setArcs(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser6);
		
		// Photons
		Photon photonA = new Photon();
		photonA.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonA);
		Photon photonB = new Photon();
		photonB.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonB);
		Photon photonC = new Photon();
		photonC.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonC);
		Photon photonD = new Photon();
		photonD.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonD);
		
		shipSpecs.put("weapons", weaponList);

		theShip.init(shipSpecs);
		
		return theShip;
	}
}
