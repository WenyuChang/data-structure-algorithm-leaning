package wenyu.learning.Graphs.AdjacentMatrix;

import java.util.Stack;

public class DepthFirstSearchUtils {
	public static void DepthFirstSearch(int[][] graphMatrix, boolean recursion, boolean print) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0 || graphMatrix.length!=graphMatrix[0].length) {
			return;
		}
		boolean[] visited = new boolean[graphMatrix.length];
		for(int i=0; i<graphMatrix.length; i++) {
			if(visited[i] != true) {
				if(recursion) {
					DFSWithRecursion(graphMatrix, i, visited, print);
				} else {
					DFSWithoutRecursion(graphMatrix, i, visited, print);
				}
				System.out.println("END");
			}
			
		}
	}
	protected static void DFSWithRecursion(int[][] graphMatrix, int currVertex, boolean[] visited, boolean print) {
		if(print) System.out.print("V" + currVertex + " --> ");
		visited[currVertex] = true;
		for(int i=0; i<graphMatrix[currVertex].length; i++) {
			if(graphMatrix[currVertex][i]>0 && visited[i] != true) {
				DFSWithRecursion(graphMatrix, i, visited, print);
			}
		}
	}
	protected static void DFSWithoutRecursion(int[][] graphMatrix, int currVertex, boolean[] visited, boolean print) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(currVertex);
		
		while(!stack.isEmpty()) {
			int vertex = stack.pop();
			if(visited[vertex] != true) {
				if(print) System.out.print("V" + vertex + " --> ");
				visited[vertex] = true;
			}
			
			for(int i=graphMatrix[vertex].length-1; i>=0 ;i--) {
				if(graphMatrix[vertex][i]>0 && visited[i] != true) {
					stack.push(i);
				}
			}
		}
	}
}
