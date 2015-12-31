package mst.support_structures.adj_list;
/**
 * Class defining an edge record for a graph. Contains four fields: vertex1, vertex2,
 * weight, and next.
 * @author Steven Howell (schowel2)
 *
 */
public class EdgeRecord {
	/** Vertex 1 for this edge */
	private int vertex1;
	/** Vertex 2 for this edge */
	private int vertex2;
	/** The weight of this edge */
	private double weight;
	/** The next edge */
	private EdgeRecord next;
	/**
	 * Constructor to create a new edge record.
	 * @param v1 vertex 1
	 * @param v2 vertex 2
	 * @param weight the weight of this edge
	 */
	public EdgeRecord(int v1, int v2, double weight) {
		this.vertex1 = v1;
		this.vertex2 = v2; 
		this.weight = weight; //key
		this.next = null;
	}

	/**
	 * @return the vertex1
	 */
	public int getVertex1() {
		return vertex1;
	}

	/**
	 * @param vertex1 the vertex1 to set
	 */
	public void setVertex1(int vertex1) {
		this.vertex1 = vertex1;
	}

	/**
	 * @return the vertex2
	 */
	public int getVertex2() {
		return vertex2;
	}

	/**
	 * @param vertex2 the vertex2 to set
	 */
	public void setVertex2(int vertex2) {
		this.vertex2 = vertex2;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the next
	 */
	public EdgeRecord getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(EdgeRecord next) {
		this.next = next;
	}
}
