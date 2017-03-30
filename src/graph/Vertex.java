package graph;

public class Vertex{
	
	private LinkedList<Vertex> neighbours;
	private int index;
	
	/** Construct a new vertex with index d */
	public Vertex(int d){
		index = d;
		neighbours = new LinkedList<Vertex>();
	}
	
	/** Add a new neightbour */
	public void addNeightbour(Vertex v){
		neighbours.addLast(v);
	}
	
	/** remove a neighbouring vertex from adjacency list */
	public void removeNeighbour(Vertex v){
		neighbours.remove(v);
	}
	
	// Getters and Setters
	public LinkedList<Vertex> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(LinkedList<Vertex> neighbours) {
		this.neighbours = neighbours;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
