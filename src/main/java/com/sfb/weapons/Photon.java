package com.sfb.weapons;

public class Photon extends HitOrMissWeapon {

	private final static int[] hitChart = 
		{0,0,5,4,4,3,3,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	private final static int[] overloadHitChart =
		{6,6,5,4,4,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private final static int[] proximityHitChart = 
		{0,0,0,0,0,0,0,0,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
	
	@Override
	public int rollDamage(int range) {
		// TODO Auto-generated method stub
		return 0;
	}

}
