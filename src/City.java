
public class City implements Buildable<City> {
	private int id;
	private String name;

	public City() {}

	public City(int id, String name) {
    	this.id = id;
    	this.name = name;
	}
	
	public String toString() {
		return (id < 10 ? "0" : "") + id + ":" + name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public City build(String str) {
		String[] strParts = str.split("\"");
		return new City(Integer.parseInt(strParts[0].substring(0, strParts[0].length()-1)), strParts[1]);	
	}
	
}
