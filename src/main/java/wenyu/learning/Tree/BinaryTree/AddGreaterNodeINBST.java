package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

/*
 * Given a Binary Search Tree (BST), modify it so that all greater values 
 * in the given BST are added to every node. For example, consider the following BST.
              50
           /      \
         30        70
        /   \      /  \
      20    40    60   80 
 * The above tree should be modified to following 
              260
           /      \
         330        150
        /   \       /  \
      350   300    210   80
      
 * Logic:
 * We can do it using a single traversal. The idea is to use following BST property. 
 * If we do reverse In-order traversal of BST, we get all nodes in decreasing order. 
 * We do reverse In-order traversal and keep track of the sum of all nodes visited so far, 
 * we add this sum to every node.
 */
public class AddGreaterNodeINBST {

	public static void transfer(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return;
		}

		int currSum = 0;
		Stack<BinaryTreeNode<Integer>> stack = new Stack<BinaryTreeNode<Integer>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<Integer> node = stack.peek();
			while(node!=null) {
				stack.push(node.right);
				node = node.right;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.pop();
				currSum += node.value;
				node.value = currSum;
				stack.push(node.left);
			}
		}
	}
	
	public static void transferRec(BinaryTreeNode<Integer> node, int[] preSum) {
		if(node == null) {
			return;
		}

		if(node.right!=null) {
			transferRec(node.right, preSum);
		}

		node.value += preSum[0];
		preSum[0] = node.value;
		
		if(node.left!=null) {
			transferRec(node.left, preSum);
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(new Integer[]{20,30,40,50,60,70,80}, 0, 6);
		TraversalBFS.traversalWithLine(root, null);
		//transfer(root);
		transferRec(root, new int[]{0});
		System.out.println("====================================");
		TraversalBFS.traversalWithLine(root, null);
	}

}
