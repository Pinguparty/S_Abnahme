
public class Test {

	public static void main(String[] args) {
		RoadNetworkImpl impl = new RoadNetworkImpl();
		
		System.out.println("Ausgabe 'printMST'");
		impl.printMST(new City(1, "San Francisco"));
		System.out.println("______________________");
		System.out.println("Ausgabe 'printAdj'");
		impl.printAdj(new City(1, "San Francisco"));
	}
}
