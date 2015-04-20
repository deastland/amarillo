package com.sfb;

import java.util.ArrayList;
import java.util.List;

import com.sfb.objects.Seeker;
import com.sfb.objects.Unit;
import com.sfb.samples.SampleUsers;

public class Game {
	private List<Player> players     	= new ArrayList<>();	// The players
	private List<Unit>   units			= new ArrayList<>();	// The ships on the board.
	private List<Seeker> seekers		= new ArrayList<>();	// The seekers on the board.
	private boolean      inProgress		= true;					// When true, the game continues to run.
	
	

	public static void main(String[] args) {
		
		Game thisGame = new Game();
		TurnTracker.reset();
		
		// GET THE PLAYERS
		thisGame.loadPlayerList();
		
		// GET THE SHIPS
		// Now that you have all the players, load all the player
		// ships into the ship list.
		// The ships should have their starting hex and facing.
		for (Player player : thisGame.players) {
			thisGame.getUnits().addAll(player.getPlayerUnits());
		}
		
		// This is the turn cycle, it repeats until the game is over.
		while(thisGame.isInProgress()) {
			
			// 1. Energy Allocation
				// A. Send EA requests to all players/ships
				// B. Wait for responses.
				// C. Assign EA results to all ships.
			
			// 2. Speed Determination
				// A. Determine all ship speeds
				// B. Report all speeds to all players
			
			// 3. Sensor lock-on
				// A. For all ships with damaged sensors, roll for lock-on
				// B. Process results.
				// C. Report results to to players.
				// D. Wait for any appropriate responses (moving seeker control)
				// E. Process responses.
			
			// 4. Impulse Procedure (repeat 32 times)
			for (int i=0; i < 32; i++) {

				// A. Advance the impulse tracker. If it passes 32 - jump to 5
				TurnTracker.nextImpulse();
				
				// B. Send impulse count to players. and request ship actions
				
				// C. Wait for responses.
				
				// D. Once all responses are in, perform actions.
				
				// E. Send result of actions out to players and request any required interaction.
				
				// F. Wait for response.
				
				// G. Register the results of these actions.
				
				// H. Back up to A.
				
				
				
				//TODO: Perhaps this process should be driven from the web-side servers.
				// They can track each impulse, deciding to fire/move/whatever with each one.
				// As they submit their option for each impulse, this code will be notified
				// and proceed after everyone has "checked their options" for the impulse.
				
				// Then it sends an update of the map/situation up to the various clients
				// and resolves them.
				
				
				
				
			}
			
			// 5. Roll for Klingon Mutiny
			
			// 6. Boarding Party Combat
			
			// 7. End-of-turn (cleanUp)
			
			
			
			
			
			thisGame.setInProgress(false);
		}
	}
	
	private boolean isInProgress() {
		return this.inProgress;
	}
	
	private void setInProgress(boolean value) {
		this.inProgress = value;
	}
	
	private void loadPlayerList() {
		
		this.players = SampleUsers.getClassicPlayers();;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Seeker> getSeekers() {
		return seekers;
	}

	public void setSeekers(List<Seeker> seekers) {
		this.seekers = seekers;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
}
