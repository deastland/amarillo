
public class Thing {
	// will eventually be "Marker"
	
	private Location location;
	// Facing is a value representing
	// a direction that the thing is facing, relative
	// to the hex map. (1 is "due north" and 4 is "due south).
	//
	//      1
	//  21     5
	//      X
	//  17     9
	//     13
	//
	private int facing;
	
	public Thing() {}

	public Thing(int x, int y) {
		this.location = new Location(x, y);
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}
	
	// Given the true map-based bearing of a target
	// from this thing, adjust the bearing so that
	// it instead gives the bearing relative to the
	// front of the Thing.
	public int getRelativeBearing(int trueBearing) {
		int relativeBearing = 0;
		
		if (this.facing ==1) {
			relativeBearing = trueBearing;
		} else {
			relativeBearing = trueBearing - (relativeBearing - 1);
		}
		
		
		return relativeBearing;
	}
	
}
