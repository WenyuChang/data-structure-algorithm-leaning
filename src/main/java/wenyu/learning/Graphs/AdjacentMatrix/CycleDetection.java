package wenyu.learning.Graphs.AdjacentMatrix;

import java.util.Stack;

class CycleDetectionForAdjMatrix {
	/*
	 * Directed/Undirected graph loop can be find using DFS by detecting back edge
	 * if undirected:
	 * 1. detect self loop (not implement)
	 * 2. detect parallel loop (not implement)
	 * 3. using DFS to detect loop
	 */
	public static String findCycleFromGraph(int[][] graphMatrix, boolean directed) {
		Stack<Integer> path = new Stack<Integer>();
		boolean[] visited = new boolean[graphMatrix.length];
		for(int i=0; i<graphMatrix.length; i++) {
			if(visited[i] != true) {
				String cycle = DFSForDetectCycle(graphMatrix, i, visited, path, directed);
				if(cycle.length() != 0) {
					return cycle;
				}
			}
		}
		return "";
	}
	
	private static String DFSForDetectCycle(int[][] graphMatrix, int currVertex, boolean[] visited, Stack<Integer> path, boolean directed) {
		visited[currVertex] = true;
		int preVertex = (path.isEmpty())? -1 : path.peek();
		path.push(currVertex);
		for(int i=0; i<graphMatrix[currVertex].length; i++) {
			if(graphMatrix[currVertex][i]>0 && visited[i] != true) {
				return DFSForDetectCycle(graphMatrix, i, visited, path, directed);
			} else if(graphMatrix[currVertex][i]>0 && path.contains(i) && (directed || preVertex!=i)) {
				StringBuilder cycle = new StringBuilder();
				cycle.append(i + " <-- ");
				while(!path.isEmpty() && path.peek()!=i) {
					int vertex = path.pop();
					cycle.append(vertex + " <-- ");
				}
				cycle.append(i);
				return cycle.toString();
			}
		}
		path.pop();
		return "";
	}
	
	public static String findCompleteCycleFromGraph(int[][] graphMatrix, boolean directed) {
		Stack<Integer> path = new Stack<Integer>();
		boolean[] visited;
		String cycle = "";
		for(int i=0; i<graphMatrix.length; i++) {
			visited = new boolean[graphMatrix.length];
			cycle = DFSForDetectCompleteCycle(graphMatrix, i, i, visited, path, directed);
			if(cycle.length() != 0) {
				return cycle;
			}
		}
		return cycle;
	}
	
	private static String DFSForDetectCompleteCycle(int[][] graphMatrix, int starter, int currVertex, boolean[] visited, Stack<Integer> path, boolean directed) {
		visited[currVertex] = true;
		int preVertex = (path.isEmpty())? -1 : path.peek();
		path.push(currVertex);
		for(int i=0; i<graphMatrix[currVertex].length; i++) {
			if(graphMatrix[currVertex][i]>0 && visited[i] != true) {
				return DFSForDetectCompleteCycle(graphMatrix, starter, i, visited, path, directed);
			} else if(graphMatrix[currVertex][i]>0 && i==starter && (directed || preVertex!=i)) {
				path.push(i);
				return path.toString();
			}
		}
		path.pop();
		return "";
	}
}

public class CycleDetection {
	/*
	 * For Adjacent Matrix
	 */
	public static boolean hasCycle(int[][] graphMatrix, boolean directed) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0) {
			return false;
		}
		return findOneCycle(graphMatrix, directed).length() != 0;
	}
	public static String findOneCycle(int[][] graphMatrix, boolean directed) {
		if(graphMatrix==null || graphMatrix.length<=0 || graphMatrix[0].length<=0) {
			return "";
		}
		return CycleDetectionForAdjMatrix.findCycleFromGraph(graphMatrix, directed);
	}
	
	public static boolean hasCompleteCycle(int[][] graphMatrix, boolean directed) {
		return findCompleteCycles(graphMatrix, directed).length() != 0;
	}
	public static String findCompleteCycles(int[][] graphMatrix, boolean directed) {
		return CycleDetectionForAdjMatrix.findCompleteCycleFromGraph(graphMatrix, directed);
	}
	
	public static void main(String[] args) {
		int[][] graphMatrix = {{0, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 0, 0}, {1, 0, 0, 0}};
		AdjacentMatrixUtils.printGraph(graphMatrix);
		
		String cycle = findCompleteCycles(graphMatrix, true);
		//cycle = findOneCycle(graphMatrix, true);
		System.out.println(cycle);
	}
}
