package com.sfb;

import java.util.LinkedList;
import java.util.List;

import com.sfb.properties.Faction;

public class Main {

	private static List<Player> players     = new LinkedList<>();	// The players
	private static TurnTracker  turnTracker = new TurnTracker();	// Time tracker for everything.
	private static boolean      inProgress  = true;					// When true, the game continues to run.
	
	public Main() {
	}

	public static void main(String[] args) {
		Player player1 = new Player();
		player1.setName("Knosset");
		player1.setFaction(Faction.Federation);

		
		//TODO: Set up players
		//TODO: Set up map
		//TODO: Set up units
		//TODO: Links between players and ships.
		
		// the main loop
		while(inProgress) {
			turnTracker.nextImpulse();
			
			// Create a string with a list of all speeds that move each impulse
			int[] moveThisImpulse = Constants.IMPULSE_CHART[turnTracker.getLocalImpulse()];
			StringBuilder moveNow = new StringBuilder();
			for (int speed : moveThisImpulse) {
				moveNow.append(speed).append(" - ");
			}
			System.out.println(turnTracker.getTurn() + "|" + turnTracker.getLocalImpulse() + ": " + moveNow.toString());
						
			// On the 3rd turn, exit the main loop
			if (turnTracker.getTurn() == 3) {
				setInProgress(false);
			}
		}

	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public static boolean isInProgress() {
		return inProgress;
	}
	
	public static void setInProgress(boolean value) {
		inProgress = value;
	}
	
	
}
