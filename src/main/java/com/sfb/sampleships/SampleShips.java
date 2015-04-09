package com.sfb.sampleships;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sfb.objects.Ship;
import com.sfb.properties.Faction;
import com.sfb.properties.TurnMode;
import com.sfb.weapons.Phaser2;
import com.sfb.weapons.Disruptor;
import com.sfb.weapons.Weapon;

/**
 * This object will have static methods that return the maps
 * used to initialize ships.
 * 
 * @author deastland
 *
 */
public class SampleShips {


	/**
	 * This map represents a Federation Heavy Cruiser (Constitution Class)
	 * @return A map with all the data for a Fed CA
	 */
	public static Map<String, Object> getFedCa() {
		Map<String, Object> shipSpecs = new HashMap<String, Object>();

		// Turn mode D
		shipSpecs.put("turnmode", TurnMode.D);
		
		shipSpecs.put("faction", Faction.Federation);
		shipSpecs.put("hull", "CA");
		shipSpecs.put("name", "USS Lexington");

		shipSpecs.put("shield1", new Integer(30));
		shipSpecs.put("shield2", new Integer(22));
		shipSpecs.put("shield3", new Integer(24));
		shipSpecs.put("shield4", new Integer(22));
		shipSpecs.put("shield5", new Integer(22));
		shipSpecs.put("shield6", new Integer(24));
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
		Phaser2 phaser1 = new Phaser2();
		phaser1.setArcs(new int[] {19,20,21,22,23,24,1,2,3,4,5,6,7});
		weaponList.add(phaser1);
		Phaser2 phaser2 = new Phaser2();
		phaser2.setArcs(new int[] {19,20,21,22,23,24,1,2,3,4,5,6,7});
		weaponList.add(phaser2);
		// LH Phasers
		Phaser2 phaser3 = new Phaser2();
		phaser3.setArcs(new int[] {13,14,15,16,17,18,19,20,21,22,23,24,1});
		weaponList.add(phaser3);
		Phaser2 phaser4 = new Phaser2();
		phaser4.setArcs(new int[] {13,14,15,16,17,18,19,20,21,22,23,24,1});
		weaponList.add(phaser4);
		// RH Phasers
		Phaser2 phaser5 = new Phaser2();
		phaser5.setArcs(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser5);
		Phaser2 phaser6 = new Phaser2();
		phaser6.setArcs(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser6);
		
		// Disruptors
		Disruptor photonA = new Disruptor();
		photonA.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonA);
		Disruptor photonB = new Disruptor();
		photonB.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonB);
		Disruptor photonC = new Disruptor();
		photonC.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonC);
		Disruptor photonD = new Disruptor();
		photonD.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(photonD);
		
		shipSpecs.put("weapons", weaponList);

		return shipSpecs;
	}

	/**
	 * This map represents a Klingon D7 (?? Class)
	 * @return A map with all the data for a Klingon D7
	 */
	public static Map<String, Object> getD7() {
		Map<String, Object> shipSpecs = new HashMap<String, Object>();

		shipSpecs.put("faction", Faction.Klingon);
		shipSpecs.put("hull", "D7");
		shipSpecs.put("name", "IKV Saber");
		shipSpecs.put("serviceyear", new Integer(120));
		shipSpecs.put("bpv", new Integer(135));
		
		// Turn mode D
		shipSpecs.put("turnmode", TurnMode.B);

		// Shields
		shipSpecs.put("shield1", new Integer(30));
		shipSpecs.put("shield2", new Integer(22));
		shipSpecs.put("shield3", new Integer(15));
		shipSpecs.put("shield4", new Integer(12));
		shipSpecs.put("shield5", new Integer(15));
		shipSpecs.put("shield6", new Integer(22));
		
		// Hull boxes
		shipSpecs.put("fhull", new Integer(4));
		shipSpecs.put("ahull", new Integer(7));
		
		// Power systems
		shipSpecs.put("lwarp", new Integer(15));
		shipSpecs.put("rwarp", new Integer(15));
		shipSpecs.put("impulse", new Integer(5));
		shipSpecs.put("apr", new Integer(4));
		shipSpecs.put("battery", new Integer(5));
		
		// Control Boxes
		shipSpecs.put("bridge", new Integer(2));
		shipSpecs.put("emer", new Integer(1));
		shipSpecs.put("auxcon", new Integer(2));
		shipSpecs.put("security", new Integer(2));
		
		// Special Functions
		shipSpecs.put("damcon", new int[] {4,4,2,2,0});
		shipSpecs.put("scanner", new int[] {0,0,1,3,5,9});
		shipSpecs.put("sensor", new int[] {6,6,5,3,1,0});
		shipSpecs.put("excess", new Integer(5));
		
		// Operations Systems
		shipSpecs.put("trans", new Integer(5));
		shipSpecs.put("tractor", new Integer(3));
		shipSpecs.put("lab", new Integer(4));
		
		// Probes
		shipSpecs.put("probe", new Integer(1));
		
		// Shuttles
		shipSpecs.put("shuttle", new Integer(4));
		
		// Crew
		shipSpecs.put("crew", new Integer(45));
		shipSpecs.put("boardingparties", new Integer(10));
		shipSpecs.put("minimumcrew", new Integer(4));
		
		
		List<Weapon> weaponList = new LinkedList<>();
		
		// Boom Phasers
		Phaser2 phaser1 = new Phaser2();
		phaser1.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser1);
		Phaser2 phaser2 = new Phaser2();
		phaser2.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser2);
		Phaser2 phaser3 = new Phaser2();
		phaser3.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser3);

		// Left Wing Phaser
		Phaser2 phaser4 = new Phaser2();
		phaser4.setArcs(new int[] {17,18,19,20,21,22,23,24,1,9,10,11,12,13});
		weaponList.add(phaser4);

		// Right Wing Phaser
		Phaser2 phaser5 = new Phaser2();
		phaser5.setArcs(new int[] {1,2,3,4,5,6,7,8,9,13,14,15,16,17});
		weaponList.add(phaser5);
		
		// Left Waist Phasers
		Phaser2 phaser6 = new Phaser2();
		phaser6.setArcs(new int[] {13,14,15,16,17,18,19,20,21});
		weaponList.add(phaser6);
		Phaser2 phaser7 = new Phaser2();
		phaser7.setArcs(new int[] {13,14,15,16,17,18,19,20,21});
		weaponList.add(phaser7);
		
		// Right Waist Phasers
		Phaser2 phaser8 = new Phaser2();
		phaser8.setArcs(new int[] {5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser8);
		Phaser2 phaser9 = new Phaser2();
		phaser9.setArcs(new int[] {5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser9);
		
		// Disruptors
		Disruptor disrA = new Disruptor();
		disrA.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrA);
		Disruptor disrB = new Disruptor();
		disrB.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrB);
		Disruptor disrC = new Disruptor();
		disrC.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrC);
		Disruptor disrD = new Disruptor();
		disrD.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrD);
		
		shipSpecs.put("weapons", weaponList);

		return shipSpecs;
	}
	
	/**
	 * This map represents a Klingon D7 (?? Class)
	 * @return A map with all the data for a Klingon D7
	 */
	public static Map<String, Object> getF5() {
		Map<String, Object> shipSpecs = new HashMap<String, Object>();

		shipSpecs.put("faction", Faction.Klingon);
		shipSpecs.put("hull", "F5");
		shipSpecs.put("name", "IKV Dagger");
		shipSpecs.put("serviceyear", new Integer(120));
		shipSpecs.put("bpv", new Integer(90));
		
		// Turn mode D
		shipSpecs.put("turnmode", TurnMode.A);

		// Shields
		shipSpecs.put("shield1", new Integer(21));
		shipSpecs.put("shield2", new Integer(16));
		shipSpecs.put("shield3", new Integer(9));
		shipSpecs.put("shield4", new Integer(9));
		shipSpecs.put("shield5", new Integer(9));
		shipSpecs.put("shield6", new Integer(16));
		
		// Hull boxes
		shipSpecs.put("fhull", new Integer(2));
		shipSpecs.put("ahull", new Integer(5));
		
		//TODO: FINISH
		///////////////////////// FINISH REST LATER //////////////////////////
		
		// Power systems
		shipSpecs.put("lwarp", new Integer(15));
		shipSpecs.put("rwarp", new Integer(15));
		shipSpecs.put("impulse", new Integer(5));
		shipSpecs.put("apr", new Integer(4));
		shipSpecs.put("battery", new Integer(5));
		
		// Control Boxes
		shipSpecs.put("bridge", new Integer(2));
		shipSpecs.put("emer", new Integer(1));
		shipSpecs.put("auxcon", new Integer(2));
		shipSpecs.put("security", new Integer(2));
		
		// Special Functions
		shipSpecs.put("damcon", new int[] {4,4,2,2,0});
		shipSpecs.put("scanner", new int[] {0,0,1,3,5,9});
		shipSpecs.put("sensor", new int[] {6,6,5,3,1,0});
		shipSpecs.put("excess", new Integer(5));
		
		// Operations Systems
		shipSpecs.put("trans", new Integer(5));
		shipSpecs.put("tractor", new Integer(3));
		shipSpecs.put("lab", new Integer(4));
		
		// Probes
		shipSpecs.put("probe", new Integer(1));
		
		// Shuttles
		shipSpecs.put("shuttle", new Integer(4));
		
		// Crew
		shipSpecs.put("crew", new Integer(45));
		shipSpecs.put("boardingparties", new Integer(10));
		shipSpecs.put("minimumcrew", new Integer(4));
		
		
		List<Weapon> weaponList = new LinkedList<>();
		
		// Boom Phasers
		Phaser2 phaser1 = new Phaser2();
		phaser1.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser1);
		Phaser2 phaser2 = new Phaser2();
		phaser2.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser2);
		Phaser2 phaser3 = new Phaser2();
		phaser3.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser3);

		// Left Wing Phaser
		Phaser2 phaser4 = new Phaser2();
		phaser4.setArcs(new int[] {17,18,19,20,21,22,23,24,1,9,10,11,12,13});
		weaponList.add(phaser4);

		// Right Wing Phaser
		Phaser2 phaser5 = new Phaser2();
		phaser5.setArcs(new int[] {1,2,3,4,5,6,7,8,9,13,14,15,16,17});
		weaponList.add(phaser5);
		
		// Left Waist Phasers
		Phaser2 phaser6 = new Phaser2();
		phaser6.setArcs(new int[] {13,14,15,16,17,18,19,20,21});
		weaponList.add(phaser6);
		Phaser2 phaser7 = new Phaser2();
		phaser7.setArcs(new int[] {13,14,15,16,17,18,19,20,21});
		weaponList.add(phaser7);
		
		// Right Waist Phasers
		Phaser2 phaser8 = new Phaser2();
		phaser8.setArcs(new int[] {5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser8);
		Phaser2 phaser9 = new Phaser2();
		phaser9.setArcs(new int[] {5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser9);
		
		// Disruptors
		Disruptor disrA = new Disruptor();
		disrA.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrA);
		Disruptor disrB = new Disruptor();
		disrB.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrB);
		Disruptor disrC = new Disruptor();
		disrC.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrC);
		Disruptor disrD = new Disruptor();
		disrD.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disrD);
		
		shipSpecs.put("weapons", weaponList);

		return shipSpecs;
	}
}
