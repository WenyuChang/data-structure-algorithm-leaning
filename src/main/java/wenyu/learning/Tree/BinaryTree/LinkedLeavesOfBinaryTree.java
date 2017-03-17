package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

/*
 * Given a Binary Tree, link all leaves of it in a Doubly Linked List (DLL). 
 * Note that the DLL need to be created in-place. Assume that the node structure of DLL and Binary Tree is same, 
 * only the meaning of left and right pointers are different. In DLL, left means previous pointer and right means 
 * next pointer.
 * 
 * Let the following be input binary tree
	        1
	     /     \
	    2       3
	   / \       \
	  4   5       6
	 / \         / \
	7   8       9   10
	
 * Output: 7<->8<->5<->9<->10
 */
public class LinkedLeavesOfBinaryTree {

	public static <E> void link(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		//BinaryTreeNode<E> tmpLeavesHead = new BinaryTreeNode<E>(null);
		BinaryTreeNode<E> pre = null;
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			if(node.left==null && node.right==null) {
				// Current node is leaf
				if(pre != null) {
					pre.right = node;
					node.left = pre;
				}
				pre = node;
				continue;
			}
			
			if(node.right!=null) {
				stack.push(node.right);
			}
			if(node.left!=null) {
				stack.push(node.left);
			}
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(new Integer[]{1,2,3,4,5,6,7,8,9,10}, 0, 9);
		TraversalBFS.traversalWithLine(root, null);
		link(root);
		
		// Do not print tree as all the leaves is linked. Printing will cause infinite loop.
		if(true) {/* For debug, make program stop here to see if leaves is linked. */ int a=0;}
	}

}
