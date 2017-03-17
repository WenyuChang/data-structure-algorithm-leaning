package wenyu.learning.Graphs.AdjacentList;

import java.util.ArrayList;
import java.util.List;

public class AdjListNode {
	public final String name;
	public final List<AdjListNode> adjacencies;
	public final List<Integer> edgesWeight;
	public AdjListNode(String name) {
		this.name = name;
		adjacencies = new ArrayList<AdjListNode>();
		edgesWeight = new ArrayList<Integer>();
	}
	public String toString() {
		return "V[" + name + "]";
	}
}