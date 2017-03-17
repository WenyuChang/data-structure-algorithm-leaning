package wenyu.learning.Graphs.AdjacentList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearchUtils {
	public static void BreadthFirstSearch(AdjListNode[] graphList, boolean recursion) {
		if(graphList==null || graphList.length<=0) {
			return;
		}
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		for(int i=0; i<graphList.length; i++) {
			if(!visited.containsKey(graphList[i])) {
				visited.put(graphList[i], false);
			}
			
			if(!visited.get(graphList[i])) {
				if(recursion) {
					// No way to implement
				} else {
					BFSWithoutRecursion(graphList[i], visited);
				}
				System.out.println("END");
			}
			
		}
	}
	private static void BFSWithoutRecursion(AdjListNode currVertex, Map<AdjListNode, Boolean> visited) {
		Queue<AdjListNode> queue = new LinkedList<AdjListNode>();
		queue.offer(currVertex);
		
		while(!queue.isEmpty()) {
			AdjListNode vertex = queue.poll();
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				System.out.print(vertex + " --> ");
				visited.put(vertex, true);
				
				for(AdjListNode adj : vertex.adjacencies) {
					if(!visited.containsKey(adj) || !visited.get(adj)) {
						visited.put(adj, false);
						queue.offer(adj);
					}
				}
			}
		}
	}
}
