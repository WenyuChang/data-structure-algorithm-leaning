package wenyu.learning.Arrays;

/*
 * A given array represents a tree in such a way that the array value gives the parent node of 
 * that particular index.The value of the root node index would always be -1.
 * Find the height of the tree.
 * 				   0 1 2 3 4  5 6 	
 * Example: Array: 1 5 5 2 2 -1 3
	         5 
	        /  \
	       1    2
	      /    / \
	     0    3   4
	         /
	        6
	        
 * Solution 1:
 * Use recursion to find every nodes' children's height
 * 
 * Solution 2: O(n)
 * first calculate depth of every node and store in an array depth[]. Once we have depths of all nodes, we return maximum of all depths.
 * 1) Find depth of all nodes and fill in an auxiliary array depth[].
 * 2) Return maximum value in depth[].
 * 
 * Following are steps to find depth of a node at index i.
 * 1) If it is root, depth[i] is 1.
 * 2) If depth of parent[i] is evaluated, depth[i] is depth[parent[i]] + 1.
 * 3) If depth of parent[i] is not evaluated, recur for parent and assign depth[i] as depth[parent[i]] + 1 
 */
public class HeightFromArrayFormattedTree {

	public static int solution1(int[] arr, int value) {
		int childrenHeight = Integer.MIN_VALUE;
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==value) {
				int childH = solution1(arr, i);
				if(childH > childrenHeight) {
					childrenHeight = childH;
				}
			}
		}
		
		if(childrenHeight == Integer.MIN_VALUE) {
			return 0;
		}
		return childrenHeight+1;
	}
	
	public static int solution2(int[] arr) {
		int[] depth = new int[arr.length];
		// fill depth of all nodes
	    for (int i = 0; i<arr.length; i++) {
	        fillDepth(arr, depth, i);
	    }
		
		int height = depth[0];
		for(int i=1; i<depth.length; i++) {
			if(depth[i] > height) {
				height = depth[i];
			}
		}
		return height;
	}
	private static void fillDepth(int[] arr, int[] depth, int n) {
		if(depth[n] != 0) {
			return;
		}
		
		if(arr[n] == -1) {
			depth[n] = 1;
			return;
		}
		
		// If depth of parent is not evaluated before, then evaluate
	    // depth of parent first
	    if (depth[arr[n]] == 0) {
	        fillDepth(arr, depth, arr[n]);
	    }

	    // Depth of this node is depth of parent plus 1
	    depth[n] = depth[arr[n]] + 1;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,5,5,2,2,-1,3,6,7,8,0,4,9};
		int height = solution1(arr, -1);
		System.out.println("Result is " + height);
		

		height = solution2(arr);
		System.out.println("Result is " + height);
	}

}
