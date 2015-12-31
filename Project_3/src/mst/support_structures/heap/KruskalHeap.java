package mst.support_structures.heap;

import java.util.Formatter;

import mst.support_structures.adj_list.EdgeRecord;


/**
 * Class defining the Heap structure. Used to represent a sorted edge set of a graph.
 * Utilizes an array representation.
 * @author Steven Howell (schowel2)
 *
 */
public class KruskalHeap {
	/** The max number of edges in the graph */
	private static final int MAX_EDGES = 5000;
	/** Array to hold edges */
	private EdgeRecord[] edgeArray;
	/** The number of edges in the heap */
	private int size;
	
	/**
	 * Initializes the edge array and sets size to 0.
	 */
	public KruskalHeap() {
		this.edgeArray = new EdgeRecord[MAX_EDGES];
		this.size = 0;
	}
	/**
	 * Returns the edge record array for this heap
	 * @return the edge record array for this heap
	 */
	public EdgeRecord[] getEdgeArray() {
		return this.edgeArray;
	}
	/**
	 * Get the size of the heap
	 * @return the size of the heap
	 */
	public int getSize() {
		return this.size;
	}
	/**
	 * Insert a new edge record into the heap
	 * @param e the edge record to insert
	 */
	public void insert(EdgeRecord e) {
		//insert in position - 1 of array
		edgeArray[size++] = e;
		upHeap(size - 1);
	}
	/**
	 * Used in conjunction with insert, ensures that the heap is sorted by continually
	 * swapping parent with child until heap property is restored.
	 * @param i the index to start the upheap
	 */
	private void upHeap(int i) {
		if (i > 0) {
			//key == weight
			EdgeRecord child = edgeArray[i];
			EdgeRecord parent = edgeArray[(i - 1)/2];
			if (parent.getWeight() > child.getWeight()) {
				//swap
				edgeArray[i] = parent;
				edgeArray[(i - 1)/2] = child;
				upHeap((i - 1)/2);
			}
		}
		
	}
	/**
	 * Deletes and returns the minimum weight edge from the heap.
	 * @return the minimum edge from the heap
	 */
	public EdgeRecord deleteMin() {
		EdgeRecord e = edgeArray[0];
		size--;
		edgeArray[0] = edgeArray[size];
		downHeap(0);
		return e;
	}
	/**
	 * Used in conjunction with deleteMin, ensures that the heap is sorted by continually
	 * swapping parent and child until property is restored. 
	 * @param i the index to start the downheap
	 */
	private void downHeap(int i) {
		int x = 0;
		if (2 * i + 2 < size) { //has 2 children
			if (edgeArray[2 * i + 2].getWeight() <= edgeArray[2 * i + 1].getWeight()) {
				x = 2 * i + 2;
			} else {
				x = 2 * i + 1;
			}
		} else if (2 * i + 1 < size) { //only left child
			x = 2 * i + 1;
		}
		//if x = 0, no children
		if (x > 0 && edgeArray[i].getWeight() > edgeArray[x].getWeight()) {
			//swap
			EdgeRecord temp = edgeArray[x];
			edgeArray[x] = edgeArray[i];
			edgeArray[i] = temp;
			downHeap(x);
		}
		
	}
	/**
	 * Creates a String representation of this heap.
	 * @return a String representation of this heap.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter f = new Formatter(sb);
		for (int i = 0; i < this.size; i++) {
			if (this.edgeArray[i].getVertex1() < this.edgeArray[i].getVertex2()) {
				f.format("%4d %4d\n", this.edgeArray[i].getVertex1(), this.edgeArray[i].getVertex2());
			} else {
				f.format("%4d %4d\n", this.edgeArray[i].getVertex2(), this.edgeArray[i].getVertex1());
			}
		}
		f.close();
		return sb.toString();
	}
}
