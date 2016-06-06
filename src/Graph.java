import java.util.ArrayList;
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
	
	/* Print the MST based on the algorithm of Prim
	 */
	public void printMST(City root) {
		//The given root object is most likely not in the graph, use findCity to get the corresponding object that is
		root = findCity(root.getId());
		
		//list of nodes that are part of the MST, add the root node
		List<City> citiesIncluded = new ArrayList<City>();
		citiesIncluded.add(root);
		
		//Set of Roads that is connected to exactly one node included in citiesIncluded, sorted by time
		TreeSet<Road> possibleRoads = new TreeSet<Road>();
		possibleRoads.addAll(this.get(root));
		

		Road road;
		City city;
		int size = this.keySet().size();
		while (citiesIncluded.size() < size) {
			road = possibleRoads.first();
			
			//Get the node that is not part of the MST yet
			city = findCity(road.getFrom());
			if (citiesIncluded.contains(city)) city = findCity(road.getTo());
			
			/* update possibleRoads:
			 * add all roads from city if they are not yet included
			 * if they are, remove them instead since these roads now connect two cities that are already part of the MST (and thus would create a circle)
			 */
			for (Road r : this.get(city)) {
				if (!possibleRoads.contains(r)) {
					possibleRoads.add(r);
				} else {
					possibleRoads.remove(r);
				}
			}
			
			//add the node to the MST
			citiesIncluded.add(city);
			
			//print out the edge along with the two nodes it connects
			System.out.println(findCity(road.getFrom()).toString() + " - " + findCity(road.getTo()).toString());
		}
	}
	public void printAdj(City root)
	{
		for(City c : keySet()){
			System.out.print(c.toString() + ":	");
			for(Road r: get(c)){
				if (r.getFrom() == c.getId()){
					System.out.print(findCity(r.getTo()).toString() + ", ");
				}
				else{
					System.out.print(findCity(r.getFrom()).toString() + ", ");
				}
			}
			System.out.println("");
		}
	}
}
