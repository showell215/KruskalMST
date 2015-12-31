package mst.support_structures.mst_list;

import java.util.Formatter;

import mst.support_structures.adj_list.EdgeRecord;

/**
 * Class for maintaining a linked list of edges for the MST.
 * Records are arranged in ascending order by vertex identifier.
 * @author Steven Howell (schowel2)
 *
 */
public class LinkedListMST {
	/** dummy head node */
	private EdgeRecord mstListHead;
	
	/**
	 * Constructor. Creates a new list with a dummy head node.
	 */
	public LinkedListMST() {
		this.mstListHead = new EdgeRecord(-1, -1, -1);
	}

	/**
	 * Insert an edge record for edge e into the list.
	 * @param edge the edge record to insert
	 */
	public void insert(EdgeRecord edge) {
		//set 2 pointers for insertion
		EdgeRecord p = this.mstListHead;
		EdgeRecord c = this.mstListHead.getNext();
		int v1, v2;
		if (edge.getVertex1() < edge.getVertex2()) {
			v1 = edge.getVertex1();
			v2 = edge.getVertex2();
		} else {
			v1 = edge.getVertex2();
			v2 = edge.getVertex1();
		}
		//make a copy of the edge where greater vertex is always v1
		EdgeRecord edgeCpy = new EdgeRecord(v1, v2, edge.getWeight());
		while (c != null && c.getVertex1() < v1) {// less than
			
			p = c;
			c = c.getNext();
		}
		//if v1 values are equal, find correct insertion spot based on v2
		if (c != null && (c.getVertex1() == v1)) {
				while (c != null && c.getVertex1() == v1 && c.getVertex2() < v2) {
					p = c;
					c = c.getNext();
				}
		}
		edgeCpy.setNext(c);
		p.setNext(edgeCpy);
	}
	/**
	 * Creates String representation of this MST linked list,
	 * each vertex right justified in a field of length 4.
	 * @return string representation of this MST linked list
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter f = new Formatter(sb);
		EdgeRecord p = this.mstListHead.getNext();
		while (p != null) {
			f.format("%4d %4d\n", p.getVertex1(), p.getVertex2());
			p = p.getNext();
		}
		f.close();
		return sb.toString();
	}
}

