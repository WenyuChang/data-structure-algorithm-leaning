package wenyu.learning.Graphs.AdjacentList;

import java.util.HashMap;
import java.util.Map;

/*
 * General Connectivity
 * All vertex are connected.
 * 
 * Logic: Undirected
 * 1) Random choose one vertex.
 * 2) DFS from this vertex.
 * 3) If all vertexes are visited after DFS, this graph is connected
 * Logic: Directed
 * 1) Loop vertex to DFS from
 * 2) If all vertexes are visited after this DFS, then return graph is connected.
 * 3) If not, continue to DFS from another vertex.
 * 
 * 
 * 
 * Strong Connectivity: Given a directed graph, find out whether the graph is strongly connected or not. 
 * A directed graph is strongly connected if there is a path between any two pair of vertices. 
 * 
 * Logic: undirected graph
 * we can just do a BFS and DFS starting from any vertex. 
 * If BFS or DFS visits all vertices, then the given undirected graph is connected. 
 *
 * Logic: directed graph
 * Following is Kosaraju’s DFS based simple algorithm that does two DFS traversals of graph.
 * 1) Initialize all vertices as not visited.
 * 2) Do a DFS traversal of graph starting from any arbitrary vertex v. 
 *    If DFS traversal doesn’t visit all vertices, then return false.
 * 3) Reverse all arcs (or find transpose or reverse of graph)
 * 4) Mark all vertices as not-visited in reversed graph.
 * 5) Do a DFS traversal of reversed graph starting from same vertex v (Same as step 2). 
 *    If DFS traversal doesn’t visit all vertices, then return false. Otherwise return true.
 * The idea is, if every node can be reached from a vertex v, and every node can reach v, then the 
 * graph is strongly connected. In step 2, we check if all vertices are reachable from v. In step 4, 
 * we check if all vertices can reach v (In reversed graph, if all vertices are reachable from v, then 
 * all vertices can reach v in original graph).
 */
public class Connectivity {

	public static boolean isUndirectedConnect(AdjListNode[] graph) {
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		DepthFirstSearchUtils.DFSWithoutRecursion(graph[0], visited, false);
		for(int i=0; i<graph.length; i++) {
			if(!visited.get(graph[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isDirectedConnect(AdjListNode[] graph) {
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		for(int i=0; i<graph.length; i++) {
			visited.clear();
			DepthFirstSearchUtils.DFSWithoutRecursion(graph[i], visited, false);
			int j;
			for(j=0; j<graph.length; j++) {
				if(!visited.get(graph[i])) {
					break;
				}
			}
			if(j==graph.length) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDirectedStrongConnectivity(AdjListNode[] graph) {
		// Step 1: DFS from one vertex
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		DepthFirstSearchUtils.DFSWithoutRecursion(graph[0], visited, false);
		for(int i=0; i<graph.length; i++) {
			if(!visited.get(graph[i])) {
				return false;
			}
		}
		
		// Step 2: Reverse all arcs
		AdjListNode[]  reversedGraph = GraphCloneUtils.reversedClone(graph);
		
		//Step 3: DFS reversed graph from one vertex
		visited.clear();
		DepthFirstSearchUtils.DFSWithoutRecursion(reversedGraph[0], visited, false);
		for(int i=0; i<graph.length; i++) {
			if(!visited.get(graph[i])) {
				return false;
			}
		}
		
		return true;
	}
}
