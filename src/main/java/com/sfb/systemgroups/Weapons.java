package com.sfb.systemgroups;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sfb.weapons.Weapon;

public class Weapons implements Systems {

	List<Weapon> weapons = new LinkedList<>();

	@Override
	public void init(Map<String, Integer> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOriginalTotalBoxes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalBoxes() {
		// TODO Auto-generated method stub
		return 0;
	}

}
