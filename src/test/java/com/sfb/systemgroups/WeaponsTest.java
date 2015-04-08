package com.sfb.systemgroups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

import com.sfb.weapons.Disruptor;
import com.sfb.weapons.Phaser1;
import com.sfb.weapons.Phaser2;
import com.sfb.weapons.Weapon;

public class WeaponsTest {

	@Test
	public void testWeapons() {
		
		Map<String, Object> values = new HashMap<>();
		values.put("weapons", getWeaponList());

		Weapons weapons = new Weapons();
		weapons.init(values);
		
		assertTrue(weapons.weapons.size() > 0);
		
		// 3 Phaser1 and 6 Phaser2 means a capacitor size of 9
		assertEquals(9, weapons.getAvailableCapacitor(), 0.24);
		
		// There are 4 torpedoes (disruptors)
		assertEquals(4, weapons.getAvailableTorps());
		
	}

	
	// Weapons on a D7 (no drones yet)
	public List<Weapon> getWeaponList() {
		List<Weapon> weaponList = new ArrayList<>();
		
		// Boom Phasers
		Phaser1 phaser1 = new Phaser1();
		Phaser1 phaser2 = new Phaser1();
		Phaser1 phaser3 = new Phaser1();
		phaser1.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		phaser2.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		phaser3.setArcs(new int[] {17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,13});
		weaponList.add(phaser1);
		weaponList.add(phaser2);
		weaponList.add(phaser3);
		
		// Wing Phasers
		Phaser2 phaser4 = new Phaser2();
		phaser4.setArcs(new int[] {17,18,19,20,21,22,23,24,1,9,10,11,12,13});
		weaponList.add(phaser4);
		Phaser2 phaser5 = new Phaser2();
		phaser5.setArcs(new int[] {1,2,3,4,5,6,7,8,9,13,14,15,16,17});
		weaponList.add(phaser5);
		
		// Waist Phasers
		Phaser2 phaser6 = new Phaser2();
		phaser6.setArcs(new int[] {13,14,15,16,17,18,19,20,21});
		weaponList.add(phaser6);
		Phaser2 phaser7 = new Phaser2();
		phaser7.setArcs(new int[] {13,14,15,16,17,18,19,20,21});
		weaponList.add(phaser7);
		Phaser2 phaser8 = new Phaser2();
		phaser8.setArcs(new int[] {5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser8);
		Phaser2 phaser9 = new Phaser2();
		phaser9.setArcs(new int[] {5,6,7,8,9,10,11,12,13});
		weaponList.add(phaser9);
		
		// Distruptors
		Disruptor disruptorA = new Disruptor(30);
		disruptorA.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		Disruptor disruptorB = new Disruptor(30);
		disruptorB.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		Disruptor disruptorC = new Disruptor(30);
		disruptorC.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		Disruptor disruptorD = new Disruptor(30);
		disruptorD.setArcs(new int[] {21,22,23,24,1,2,3,4,5});
		weaponList.add(disruptorA);
		weaponList.add(disruptorB);
		weaponList.add(disruptorC);
		weaponList.add(disruptorD);
		
		// Drones
		
		
		return weaponList;
	}

}
