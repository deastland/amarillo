package com.sfb;

import com.sfb.objects.Ship;
import com.sfb.sampleships.SampleShips;

public class ShipFactory {

	private static ShipFactory shipFactory = null;
	
	private ShipFactory() {
		
	}
	
	public static ShipFactory getInstance() {
		if (shipFactory != null) {
			return shipFactory;
		} else {
			shipFactory = new ShipFactory();
			return shipFactory;
		}
	}
	
	public Ship buildShip(String shipType, String shipName) {
		Ship theShip = new Ship();
		
		
		//////////////////////////////
		// Get a test version of the Fed CA
		
		theShip.init(SampleShips.getFedCa());
		theShip.setName("USS Merrimac");
		//////////////////////////////
		
		
		
		return theShip;
	}
	
}
