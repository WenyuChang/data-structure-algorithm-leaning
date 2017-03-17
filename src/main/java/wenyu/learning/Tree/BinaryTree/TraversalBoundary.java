package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

/*
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root. 
 * For example, boundary traversal of the following tree is “20 8 4 10 14 25 22″
 * 
 * 		      20
 * 		8	        22
 * 	4	    12          25
 *      10      14    
 *      
 * Logic: 
 * 		1. Traversal left side;
 * 		2. Traversal all leaves;
 * 		3. Traversal right side;
 */
public class TraversalBoundary {
	
	public static <E> void traversal(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		traversalLeftSide(root);
		traversalLeaves(root);
		traversalRightSide(root);
	}
	
	private static <E> void traversalLeftSide(BinaryTreeNode<E> root) {
		BinaryTreeNode<E> node = root;
		while(node != null) {
			if(node.left!=null || node.right!=null) {
				System.out.print(node.value + " ");
			}
			node = node.left;
		}
	}
	
	private static <E> void traversalLeaves(BinaryTreeNode<E> root) {
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			if(node.left==null && node.right==null) {
				System.out.print(node.value + " ");
			}
			
			if(node.right!=null) {
				stack.push(node.right);
			}
			if(node.left!=null) {
				stack.push(node.left);
			}
		}
	}
	
	private static <E> void traversalRightSide(BinaryTreeNode<E> root) {
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		BinaryTreeNode<E> node = root.right;
		while(node != null) {
			if(node.left!=null || node.right!=null) {
				stack.push(node);
			}
			node = node.right;
		}
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop().value + " ");
		}
	}
	
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(5);
//		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(new Integer[] {695,4}, 0, 4);
		TraversalBFS.traversalWithLine(root, null);
		traversal(root);
	}

}
