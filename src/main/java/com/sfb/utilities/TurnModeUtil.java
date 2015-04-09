package com.sfb.utilities;

import com.sfb.properties.TurnMode;

public class TurnModeUtil {

	private static int[] a = new int[] {1,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,5,5,5,5,5,5};
	private static int[] b = new int[] {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6};
	private static int[] c = new int[] {1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,6};
	private static int[] d = new int[] {1,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6};
	private static int[] e = new int[] {1,1,1,1,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,7,7,7};
	
	private static int[] fighter = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	TurnModeUtil() {
		
	}
	
//	public static int[] getTurnMode(String type) {
//		switch(type) {
//		case "a":
//		case "A":
//			return a;
//		case "b":
//		case "B":
//			return b;
//		case "c":
//		case "C":
//			return c;
//		case "d":
//		case "D":
//			return d;
//		case "e":
//		case "E":
//			return e;
//		default:
//			break;
//		}
//		
//		return new int[] {};
//	}
//	
//	public static int[] getFighterTurnMode() {
//		return fighter;
//	}
	
	/**
	 * Fetch the turn mode for a unit with a given turn mode and given speed.
	 * @param turnMode The TurnMode of the unit.
	 * @param speed The speed of the unit.
	 * @return The number of hexes the unit must move before it can turn (turn mode)
	 */
	public static int getTurnMode(TurnMode turnMode, int speed) {
		switch(turnMode) {
		case A:
			return a[speed];
		case B:
			return b[speed];
		case C:
			return c[speed];
		case D:
			return d[speed];
		case E:
			return e[speed];
		case X:
			return fighter[speed];
		default:
			return 99;
		}
		
	}

}
