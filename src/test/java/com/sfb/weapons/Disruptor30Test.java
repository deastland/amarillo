package com.sfb.weapons;

import static org.junit.Assert.*;

import org.junit.Test;

public class Disruptor30Test {

	@Test
	public void testArming() {
		Disruptor disruptor = getStandardDisruptor();
		int range = 12;
		
		int damage = disruptor.fire(range);
		assertEquals(damage, 3);
		
		
		
	}
	
	
	
	private Disruptor getStandardDisruptor() {
		Disruptor disruptor = new Disruptor(30);
		disruptor.arm(2);
		
		return disruptor;
		
	}

}
