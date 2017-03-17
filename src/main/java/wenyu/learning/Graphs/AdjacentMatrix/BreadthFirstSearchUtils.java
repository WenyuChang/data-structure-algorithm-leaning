package wenyu.learning.Graphs.AdjacentMatrix;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchUtils {
	public static void BreadthFirstSearch(int[][] graphMatrix, boolean recursion) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0 || graphMatrix.length!=graphMatrix[0].length) {
			return;
		}
		boolean[] visited = new boolean[graphMatrix.length];
		for(int i=0; i<graphMatrix.length; i++) {
			if(visited[i] != true) {
				if(recursion) {
					// Reference to the recursion traversal algorithm of trees
				} else {
					BFSWithoutRecursion(graphMatrix, i, visited);
				}
				System.out.println("END");
			}
			
		}
	}
	private static void BFSWithoutRecursion(int[][] graphMatrix, int currVertex, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(currVertex);
		
		while(!queue.isEmpty()) {
			int vertex = queue.poll();
			if(visited[vertex] != true) {
				System.out.print("V" + vertex + " --> ");
				visited[vertex] = true;
			
				for(int i=0; i<graphMatrix[vertex].length ;i++) {
					if(graphMatrix[vertex][i]>0 && visited[i] != true) {
						queue.offer(i);
					}
				}
			}
		}
	}
}
