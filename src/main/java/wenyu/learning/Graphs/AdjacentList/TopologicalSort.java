package wenyu.learning.Graphs.AdjacentList;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TopologicalSort {
	public static final int UNVISITED = 0;
	public static final int VISITED = 1;
	public static void topologicalSort(AdjListNode[] graphList, boolean directed) {
		boolean hasCycle = CycleDetection.hasCycle(graphList, directed);
		if(!hasCycle) {
			// Find first vertex without any incomming edge
			Map<AdjListNode, Boolean> ifHasPreVertex = new HashMap<AdjListNode, Boolean>();
			for(AdjListNode vertex : graphList) {
				for(AdjListNode adj : vertex.adjacencies) {
					ifHasPreVertex.put(adj, true);
				}
			}
			
			AdjListNode nextVertex = null;
			for(AdjListNode vertex : graphList) {
				if(!ifHasPreVertex.containsKey(vertex) || !ifHasPreVertex.get(vertex)) {
					nextVertex = vertex;
					break;
				}
			}
			if(nextVertex == null) {
				System.out.println("Graph has loop...");
				return;
			}
			
			// using post DFS to get a post order stack
			Stack<AdjListNode> stack = DepthFirstSearchUtils.PostDepthFirstSearch(graphList, nextVertex);
			while(!stack.isEmpty()) {
				AdjListNode vertex = stack.pop();
				System.out.print(vertex + " --> ");
			}
			System.out.println("END");
		} else {
			System.out.println("Graph has loop...");
			return;
		}
	}
	
	public static void main(String[] args) throws Exception {
		AdjListNode[] graphList = AdjacentListUtils.genRandomDirectedGraph(3);
		AdjacentListUtils.printGraph(graphList);
		topologicalSort(graphList, true);
	}
}
