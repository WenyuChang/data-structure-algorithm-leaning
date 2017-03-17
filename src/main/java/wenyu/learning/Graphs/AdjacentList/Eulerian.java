package wenyu.learning.Graphs.AdjacentList;

import java.util.HashMap;

/*
 * Eulerian Path is a path in graph that visits every edge exactly once. 
 * Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.
 * 
 * Logic of Undirected Eulerian Cycle: 
 * An undirected graph has Eulerian cycle if following two conditions are true.
 *    a) All vertices with non-zero degree are connected. We don’t care about vertices with zero 
 *       degree because they don’t belong to Eulerian Cycle or Path (we only consider all edges).
 *    b) All vertices have even degree.
 *    
 * Logic of Undirected Eulerian Path
 * An undirected graph has Eulerian Path if following two conditions are true.
 *    a) Same as condition (a) for Eulerian Cycle
 *    b) If only two vertices have odd degree and all other vertices have even degree. 
 *       Note that only one vertex with odd degree is not possible in an undirected graph 
 *       (sum of all degrees is always even in an undirected graph). In Eulerian path, each time 
 *       we visit a vertex v, we walk through two unvisited edges with one end point as v. 
 *       Therefore, all middle vertices in Eulerian Path must have even degree. 
 *       For Eulerian Cycle, any vertex can be middle vertex, therefore all vertices must 
 *       have even degree.
 *       
 * Logic of Directed Cycle:
 * A directed graph has an eulerian cycle if following conditions are true
 *    a) All vertices with nonzero degree belong to a single strongly connected component.
 *    2) In degree and out degree of every vertex is same.
 *    
 * Logic of Directed Path:
 * A directed graph has an eulerian cycle if following conditions are true
 *    a) All vertices with nonzero degree belong to a single strongly connected component.
 *    2) In degree and out degree of every vertex is same except two. 
 *       One vertex's in degree is one bigger than out degree.
 *       And another's out degree is one bigger than in degree.
 */
public class Eulerian {

	public static final String EULER_PATH="Euler_Path";
	public static final String EULER_CYCLE="Euler_Cycle";
	public static final String NON_EULER="Non_Euler";
	
	public static String undirectedCheck(AdjListNode[] graph) {
		//Step 1: check connectivity
		boolean connected = Connectivity.isUndirectedConnect(graph);
		if(!connected) {
			return NON_EULER;
		}
		
		//Step 2: Count degree
		HashMap<AdjListNode, Integer> map = new HashMap<AdjListNode, Integer>();
		for(int i=0; i<graph.length; i++) {
			map.put(graph[i], i);
		}
		int[] degree = new int[graph.length]; 
		for(int i=0; i<graph.length; i++) {
			degree[i] += graph[i].adjacencies.size();
			for(int j=0; j<graph[i].adjacencies.size(); j++) {
				degree[map.get(graph[i].adjacencies.get(j))]++;
			}
		}
		map.clear();
		
		int oddCount = 0;
		for(int i=0; i<degree.length; i++) {
			if(degree[i]%2 != 0) {
				oddCount++;
			}
		}
		
		if(oddCount == 0) {
			return EULER_CYCLE;
		} else if(oddCount == 2) {
			return EULER_PATH;
		} else {
			return NON_EULER;
		}
	}
	
	public static String DirectedCheck(AdjListNode[] graph) {
		// Step 1: check strong connectivity
		boolean connected = Connectivity.isDirectedStrongConnectivity(graph);
		if(!connected) {
			return NON_EULER;
		}
		
		//Step 2: Count degree
		HashMap<AdjListNode, Integer> map = new HashMap<AdjListNode, Integer>();
		for(int i=0; i<graph.length; i++) {
			map.put(graph[i], i);
		}
		int[] inDegree = new int[graph.length]; 
		for(int i=0; i<graph.length; i++) {
			for(int j=0; j<graph[i].adjacencies.size(); j++) {
				inDegree[map.get(graph[i].adjacencies.get(j))]++;
			}
		}
		map.clear();

		boolean ifPath = false;
		int flag = 0;
		for(int i=0; i<inDegree.length; i++) {
			if(inDegree[i] == graph[i].adjacencies.size()) {
				continue;
			} else if((!ifPath||flag==-1) && inDegree[i]-graph[i].adjacencies.size()==1) {
				ifPath = true;
				flag++;
			} else if((!ifPath||flag==1) && inDegree[i]-graph[i].adjacencies.size()==-1) {
				ifPath = true;
				flag--;
			} else {
				return NON_EULER;
			}
		}

		if(!ifPath && flag==0) {
			return EULER_CYCLE;
		} else if(ifPath && flag==0) {
			return EULER_PATH;
		} else {
			return NON_EULER;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
