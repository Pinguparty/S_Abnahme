
public class Road implements Comparable<Road>, Buildable<Road> {
    private int from;
    private int to;
    private double time;
    
    public Road() {}
    
    public Road(int from, int to, double time) {
    	this.from = from;
    	this.to = to;
    	this.time = time;
    }
    
    public boolean connectsTo(int cityId) {
    	return from == cityId || to == cityId;
    }
	
    /* First criterion is the time-value.
     * Second criterion is the from-value.
     * Third criterion is the to-value.
     * If all three values are equal to those of the other-object, the method will return 0 and thus this object will be removed from the TreeSet since its a duplicate.
     */
	@Override
	public int compareTo(Road other) {
		if (getTime() < other.getTime()) return -1;
		if (getTime() > other.getTime()) return 1;
			
		if (getFrom() < other.getFrom()) return -1;
		if (getFrom() > other.getFrom()) return 1;
		
		if (getTo() < other.getTo()) return -1;
		if (getTo() > other.getTo()) return 1;
		
		return 0;
	}
	
	/* This implementation does not detect duplicates and may lead to different MSTs depending on the root node(city).
	 */
	/*@Override
	public int compareTo(Road other) {
		if (getTime() <= other.getTime()) return -1;
		else return 1;
	} */

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public double getTime() {
		return time;
	}

	@Override
	public Road build(String str) {
		String[] strParts = str.split(" ");
		return new Road(Integer.parseInt(strParts[0]), Integer.parseInt(strParts[1]), Double.parseDouble(strParts[2]));
	}
	
}
