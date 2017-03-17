package wenyu.learning.Tree.BinaryTree;

import java.util.Arrays;
import java.util.Random;
/*
 * Problem 1: Verify if two trees are mirrored
 * Problem 2: Generate mirrored tree from given tree
 * Problem 3: Given a binary tree, find out if the tree can be folded or not. (folded means mirror but can has different values)
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
 * 				
 */

public class MirroredTreeProblems {
	
	public static <E> boolean verifyTwoTreesAreMirrored(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2) {
		if(node1==null && node2==null) {
			return true;
		} else if((node1==null&&node2!=null) || (node1!=null&&node2==null) || (node1.value!=node2.value)){
			return false;
		}
		
		boolean result = verifyTwoTreesAreMirrored(node1.left, node2.right);
		if(result) {
			result = verifyTwoTreesAreMirrored(node1.right, node2.left);
		}
		
		return result;
	}
	
	public static <E> void generateMirroredTree(BinaryTreeNode<E> node, BinaryTreeNode<E> mirroredNode) {
		if(node == null) {
			return;
		}
		mirroredNode.value = node.value;
		
		if(node.left!=null) {
			mirroredNode.right = new BinaryTreeNode<E>();
			generateMirroredTree(node.left, mirroredNode.right);
		}
		
		if(node.right!=null) {
			mirroredNode.left = new BinaryTreeNode<E>();
			generateMirroredTree(node.right, mirroredNode.left);
		}
	}
	
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
	
	public static void main(String[] args) {
		int treeNodeCount = 20;
		Integer[] values = new Integer[treeNodeCount];
		Random random = new Random();
		for(int i=0;i<treeNodeCount;i++) {
			values[i] = random.nextInt(100);
		}
		Arrays.sort(values);
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(values, 0, values.length-1);
		TraversalBFS.traversalWithLine(root, null);
		
		//=======================================================================
		System.out.println("======================================================");
		
		BinaryTreeNode<Integer> mirroredTree = new BinaryTreeNode<Integer>();
		generateMirroredTree(root, mirroredTree);
		TraversalBFS.traversalWithLine(mirroredTree, null);

		//=======================================================================
		System.out.println("======================================================");
		if(verifyTwoTreesAreMirrored(root, mirroredTree)) {
			System.out.println("Two trees are mirrored.");
		} else {
			System.out.println("Two trees are not mirrored.");
		}
		
		//=======================================================================
		System.out.println("======================================================");
		if(checkFoldable(root)) {
			System.out.println("Tree can be folded.");
		} else {
			System.out.println("Tree cannot be folded.");
		}
	}

}
