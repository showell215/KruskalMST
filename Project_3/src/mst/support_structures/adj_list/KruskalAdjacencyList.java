package mst.support_structures.adj_list;
/**
 * Class representing an adjacency list of a graph.
 * @author Steven Howell (schowel2)
 *
 */
public class KruskalAdjacencyList {
	/** Max number of vertices in the graph */
	static final int MAX_VERTICES = 1000;
	/** Array to hold the linked list of edge records for each vertex */
	private EdgeRecordList[] adjList;
	/** The size of the adjacency list */
	private int size = 0;

	/**
	 * Constructor initializes the array.
	 */
	public KruskalAdjacencyList() {
		adjList = new EdgeRecordList[MAX_VERTICES];
	}
	/**
	 * Insert a new edge record into the adjacency list.
	 * @param e the edge record to insert.
	 */
	public void insert(EdgeRecord e) {
		//insert into vertex1's list
		if (this.adjList[e.getVertex1()] == null) {
			this.adjList[e.getVertex1()] = new EdgeRecordList();
			this.size++;
		}
		this.adjList[e.getVertex1()].insert(e);		
		//insert into vertex2's list
		//to simplify insertion, create a copy with v1 and v2 swapped
		EdgeRecord eV2 = new EdgeRecord(e.getVertex2(), e.getVertex1(), e.getWeight());
		if (this.adjList[eV2.getVertex1()] == null) {
			this.adjList[eV2.getVertex1()] = new EdgeRecordList();
			this.size++;
		}
		this.adjList[eV2.getVertex1()].insert(eV2);
	}
	/**
	 * Create a String representation of this adjacency list.
	 * @return String representation of this adjacency list. 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();	
		for (int i = 0; i < this.size; i++) {
			sb.append(this.adjList[i].toString() + "\n");
		}
		return sb.toString();
	}
	/**
	 * Get the size of this adjacecny list
	 * @return the size of the adjacency list
	 */
	public int getSize() {
		return this.size;
	}
}
