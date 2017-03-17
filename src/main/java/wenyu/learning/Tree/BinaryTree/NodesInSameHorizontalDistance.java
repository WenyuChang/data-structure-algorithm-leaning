package wenyu.learning.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Map.Entry;



import wenyu.learning.Tree.BinaryTree.BSTUtils;
import wenyu.learning.Tree.BinaryTree.BinaryTreeNode;
import wenyu.learning.Tree.BinaryTree.TraversalBFS;

/*
 * Given a binary tree, count the number of occurrences where there are two nodes with the same horizontal distance. 
 * To make it clearer, if we assume each node in a cell of a matrix, then count the number of occurrences when there is 
 * a collision of two nodes in the same cell.
 * 			  1
 * 		2		 	3
 * 	4		5   6	  	7
 * 
 * ---------------------------
 * |    |    |  1  |    |    |
 * ---------------------------
 * |    |  2 |     |  3 |    |
 * ---------------------------
 * | 4  |    | 5 6 |    |  7 |
 * 
 * Here the count is 1 because 5 and 6 occupy the same cell in the matrix.
 * 
 * Logic: (Almost same as print tree vertically)
 * 	Each node is on a vertical line and can be assigned a number. 
 *  The line corresponding to the root being zero. If a node has 
 *  the vertical line number v, then its left child has number 
 *  v-1, and right child has v+1. So we can do a breadth first 
 *  search, collecting all nodes with the same vertical line in 
 *  a list.
 */
public class NodesInSameHorizontalDistance {
	public static int getOccurence(BinaryTreeNode<Integer> root, boolean print) {
		if(root == null) {
			return 0;
		}
		
		int occurence = 0;
		Map<Integer, ArrayList<BinaryTreeNode<Integer>>> indexMap = new TreeMap<Integer, ArrayList<BinaryTreeNode<Integer>>>();
		Map<BinaryTreeNode<Integer>, Integer> nodeMap = new HashMap<BinaryTreeNode<Integer>, Integer>();
		
		// Using BFS to initial index of each node
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
		queue.add(root);
		ArrayList<BinaryTreeNode<Integer>> list = new ArrayList<BinaryTreeNode<Integer>>();
		list.add(root);
//		indexMap.put(0, list);
		nodeMap.put(root, 0);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<Integer> node = queue.poll();
			int vLevel = nodeMap.get(node);
			if(node.left!=null) {
				queue.offer(node.left);
				nextLineNodeCount++;
				if(indexMap.containsKey(vLevel-1)) {
					indexMap.get(vLevel-1).add(node.left);
				} else {
					list = new ArrayList<BinaryTreeNode<Integer>>();
					list.add(node.left);
					indexMap.put(vLevel-1, list);
				}
				nodeMap.put(node.left, vLevel-1);
			}
			
			if(node.right!=null) {
				queue.offer(node.right);
				nextLineNodeCount++;
				if(indexMap.containsKey(vLevel+1)) {
					indexMap.get(vLevel+1).add(node.right);
				} else {
					list = new ArrayList<BinaryTreeNode<Integer>>();
					list.add(node.right);
					indexMap.put(vLevel+1, list);
				}
				nodeMap.put(node.right, vLevel+1);
			}
			
			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				Iterator<Entry<Integer, ArrayList<BinaryTreeNode<Integer>>>> it = indexMap.entrySet().iterator();
				while(it.hasNext()) {
					Entry<Integer, ArrayList<BinaryTreeNode<Integer>>> entry = it.next();
					ArrayList<BinaryTreeNode<Integer>> nodesOnSameVLevel = entry.getValue();
					if(nodesOnSameVLevel.size() > 1) {
						occurence++;
					}
				}
				indexMap.clear();
			}
		}
		
		if(print) System.out.println("Result is " + occurence);
		return occurence;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(10);
		TraversalBFS.traversalWithLine(root, null);
		
		getOccurence(root, true);
	}

}
