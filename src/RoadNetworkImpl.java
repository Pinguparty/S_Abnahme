import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RoadNetworkImpl implements RoadNetwork {
	
	private final String PATH = ""; //text files are in the root directory of this project
	
	private Graph graph;
	
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

	/* Print the MST based on the algorithm of Prim
	 */
	@Override
	public void printMST(City root) {
		graph.printMST(root);
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
}
	
