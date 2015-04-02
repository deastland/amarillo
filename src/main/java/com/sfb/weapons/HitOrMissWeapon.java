package com.sfb.weapons;

// These weapons simply hit or miss depending on a roll.
// They then do damage, often depending on the range.
public abstract class HitOrMissWeapon extends Weapon {

	// Chart is just the odds to hit (number to roll under) for a given range.
	private static final int[] hitChart = {};
	// Damage done on a hit at a given range when the weapon is armed as STANDARD.
	private static final int[] damageChart = {};
	// Damage done on a hit at a given range when the weapon is armed as OVERLOAD.
	private static final int[] overloadDamageChart = {};
	
	
}
