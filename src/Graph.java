import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/* Graph whose nodes are represented by City objects.
 * Each City gets a TreeSet filled with all the roads that connect to it.
 * The roads are sorted by the time-value ascending. (If two roads have the same time, the next criterion is the from-value followed by the to-value)
 */
@SuppressWarnings("serial")
public class Graph extends HashMap<City, TreeSet<Road>> {

	public Graph(List<City> cities, List<Road> roads) {
		
		TreeSet<Road> roadsOfCity;
		for (City c : cities) {
			roadsOfCity = new TreeSet<Road>();
			for (Road r : roads) {
				if (r.connectsTo(c.getId())) {
					roadsOfCity.add(r);
				}
			}
			this.put(c, roadsOfCity);
		}
	}
	
	public City findCity(int id) {
		for (City c : keySet()) {
			if (c.getId() == id) return c;
		}
		
		return null;
	}

	/* Returns the city based on the from-value of r if it is not yet included in citiesIncluded.
	 * If it is the city based on the to-value of r is returned of it is not yet included.
	 * If both are included, null is returned.
	 */
	public City getNextCityIfNotIncluded(Road r, List<City> citiesIncluded) {
		City city;
		
		city = findCity(r.getFrom());
		if (!citiesIncluded.contains(city)) {
			return city;
		}
		
		city = findCity(r.getTo());
		if (!citiesIncluded.contains(city)) {
			return city;
		}
		
		return null;
	}
}
