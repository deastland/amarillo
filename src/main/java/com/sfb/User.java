package com.sfb;

import com.sfb.properties.Faction;

public class User {

	private String userName = null;
	private Faction faction = null;
	
	public User() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	
}
