package com.sfb.systemgroups;

import java.util.Map;

public class Crew implements Systems {

	private int crewUnits = 0;
	private int minimumCrew = 0;
	private int boardingParties = 0;
	
	private int availableCrewUnits = 0;
	private int availableBoardingParties = 0;
	
	public Crew() {
		
	}
	
	public void init(Map<String, Object> values) {
		crewUnits       = values.get("crew")            == null ? 0 : (Integer)values.get("crew");
		boardingParties = values.get("boardingparties") == null ? 0 : (Integer)values.get("boardingparties");
		minimumCrew     = values.get("minimumcrew")     == null ? 0 : (Integer)values.get("minimumcrew");
		availableCrewUnits = crewUnits;
		availableBoardingParties = boardingParties;
	}
	
	/**
	 * Checks if the crew has dropped below the minimum and, therefore,
	 * the ship is running on a skeleton crew.
	 * @return True if the ship is below minimum crew, false otherwise.
	 */
	public boolean isSkeleton() {
		return availableCrewUnits < minimumCrew;
	}

	public int getAvailableCrewUnits() {
		return availableCrewUnits;
	}

	public void setAvailableCrewUnits(int availableCrewUnits) {
		this.availableCrewUnits = availableCrewUnits;
	}

	public int getAvailableBoardingParties() {
		return availableBoardingParties;
	}

	public void setAvailableBoardingParties(int availableBoardingParties) {
		this.availableBoardingParties = availableBoardingParties;
	}

	@Override
	public int getOriginalTotalBoxes() {
		return 0;
	}

	@Override
	public int getTotalBoxes() {
		return 0;
	}

	
	
}
