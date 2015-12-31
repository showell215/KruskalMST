package mst.support_structures.adj_list;
import java.util.Formatter;

/**
 * Class for maintaining a linked list of edge records for this vertex.
 * Records are arranged in ascending order by vertex identifier.
 * @author Steven Howell (schowel2)
 *
 */
public class EdgeRecordList {
	/** dummy head node */
	private EdgeRecord head;

	/**
	 * Constructor. Creates a new list with a dummy head node.
	 */
	public EdgeRecordList() {
		this.head = new EdgeRecord(-1, -1, -1);
	}
	/**
	 * Insert an edge record for edge e into the list for this vertex
	 * @param e the edge record to insert
	 */
	protected void insert(EdgeRecord e) {
		EdgeRecord prev = this.head;
		EdgeRecord current = this.head.getNext();
		while (current != null && current.getVertex2() <= e.getVertex2()) {
			prev = current;
			current = current.getNext();
		}
		//either current is null (end of list)
		//OR current is target. push current forward and e occupies current's spot
		e.setNext(current);
		prev.setNext(e);
	}
	/**
	 * Creates String representation of this EdgeRecordList, with all vertices adjacent
	 * to this vertix in ascending order, right justified in a field of length 4.
	 * @return string representation of this EdgeRecordList
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter f = new Formatter(sb);
		EdgeRecord p = this.head.getNext();
		while (p != null) {
			f.format("%4d ", p.getVertex2());
			p = p.getNext();
		}
		f.close();
		return sb.toString();
	}
}
