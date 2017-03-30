package graph;

public class DiGraph {
	
	private Vertex [] vertices;
	private LinkedList<Edge> edges;
	private int [] [] adjMatrix;
	
	/** Default constructor */
	public DiGraph(){
		vertices = null;
		edges = new LinkedList<Edge>();
		adjMatrix = null;
	}
	/** Construct a graph with v verices */
	public DiGraph(int v){
		vertices = new Vertex[v];
		for (int i = 0; i < v; i++){
			vertices[i] = new Vertex(i);
		}
		edges = new LinkedList<Edge>();
		adjMatrix = null;
	}
	
	/**
	 * Find a path from one node to another, if it exists, used breadth first methods
	 * @param start Starting node
	 * @param end Ending node
	 * @return A stack of Integers, containing the path to the end node
	 */
	public Stack<Integer> findPathBreadth(int start, int end){
		Stack<Integer> s = new Stack<Integer>();
		Queue<Vertex> q = new Queue<Vertex>();
		q.enQueue(vertices[start]);
		
		int [] parent = new int[vertices.length];
		for (int i = 0; i < parent.length; i++){
			parent[i] = -1;
		}
		boolean [] visited = new boolean[vertices.length + 1];
		for (int i = 0; i < visited.length; i++){
			visited[i] = false;
		}
		while(!q.isEmpty()){
			Vertex temp = q.deQueue();
			int cur = temp.getIndex();
			visited[cur] = true;
			if (cur == end){
				visited[visited.length-1] = true;
				break;
			}
			Node<Vertex> loop;
			for (loop = vertices[cur].getNeighbours().head(); loop != null; loop = loop.getNext()){
				if (!visited[loop.getData().getIndex()]) {
					parent[loop.getData().getIndex()] = cur;
					q.enQueue(loop.getData());
				}
			}
		}

		if (!visited[visited.length-1]){		// no path found
			s = new Stack<Integer>();
			s.push(new Integer(start));
			s.push(new Integer(-1));
			s.push(new Integer(end));
			s.reverse();
		}
		else {									// path found
			int k = end;
			s.push(new Integer(end));
			while (parent[k] != -1){
				s.push(new Integer(parent[k]));
				k = parent[k];
			}
		}
		return s;
	}
	
	
	/**
	 * Find a path from one node to another, if it exists, using deapth first methods
	 * @param start Starting node
	 * @param end Ending node
	 * @return A stack of Integers, containing the path to the end node
	 */
	public Stack<Integer> findPathDepth(int start, int end){
		Stack<Integer> s = new Stack<Integer>();
		boolean [] visited = new boolean[vertices.length + 1];
		for (int i = 0; i < visited.length; i++){
			visited[i] = false;
		}
		System.out.println("Finding path between " + start + " and " + end);
		s.push(new Integer(start));
		findPathUtilD(s, visited, start, end);
		if (visited[visited.length - 1] == false){
			s = new Stack<Integer>();
			s.push(new Integer(start));
			s.push(new Integer(-1));
			s.push(new Integer(end));
		}
		s.reverse();
		return s;
	}
	
	/** recursive function used to find a path from one node to another */
	private void findPathUtilD(Stack<Integer> s, boolean [] visited , int start, int end){
		int cur = s.getTop().intValue();
		if (cur == end) {
			visited[visited.length - 1] = true;
			return;
		}
		visited[cur] = true;
		
		Node<Vertex> temp = vertices[start].getNeighbours().head();
		
		for (temp = vertices[cur].getNeighbours().head(); temp != null; temp = temp.getNext()){
			int index = temp.getData().getIndex();
			if (!visited[index]){
				s.push(new Integer(index));
				findPathUtilD(s, visited, start, end);
				if (s.getTop() == end) {
					visited[visited.length - 1] = true;
					return;
				}
				s.pop();
			}
		}
	}
	
	/** checks if there is a cycle in the graph 
	 * Returns true if graph is cyclic*/
	public boolean isCyclic(){
		
		boolean [] visited = new boolean[vertices.length];
		boolean [] rStack = new boolean[vertices.length];
		for (int i = 0; i < visited.length; i++){
			visited[i] = false;
			rStack[i] = false;
		}
		
		for (int i = 0; i < visited.length; i++){
			if (isCycleCheck(i, visited, rStack)) return true;
		}
		return false;
	}
	/**
	 * Utility to check if the graph has a cycle, from http://www.geeksforgeeks.org
	 * @param v Index
	 * @param visited Visited array
	 * @param cycleStack CycleStack array
	 * @return
	 */
	private boolean isCycleCheck(int v, boolean [] visited, boolean [] rStack){
		if(visited[v] == false)
	    {
	        // Mark the current node as visited and part of recursion stack
	        visited[v] = true;
	        rStack[v] = true;
	 
	        // Recur for all the vertices adjacent to this vertex
	        Node<Vertex> temp = vertices[v].getNeighbours().head();
	        for (temp = vertices[v].getNeighbours().head(); temp != null; temp = temp.getNext()){
	        	if (!visited[temp.getData().getIndex()] && isCycleCheck(temp.getData().getIndex(), visited, rStack)) 
	        		return true;
	        	else if (rStack[temp.getData().getIndex()]) return true;
	        }
	 
	    }
	    rStack[v] = false;  // remove the vertex from recursion stack
	    return false;
	}

	public void addEdge(int start, int end){
		if (vertices != null){
			try {
				Node<Vertex> temp = vertices[start].getNeighbours().head();
				while (temp != null){
					if (temp.getData().getIndex() == end) return;
					temp = temp.getNext();
				}
				vertices[start].addNeightbour(vertices[end]);
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	
	/** adds edges based on the adjacency matrix*/
	private void updateEdges(){
		for (int i = 0; i < adjMatrix.length; i++){
			for (int j = 0; j < adjMatrix.length; j++){
				if (adjMatrix[i][j] == 1) {
					this.addEdge(i, j);
				}
			}
		}
	}
	
	// getters and setters
	public Vertex [] getVertices() {
		return vertices;
	}
	public void setVertices(Vertex [] vertices) {
		this.vertices = vertices;
	}
	public LinkedList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}
	public int [] [] getAdjMatrix() {
		return adjMatrix;
	}
	public void setAdjMatrix(int [] [] adjMatrix) {
		this.adjMatrix = adjMatrix;
		this.updateEdges();
	}
}
