package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

import wenyu.learning.Stack.BuildinStack;

/**
 * Problem1: Given a binary tree and an integer value,
 * please print all paths where the sum of 
 * node values equals the given integer. 
 * All nodes from the root node to a node 
 * compose a path. (P168)
 *
 * Problem2: Given a binary tree and a sum, determine if the tree has a root-to-leaf path such
 * that adding up all the values along the path equals the given sum.
 */

public class GetPathWithSum {
	public static class Problem1 {
		public static void getPathWithSum(BinaryTreeNode<Integer> root, int sum) {
			getPathWithSum(root, sum, 0, new Stack<BinaryTreeNode<Integer>>());
		}

		private static void getPathWithSum(BinaryTreeNode<Integer> node, int sum, int currSum, Stack<BinaryTreeNode<Integer>> stack) {
			if(node == null) {
				return;
			}

			int tmpSum = node.value+currSum;
			if(tmpSum == sum) {
				stack.push(node);
				BuildinStack.printStack(stack);
				stack.pop();
			} else if(tmpSum < sum) {
				currSum += node.value;
				stack.push(node);
				getPathWithSum(node.left, sum, currSum, stack);
				getPathWithSum(node.right, sum, currSum, stack);
				stack.pop();
				currSum -= node.value;
			}
		}
	}


	public static class Problem2 {
		private boolean helper(BinaryTreeNode<Integer> node, int currSum, int sum) {
			currSum += node.value;

			if (node.left == null && node.right == null && currSum == sum) {
				return true;
			}

			boolean left = false;
			if (node.left != null){
				left = helper(node.left, currSum, sum);
			}
			if (left) {
				return true;
			}

			boolean right = false;
			if (node.right != null){
				right = helper(node.right, currSum, sum);
			}
			if (right) {
				return true;
			}

			return false;
		}

		public boolean hasPathSum(BinaryTreeNode<Integer> root, int sum) {
			if (root == null) {
				return false;
			}

			return helper(root, 0, sum);
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(9);
		root1.left = new BinaryTreeNode<Integer>(5);
		root1.right = new BinaryTreeNode<Integer>(12);
		root1.left.left = new BinaryTreeNode<Integer>(4);
		root1.left.right = new BinaryTreeNode<Integer>(7);
		TraversalBFS.traversalWithLine(root1, null);
		System.out.println("=====================================");
		
		Problem1.getPathWithSum(root1, 30);
	}
}
