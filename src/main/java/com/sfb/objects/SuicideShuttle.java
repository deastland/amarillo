package com.sfb.objects;

public class SuicideShuttle extends Shuttle implements Seeker {

	Unit target;						// The target of the suicide shuttle
	private int     warheadDamage;		// The damage dealt if the weapon hits its target.
	
	public SuicideShuttle(int warhead) {
		super();
		this.warheadDamage = warhead;
	}
	
	@Override
	public void setTarget(Unit target) {
		this.target = target;
	}

	@Override
	public Unit getTarget() {
		return this.target;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelfGuiding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSelfGuiding(boolean selfGuiding) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getEndurance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEndurance(int endurance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLaunchImpulse() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLaunchImpulse(int launchImpulse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWarheadDamage() {
		return this.warheadDamage;
	}

	@Override
	public void setWarheadDamage(int warheadDamage) {
		this.warheadDamage = warheadDamage;
	}

	@Override
	public void setController(Unit controllingUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int impact() {
		// TODO Auto-generated method stub
		return 0;
	}

}
