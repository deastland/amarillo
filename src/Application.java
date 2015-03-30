
public class Application {

	public static void main(String[] args) {

		Thing thing1 = new Thing(14,10);
		Thing thing2 = new Thing(14,9);
		
		int facing = 1;
		
		int range = HexMap.getDistance(thing1, thing2);
		
		FiringArc firingArc = FiringArc.values()[HexMap.getAbsoluteArc(thing1, thing2)];
		
		ShieldFacing shieldFacing = ShieldFacing.values()[HexMap.getAbsoluteShieldFacing(thing1, thing2)];

		int trueBearing = HexMap.getBearing(thing1, thing2);
		
		System.out.println("RelativeBearing: " + HexMap.getRelativeBearing(trueBearing, facing));

		System.out.println("Distance: " + range);
		System.out.println("ARC: " + firingArc);
		System.out.println("SHLD: " + shieldFacing);
		System.out.println("Bearing: " + trueBearing);

	}
	
}
