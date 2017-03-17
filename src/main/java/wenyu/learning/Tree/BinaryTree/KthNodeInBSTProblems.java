package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

/*
 * Problem 1: Given root of binary search tree and K as input, find K-th smallest element in BST.
 * Problem 2: Given root of binary search tree and K as input, find K-th biggest element in BST.
 */
public class KthNodeInBSTProblems {
	public static <E extends Comparable<E>> BinaryTreeNode<E> problem1(BinaryTreeNode<E> root, int k) {
		if(root==null || k<=0) {
			return null;
		}

		int currCount = 0;
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.peek();
			while(node!=null) {
				stack.push(node.left);
				node = node.left;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.pop();
				currCount++;
				if(currCount == k) {
					return node;
				}
				stack.push(node.right);
			}
		}
		
		return null;
	}
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> problem2(BinaryTreeNode<E> root, int k) {
		if(root==null || k<=0) {
			return null;
		}

		int currCount = 0;
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.peek();
			while(node!=null) {
				stack.push(node.right);
				node = node.right;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.pop();
				currCount++;
				if(currCount == k) {
					return node;
				}
				stack.push(node.left);
			}
		}
		
		return null;
	}

	
	public static void main(String[] args) {
		int k = 2;
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		TraversalBFS.traversalWithLine(root, null);

		System.out.println("=============================");
		BinaryTreeNode<Integer> node = problem2(root, k);
		if(node != null) {
			System.out.println("Result is " + node.value);
		}
	}

}
