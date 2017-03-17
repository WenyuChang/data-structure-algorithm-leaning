package wenyu.learning.Graphs.AdjacentMatrix;

public class GraphCloneUtils {
	public static int[][] clone(int[][] graphMatrix) {
		if(graphMatrix==null || graphMatrix.length==0 || graphMatrix[0].length==0) {
			return null;
		}
		
		int[][] cloneMatrix = new int[graphMatrix.length][graphMatrix[0].length];
		for(int i=0; i<graphMatrix.length; i++) {
			for(int j=0; j<graphMatrix[i].length; j++) {
				cloneMatrix[i][j] = graphMatrix[i][j];
			}
		}
		
		return cloneMatrix;
	}
	
	public static int[][] reversedClone(int[][] graphMatrix) {
		if(graphMatrix==null || graphMatrix.length==0 || graphMatrix[0].length==0) {
			return null;
		}
		
		int[][] reversedGraph = new int[graphMatrix.length][graphMatrix[0].length];
		for(int i=0; i<graphMatrix.length; i++) {
			for(int j=0; j<graphMatrix[0].length; j++) {
				reversedGraph[j][i] = graphMatrix[i][j];
			}
		}
		
		return reversedGraph;
	}
}
