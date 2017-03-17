package wenyu.learning.Tree.BinaryTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Given a Binary Tree and a positive integer k, print all nodes that are distance k from a leaf node.
 * 
 * Here the meaning of distance is different from previous post. 
 * Here k distance from a leaf means k levels higher than a leaf node. 
 * For example if k is more than height of Binary Tree, then nothing should be printed. 
 * Expected time complexity is O(n) where n is the number nodes in the given Binary Tree.
 * 
 * Logic: 
 * 		1. Almost the same logic as height calculate
 * 		2. But the difference from height calculation is need to keep both the left and right heights rather than return only the bigger one.
 * 		   Because: both left and right heights contains the height from all sub-tree's leaves.
 */
public class DistanceFromAllLeaves {

	public static <E> Set<BinaryTreeNode<E>> find(BinaryTreeNode<E> root, int K, boolean print) {
		Set<BinaryTreeNode<E>> result = new HashSet<BinaryTreeNode<E>>();
		if(root==null) {
			return result;
		}
		find_core(root, K, result);

		if(print) {
			Iterator<BinaryTreeNode<E>> it = result.iterator();
			while(it.hasNext()) {
				BinaryTreeNode<E> node = it.next();
				System.out.print(node.value + " ");
			}
			System.out.println();
		}
		return result;
	}
	private static <E> int[] find_core(BinaryTreeNode<E> node, int K, Set<BinaryTreeNode<E>> result) {
		if(node==null) {
			int[] heights = new int[1];
			heights[0] = -1;
			return heights;
		}
		
		int[] leftHeights = find_core(node.left, K, result);
		int[] rightHeights= find_core(node.right, K, result);
		int[] currHeights = new int[leftHeights.length+rightHeights.length];
		int idx = 0;
		if(node.left!=null || (node.left==null&&node.right==null)) {
			for(int height : leftHeights) {
				currHeights[idx] = height+1;
				if(currHeights[idx] == K) {
					result.add(node);
				}
				idx++;
			}
		}
		
		if(node.right!=null || (node.right==null&&node.left==null)) {
			for(int height : rightHeights) {
				currHeights[idx] = height+1;
				if(currHeights[idx] == K) {
					result.add(node);
				}
				idx++;
			}
		}
		
		return Arrays.copyOfRange(currHeights, 0, idx);
	}
	
	
	public static void main(String[] args) {
//		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(new Integer[]{41,152,181,272,354,496,655,707,790,956}, 0, 9);
		TraversalBFS.traversalWithLine(root, null);
		
		System.out.println("Result is: ");
		find(root, 6, true);
	}
}
