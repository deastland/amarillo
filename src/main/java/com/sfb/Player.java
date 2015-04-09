package com.sfb;

import com.sfb.properties.Faction;

public class Player {

	private String name = null;
	private Faction faction = null;
	
	public Player() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	
}
