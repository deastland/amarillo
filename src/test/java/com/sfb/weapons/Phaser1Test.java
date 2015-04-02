package com.sfb.weapons;

import static org.junit.Assert.*;

import org.junit.Test;

public class Phaser1Test {

	@Test 
	public void testFire() {
		Phaser1 phaser1 = new Phaser1();
		
		int range = 15;
		
		int damage = phaser1.rollDamage(range);
		
		System.out.println("Damage: " + damage);
		
		assertEquals(phaser1.getDacHitLocaiton(), "phaser");
	}

}
