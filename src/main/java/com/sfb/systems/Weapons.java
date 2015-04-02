package com.sfb.systems;

import java.util.LinkedList;
import java.util.List;

import com.sfb.weapons.Weapon;

public class Weapons implements Systems {

	List<Weapon> weapons = new LinkedList<>();

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
