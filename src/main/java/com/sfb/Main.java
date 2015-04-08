package com.sfb;

import java.util.LinkedList;
import java.util.List;

public class Main {

	private List<User> users = new LinkedList<>();
	private static TurnTracker turnTracker = new TurnTracker();
	private static boolean inProgress = true;
	
	public Main() {
	}

	public static void main(String[] args) {
		//TODO: Set up users
		//TODO: Set up map
		//TODO: Set up units
		
		// the main loop
		while(inProgress) {
			turnTracker.nextImpulse();
			System.out.println(turnTracker.getTurn() + "|" + turnTracker.getImpulse());
						
			// On the 3rd turn, exit the main loop
			if (turnTracker.getTurn() == 3) {
				setInProgress(false);
			}
		}

	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public static boolean isInProgress() {
		return inProgress;
	}
	
	public static void setInProgress(boolean value) {
		inProgress = value;
	}
	
	
}
