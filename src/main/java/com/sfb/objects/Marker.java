package com.sfb.objects;

import com.sfb.properties.Location;

// This represents any marker on the map.
// All markers have a location, if nothing else.
public class Marker {
	
	// Location is a value representing
	// The X,Y coordinates on the hex map
	// where the thing can be found.
	private Location location = new Location(0,0);
	
	public Marker() {}

	public Marker(int x, int y) {
		this.location = new Location(x, y);
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	
}
