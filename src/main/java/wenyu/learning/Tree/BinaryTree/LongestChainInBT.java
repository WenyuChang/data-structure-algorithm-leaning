package wenyu.learning.Tree.BinaryTree;

/*
 * In a binary tree, a chain can be defined as sum of length of the left node series, right node series, and 1. 
 * Find the length of longest chain in the tree.
 * 
 * Example: Refer to the image given below – Chain length of node 1 = 3 + 2 + 1 = {count of 3 corresponds to node 2 , node 4, node 8 ; 
 * count of 2 corresponds to node 3, node 7 ; 1 corresponds to node 1 itself}. Similarly, chain length of node 2 = 2 + 1 + 1. 
 * The max chain length here is of node 1 which is 6. So, the output should be 6.
 * 				1
 * 		   2	     3
 * 		4     5   6     7
 *   8     9
 */
public class LongestChainInBT {

	public static <E> int longestChain(BinaryTreeNode<E> root, boolean print) {
		int[] max = new int[]{Integer.MIN_VALUE};
		longestChain_core(root, false, max);
		if(print) System.out.println("Result is " + max[0]);
		return max[0];
	}
	private static <E> int longestChain_core(BinaryTreeNode<E> node, boolean ifLeft, int[] max) {
		if(node == null) {
			return 0;
		}
		
		int left = longestChain_core(node.left, true, max);
		int right = longestChain_core(node.right, false, max);
		
		int currChainCount = 1 + left + right;
		if(currChainCount > max[0]) {
			max[0] = currChainCount;
		}
		
		if(ifLeft) {
			return left+1;
		} else {
			return right+1;
		}
	}
 	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(5);
		BinaryTreeNode<Integer> node6 = new BinaryTreeNode<Integer>(6);
		BinaryTreeNode<Integer> node7 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(9);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node4.left = node8;
		node4.right = node9;

		TraversalBFS.traversalWithLine(node1, null);
		longestChain(node1, true);
	}

}
