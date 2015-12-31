package mst.support_structures.up_tree;
/**
 * Class defining structure and behaviors for Up-Tree structure. Utilizes an array
 * representation of Up-Trees.
 * Used to hold vertices in the MST algorithm.
 * @author Steven Howell (schowel2)
 *
 */
public class KruskalUpTree {
	/** The max number of vertices to be used */
	private static final int MAX_VERTICES = 1000;
	/** The array representation of Up-Tree nodes */
	private UpTreeNode[] upTreeArray;
	
	/**
	 * Constructor initializes the array of nodes.
	 */
	public KruskalUpTree() {
		upTreeArray = new UpTreeNode[MAX_VERTICES];
	}
	/**
	 * Performs a balanced union operation between two nodes s and t.
	 * @param s a node in the Up-Tree
	 * @param t a node in the Up-Tree
	 */
	public void union(int s, int t) {
		//balanced union: smaller tree points to larger tree
		if (Math.abs(upTreeArray[s].parentCount) >= Math.abs(upTreeArray[t].parentCount)) {
			//s is the larger tree
			upTreeArray[s].parentCount += upTreeArray[t].parentCount;
			//set child's parent count to s's index, as ptr
			upTreeArray[t].parentCount = s;
			//no return, we've changed the parent ptrs in array
		} else {
			//t is larger tree
			upTreeArray[t].parentCount += upTreeArray[s].parentCount;
			upTreeArray[s].parentCount = t;
		}
	}
	/**
	 * Gets the index of the root vertex of v
	 * @param v the vertex at which to begin the search
	 * @return the index of the root vertex of v
	 */
	public int find(int v) {
		while (upTreeArray[v].parentCount >= 0) {
			v = upTreeArray[v].parentCount;
		}
		return v;
	}
	/**
	 * Create a set consisting of this vertex.
	 * @param v the vertex to make a set with
	 */
	public void makeSet(int v) {
		upTreeArray[v] = new UpTreeNode();
	}
	/**
	 * Private class defining a node in the up tree.
	 * @author Steven Howell (schowel2)
	 *
	 */
	private class UpTreeNode {
		/** parentCount will be:
		 * 		-n, where n is the number of elements in the uptree, if a root
		 * 		integer representing the index of its parent, otherwise
		 */
		public int parentCount;
		/**
		 * Initialize this node to -1, showing only 1 element.
		 */
		public UpTreeNode() {
			this.parentCount = -1;
		}
		/**
		 * Creates a string reprseentation of this node
		 * @return a string representation of this node
		 */
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(parentCount);
			return sb.toString();
		}
	}
}
