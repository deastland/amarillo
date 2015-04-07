package com.sfb.objects;

import com.sfb.weapons.Phaser3;

public class AdminShuttle extends Shuttle {
	
	private Phaser3 phaser = new Phaser3();
	
	public AdminShuttle() {
		phaser.setArcs(new int[] {0});
		
	}

}
