package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

/*
 * Check if the given binary tree is BST or not.
 */
public class VerifyBST {

	public static <E extends Comparable<E>> boolean verify(BinaryTreeNode<E> root) {
		if(root == null) {
			return true;
		}
		
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		BinaryTreeNode<E> pre = null;
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.peek();
			while(node!=null) {
				stack.push(node.left);
				node = node.left;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.pop();
				if(pre!=null && node.value.compareTo(pre.value)<=0) {
					return false;
				}
				stack.push(node.right);
				pre = node;
			}
		}
		
		return true;
	}
	
	public static <E extends Comparable<E>> boolean verifyRecursion(BinaryTreeNode<E> node, E min, E max) {
		if(node==null) {
			return true;
		}

		// Attention the case that min or max is the min or max value of E (e.g. min or max value of integer)
		if((max == null || node.value.compareTo(max)<0) && (min == null || node.value.compareTo(min)>0)) {
			boolean left = verifyRecursion(node.left, min, node.value);
			boolean right = verifyRecursion(node.right, node.value, max);
			return left&&right;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(9);
		root.left = new BinaryTreeNode<Integer>(6);
		root.right = new BinaryTreeNode<Integer>(13);
		root.left.left = new BinaryTreeNode<Integer>(3);
		root.left.right = new BinaryTreeNode<Integer>(8);
		
		boolean result = verifyRecursion(root, null, null);
		if(result) {
			System.out.println("Is Binary Search Tree");
		} else {
			System.out.println("Is not Binary Search Tree");
		}
		
		result = verify(root);
		if(result) {
			System.out.println("Is Binary Search Tree");
		} else {
			System.out.println("Is not Binary Search Tree");
		}
	}

}
