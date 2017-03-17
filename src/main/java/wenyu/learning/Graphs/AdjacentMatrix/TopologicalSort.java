package wenyu.learning.Graphs.AdjacentMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import wenyu.learning.Graphs.AdjacentList.AdjacentListUtils;

public class TopologicalSort {
	public static final int UNVISITED = 0;
	public static final int VISITED = 1;
	
	public static void topologicalSort(int[][] graphMatrix) throws Exception {
		if(graphMatrix==null || graphMatrix.length<=0) {
			return;
		}
		
		StringBuilder output = new StringBuilder();
		int[] visited = new int[graphMatrix.length];
		int visitedCount = 0;
		int nextVertex = -1;
		
		while(visitedCount < graphMatrix.length) {
			// Find next vertex
			for(int colVertex=0; colVertex<graphMatrix.length; colVertex++) {
				int rowVertex = 0;
				for(; rowVertex<graphMatrix.length; rowVertex++) {
					if(graphMatrix[rowVertex][colVertex] > 0) {
						break;
					}
				}
				if(rowVertex==graphMatrix.length && visited[colVertex]==0) {
					nextVertex = colVertex;
					break;
				}
			}
			
			if(nextVertex == -1) {
				System.out.println("Graph has loop...");
				return;
			} else if(visited[nextVertex] == 0) {
				output.append("V" + nextVertex + " --> ");
				visited[nextVertex] = 1;
				visitedCount++;
				
				for(int i=0; i<graphMatrix.length; i++) {
					graphMatrix[nextVertex][i] = 0;
				}
				nextVertex = -1;
			}
		}
		output.append("END");
		System.out.println(output.toString());
	}
	
	public static void main(String[] args) throws Exception {
		int[][] graphMatrix = AdjacentMatrixUtils.genRandomDirectedGraph(3);
		AdjacentMatrixUtils.printGraph(graphMatrix);
		topologicalSort(graphMatrix);
	}
}
