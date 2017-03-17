package wenyu.learning.Strings;

import wenyu.learning.Graphs.AdjacentMatrix.Eulerian;

/*
 * Given an array of strings, find if the given strings can be chained to form a circle. 
 * A string X can be put before another string Y in circle if the last character of X is 
 * same as first character of Y.
 * 
 * Examples:
 * Input: arr[] = {"geek", "king"}
 * Output: Yes, the given strings can be chained.
 * Note that the last character of first string is same
 * as first character of second string and vice versa is
 * also true.
 * 
 * Input: arr[] = {"for", "geek", "rig", "kaf"}
 * Output: Yes, the given strings can be chained.
 * The strings can be chained as "for", "rig", "geek" and "kaf"
 * 
 * Input: arr[] = {"aaa"};
 * Output: Yes
 * 
 * Input: arr[] = {"aaa", "bbb"};
 * Output: No
 * 
 * 
 * Logic:
 * 1) Create a directed graph g with number of vertices equal to the size of alphabet. 
 *    We have created a graph with 26 vertices in the below program.
 * 2) Do following for every string in the given array of strings.
 *    a) Add an edge from first character to last character of the given graph.   
 * 3) If the created graph has eulerian circuit, then return true, else return false.
 */
public class ChainStrings {
	/*
	 * 0: string array cannot form a cycle
	 * 1: string array can form a cycle
	 * 2: string array can only form a path
	 */
	public static int verify(String[] strs) {
		// Step 1 & 2
		int[][] graph = new int[26][26];
		for(int i=0; i<strs.length; i++) {
			// Connect first character to last character
			int first = strs[i].charAt(0)-'a';
			int last = strs[i].charAt(strs[i].length()-1)-'a';
			graph[first][last] = 1;
		}
		
		//Step 3
		String type = Eulerian.DirectedCheck(graph);
		if(type.equals(Eulerian.EULER_CYCLE)) {
			return 1;
		} else if(type.equals(Eulerian.EULER_PATH)) {
			return 2;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
