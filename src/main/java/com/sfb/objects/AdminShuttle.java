package com.sfb.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sfb.weapons.Phaser3;
import com.sfb.weapons.Weapon;

public class AdminShuttle extends Shuttle {


	public AdminShuttle() {
		setHull(6);
		setMaxSpeed(6);

		// Create a phaser 3 (360 arc) and put it with the shuttle weapons.
		Phaser3 phaser1 = new Phaser3();
		phaser1.setDesignator("1");
		phaser1.setArcs(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
		List<Weapon> weaponList = new ArrayList<>();
		weaponList.add(phaser1);
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("weapons", weaponList);

		getWeapons().init(values);
	}
	

}
