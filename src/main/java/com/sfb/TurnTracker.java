package com.sfb;

/**
 * This will be the main class that tracks time (and therefore events).
 * Possibly, it will be the main. Not sure yet.
 * 
 * @author deastland
 *
 */
public class TurnTracker {

	private static int impulse = 1;				// Total impulse count for the game thus far.
	
	public TurnTracker() {
		impulse = 0;
	}
	
	public int getImpulse() {
		return impulse;
	}
	
	public void nextImpulse() {
		impulse++;
	}
	
	// Return the current turn.
	public int getTurn() {
		return (int)((impulse - 1)/ Constants.IMPULSES_PER_TURN);
				
	}
}
