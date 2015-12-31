package mst.main;
import java.io.PrintStream;
import java.util.Scanner;

import mst.support_structures.adj_list.EdgeRecord;
import mst.support_structures.adj_list.KruskalAdjacencyList;
import mst.support_structures.heap.KruskalHeap;
import mst.support_structures.mst_list.LinkedListMST;
import mst.support_structures.up_tree.KruskalUpTree;
import mst.util.IOUtilities;

/**
 * Main program for computing the MST of a graph using Kruskal's Algorithm.
 * Reads input and output files, and uses supporting classes to compute the MST.
 * @author Steven Howell (schowel2)
 *
 */
public class KruskalMST {
	/** Heap used to hold graph's Edges in ascending order */
	private KruskalHeap heap;
	/** The adjacency list containing edge records for all vertices in the graph */
	private KruskalAdjacencyList adjList;
	/** The UpTree used to keep track of connected components in executing algorithm */
	private KruskalUpTree upTree;
	/** Linked list to store the edges of the MST */
	private LinkedListMST mstList;
	/** The number of vertices in the graph */
	private int vertices = 0;
	
	public KruskalMST() {
		this.heap = new KruskalHeap();
		this.adjList = new KruskalAdjacencyList();
		//scanner for console input
		Scanner console = new Scanner(System.in);
		//prompt for input file
		Scanner input = IOUtilities.getFileScanner(console, "");
		//prompt for output file
		PrintStream output = IOUtilities.getOutputStream(console, "");
		//read input and create heap of edges and adjacency list
		readInput(input);
		//print heap before we alter it
		output.print(this.heap.toString());
		findKruskalMST();
		//print mst edges
		output.print(this.mstList.toString());
		//print adj list
		output.print(this.adjList.toString());
		input.close();
		console.close();
		output.close();
	}
	/**
	 * This method does the work of finding the MST for this graph. Uses the sorted heap of
	 * edges to determine the MST, and keeps track of connected components in the Up-Tree structure.
	 * Stores the resulting MST in a linked list representation.
	 */
	private void findKruskalMST() {
		this.upTree = new KruskalUpTree();
		this.mstList = new LinkedListMST();
		int components = this.vertices;
		//make a trivially connected component for each vertex
		for (int v = 0; v < components; v++) {
			this.upTree.makeSet(v);
		}
		//examine the minimum weight edge, and perform union operations until we have a 
		//single connected component containing all vertices. 
		while (components > 1) {
			EdgeRecord e = heap.deleteMin();
			EdgeRecord eCpy = new EdgeRecord(e.getVertex1(), e.getVertex2(), e.getWeight());
			int u = e.getVertex1();
			int v = e.getVertex2();
			int U = upTree.find(u);
			int V = upTree.find(v);
			if (U != V) {
				this.upTree.union(U, V);
				this.mstList.insert(eCpy);
				components--;
			}
		}	
	}
	/**
	 * Reads the input file, creates an edge record for each line, and inserts edge records
	 * into the adjacency list and heap.
	 * @param input the input file
	 */
	private void readInput(Scanner input) {
		//read edge record and insert into heap and adjacency list
		int v1, v2;
		double weight;
		while (input.hasNextLine()) {
			v1 = input.nextInt();
			if (v1 == -1) {
				break;
			}
			//check v1 and update value for n if greater than current n
			if (v1 > this.vertices) { this.vertices = v1;}
			v2 = input.nextInt();
			//check v2 and update value for n if greater than current n
			if (v2 > this.vertices) { this.vertices = v2;}
			weight = input.nextDouble();
			//create a new Edge Record with this data
			EdgeRecord e = new EdgeRecord(v1, v2, weight);
			//insert into heap
			this.heap.insert(e);
			//insert into adjacency list
			this.adjList.insert(e);
		}
		//increment vertex count to offset starting at 0
		this.vertices++;
	}
	/**
	 * Main method. Starts the program and creates a new instance of KruskalMST.
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		new KruskalMST();
	}
}
