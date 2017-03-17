package wenyu.learning.Graphs.AdjacentList;

import java.util.Random;

public class AdjacentListUtils {
	public static void printGraph(AdjListNode[] graphList) {
		for(int i=0;i<graphList.length;i++) {
			System.out.print(graphList[i] + ": ");
			
			for(int j=0;j<graphList[i].adjacencies.size();j++) {
				System.out.print(graphList[i].adjacencies.get(j) + ", ");
			}
			System.out.println();
		}
	}
	
	public static AdjListNode[] genRandomDirectedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		AdjListNode[] graphList = new AdjListNode[vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			if(graphList[i] == null) {
				graphList[i] = new AdjListNode(String.valueOf(i));
			}
			for(int j=0;j<vertexCount;j++) {
				if(i==j) { continue; }
				if(random.nextBoolean()) {
					if(graphList[j] == null) {
						graphList[j] = new AdjListNode(String.valueOf(j));
					}
					graphList[i].adjacencies.add(graphList[j]);
					graphList[i].edgesWeight.add(1);
				}
			}
		}
		return graphList;
	}
	
	public static AdjListNode[] genRandomDirectedWeightedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		AdjListNode[] graphList = new AdjListNode[vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			if(graphList[i] == null) {
				graphList[i] = new AdjListNode(String.valueOf(i));
			}
			for(int j=0;j<vertexCount;j++) {
				if(i==j) { continue; }
				if(random.nextBoolean()) {
					if(graphList[j] == null) {
						graphList[j] = new AdjListNode(String.valueOf(j));
					}
					graphList[i].adjacencies.add(graphList[j]);
					graphList[i].edgesWeight.add(random.nextInt(100));
				}
			}
		}
		return graphList;
	}
	
	public static AdjListNode[] genRandomUndirectedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		AdjListNode[] graphList = new AdjListNode[vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			if(graphList[i] == null) {
				graphList[i] = new AdjListNode(String.valueOf(i));
			}
			for(int j=0;j<i;j++) {
				if(random.nextBoolean()) {
					if(graphList[j] == null) {
						graphList[j] = new AdjListNode(String.valueOf(j));
					}
					graphList[i].adjacencies.add(graphList[j]);
					graphList[i].edgesWeight.add(1);
					graphList[j].adjacencies.add(graphList[i]);
					graphList[j].edgesWeight.add(1);
				}
			}
		}
		return graphList;
	}
	
	public static AdjListNode[] genRandomUndirectedWeightedGraph(int vertexCount) {
		if(vertexCount <= 0) {
			return null;
		}
		
		AdjListNode[] graphList = new AdjListNode[vertexCount];
		Random random = new Random();
		for(int i=0;i<vertexCount;i++) {
			if(graphList[i] == null) {
				graphList[i] = new AdjListNode(String.valueOf(i));
			}
			for(int j=0;j<i;j++) {
				if(random.nextBoolean()) {
					if(graphList[j] == null) {
						graphList[j] = new AdjListNode(String.valueOf(j));
					}
					int weight = random.nextInt(100);
					graphList[i].adjacencies.add(graphList[j]);
					graphList[i].edgesWeight.add(weight);
					graphList[j].adjacencies.add(graphList[i]);
					graphList[j].edgesWeight.add(weight);
				}
			}
		}
		return graphList;
	}
}
