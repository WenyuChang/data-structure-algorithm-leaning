package wenyu.learning.Tree.BinaryTree;

/*
 * Problem 1: Verify if a binary tree is symmetrical whose right part is identical to the left part.
 * Problem 2: Given a binary tree, find out if the tree can be folded or not. (folded means mirror but can has different values)
 * 			  For example:	Consider the below trees:
							(a) Can be folded
							       10
							     /    \
							    7      15
							     \    /
							      9  11
							      
							(c) Cannot be folded
							        10
							       /  \
							      7   15
							     /    /
							    5   11
 */

public class VerifySymmetrical {
	/* 
	 * Problem 1
	 */
	public static <E> boolean verify(BinaryTreeNode<E> root) {
		return verify(root, root);
	}
	
	public static <E> boolean verify(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2) {
		if(node1 == null && node2 == null) {
			return true;
		}
		if(node1 == null || node2 == null) {
			return false;
		}
		if(node1.value != node2.value) {
			return false;
		}
		
		return verify(node1.left, node2.right) && verify(node1.right, node2.left);
	}
	
	/*
	 * Problem 2
	 */
	public static <E> boolean checkFoldable(BinaryTreeNode<E> root) {
		return checkFoldable_core(root, root);
	}
	private static <E> boolean checkFoldable_core(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2) {
		if(node1==null && node2==null) {
			return true;
		} else if(node1!=null && node2!=null){
			return checkFoldable_core(node1.left, node2.right) && checkFoldable_core(node1.right, node2.left);
		} else {
			return false;
		}
	}
}
