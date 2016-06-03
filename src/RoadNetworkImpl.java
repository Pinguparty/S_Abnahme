import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class RoadNetworkImpl implements RoadNetwork {
	
	final String PATH = ""; //text files are in the root directory of this project
	
	public Graph graph;
	
	public RoadNetworkImpl() {
		//read cities.txt and roads.txt and create array lists with their objects
		List<City> cities = this.<City>readFileAndFillList(PATH+"cities.txt", new City());
		List<Road> roads = this.<Road>readFileAndFillList(PATH+"roads.txt", new Road());
		
		//Create the graph with the cities as nodes and the roads as edges
		graph = new Graph(cities, roads);
	}

	@Override
	public City findCity(int id) {
		return graph.findCity(id);
	}

	
	
	//Move to Graph? 
	/* Print the MST based on the algorithm of Prim
	 */
	@Override
	public void printMST(City root) {
		//The given root object is most likely not in the graph, use findCity to get the corresponding object that is
		root = findCity(root.getId());
		
		//list of nodes that are part of the MST, add the root node
		List<City> citiesIncluded = new ArrayList<City>();
		citiesIncluded.add(root);
		
		//the algorithm adds the shortest road that is connected to exactly one node included in citiesIncluded, this is a set with all possible roads
		TreeSet<Road> roadsToCheck = new TreeSet<Road>();
		roadsToCheck.addAll(graph.get(root));
		

		Road road;
		City city;
		int size = graph.keySet().size();
		while (citiesIncluded.size() < size) {
			
			//Reset to null so the following loop works correctly
			road = null;
			city = null;
			
			for (Road r : roadsToCheck) { //Sorted by time
				if (road == null) { //If the road has already been found do nothing
					city = graph.getNextCityIfNotIncluded(r, citiesIncluded);
					if (city != null) road = r; //city is only != null if one side of r connects to it, making city the next node and r the next road/edge
				}
			}
			
			//road = shortest edge to a node that is not yet included in the MST
			//city = the (new) node to be added to the MST
			
			
			//add the node to the MST
			citiesIncluded.add(city);
			//add all edges from the node to the pool of possible edges to new nodes
			roadsToCheck.addAll(graph.get(city));
			//remove the current edge, since it connects two nodes that are already in the MST
			roadsToCheck.remove(road);
			
			//print out the edge along with the two nodes it connects
			System.out.println(findCity(road.getFrom()).toString() + " - " + findCity(road.getTo()).toString());
		}
	}
	
	/* Reads the (text) file in the given path.
	 * Next, the builder-object of type T creates a new Object of type T for each line in the text file and puts them in an ArrayList<T>.
	 * May throw different FormatExceptions depending on the implementations of the T.build(String str) method.
	 */
	private <T extends Buildable<T>> ArrayList<T> readFileAndFillList(String path, T builder) {
		ArrayList<T> list = new ArrayList<T>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String ln = br.readLine();
			while (ln != null) {
				list.add(builder.build(ln));
				ln = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/*private ArrayList<City> readCities() {
		ArrayList<City> cities = new ArrayList<City>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(PATH + "cities.txt"));
			
			String cityLine = br.readLine();
			String[] cityLineParts;
			while (cityLine != null) {
				cityLineParts = cityLine.split("\"");
				cities.add(new City(Integer.parseInt(cityLineParts[0].substring(0, cityLineParts[0].length()-1)), cityLineParts[1]));	
				cityLine = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return cities;

	}
	
	private ArrayList<Road> readRoads() {
		ArrayList<Road> roads = new ArrayList<Road>();	
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PATH + "roads.txt"));
			String roadLine;
			roadLine = br.readLine();
			String[] roadLineParts;
			while (roadLine != null) {
				roadLineParts = roadLine.split(" ");
				roads.add(new Road(Integer.parseInt(roadLineParts[0]), Integer.parseInt(roadLineParts[1]), Double.parseDouble(roadLineParts[2])));	
				roadLine = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roads;
	}*/
}
	
