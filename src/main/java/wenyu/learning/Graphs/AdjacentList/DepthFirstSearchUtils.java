package wenyu.learning.Graphs.AdjacentList;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import wenyu.learning.Graphs.AdjacentList.AdjListNode;

public class DepthFirstSearchUtils {
	public static void DepthFirstSearch(AdjListNode[] graphList, boolean recursion, boolean print) {
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
					DFSWithRecursion(graphList[i], visited, print);
				} else {
					DFSWithoutRecursion(graphList[i], visited, print);
				}
				if(print) System.out.println("END");
			}
			
		}
	}
	protected static void DFSWithRecursion(AdjListNode currVertex, Map<AdjListNode, Boolean> visited, boolean print) {
		if(print) System.out.print(currVertex + " --> ");
		visited.put(currVertex, true);
		for(AdjListNode vertex : currVertex.adjacencies) {
			if(!visited.containsKey(vertex)) {
				visited.put(vertex, false);
			}
			
			if(!visited.get(vertex)) {
				DFSWithRecursion(vertex, visited, print);
			}
		}
	}
	protected static void DFSWithoutRecursion(AdjListNode currVertex, Map<AdjListNode, Boolean> visited, boolean print) {
		Stack<AdjListNode> stack = new Stack<AdjListNode>();
		stack.push(currVertex);
		
		while(!stack.isEmpty()) {
			AdjListNode vertex = stack.pop();
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				if(print) System.out.print(vertex + " --> ");
				visited.put(vertex, true);
			}
			
			for(int i=vertex.adjacencies.size()-1; i>=0 ;i--) {
				if(!visited.containsKey(vertex.adjacencies.get(i)) || !visited.get(vertex.adjacencies.get(i))) {
					visited.put(vertex.adjacencies.get(i), false);
					stack.push(vertex.adjacencies.get(i));
				}
			}
		}
	}
	
	public static Stack<AdjListNode> PostDepthFirstSearch(AdjListNode[] graphList) {
		return PostDepthFirstSearch(graphList, graphList[0]);
	}
	public static Stack<AdjListNode> PostDepthFirstSearch(AdjListNode[] graphList, AdjListNode starter) {
		Stack<AdjListNode> stack = new Stack<AdjListNode>();
		Map<AdjListNode, Boolean> visited = new HashMap<AdjListNode, Boolean>();
		if(!visited.containsKey(starter) || !visited.get(starter)) {
			postDFSRec(stack, starter, visited);
		}
		for(AdjListNode vertex : graphList) {
			if(!visited.containsKey(vertex) || !visited.get(vertex)) {
				postDFSRec(stack, vertex, visited);
			}
		}
		return stack;
	}
	private static void postDFSRec(Stack<AdjListNode> stack, AdjListNode currentVertex, Map<AdjListNode, Boolean> visited) {
		visited.put(currentVertex, true);
		for(int i=0; i<currentVertex.adjacencies.size(); i++) {
			AdjListNode tmpVertex = currentVertex.adjacencies.get(i);
			if(!visited.containsKey(tmpVertex) || !visited.get(tmpVertex)) {
				postDFSRec(stack, tmpVertex, visited);
			}
		}
		stack.push(currentVertex);
	}
}
