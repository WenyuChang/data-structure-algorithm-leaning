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
 * Given a binary tree, write code to get the vertical sum of all the columns in the tree, with minimum space complexity.
 * 
 * Logic: (Same as print tree vertically)
 * 	Each node is on a vertical line and can be assigned a number. 
 *  The line corresponding to the root being zero. If a node has 
 *  the vertical line number v, then its left child has number 
 *  v-1, and right child has v+1. So we can do a breadth first 
 *  search, collecting all nodes with the same vertical line in 
 *  a list. 
 *  
 */
public class GetAllVerticalSumFromBinaryTree {
	public static void getSum(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return;
		}
		
		Map<Integer, ArrayList<BinaryTreeNode<Integer>>> indexMap = new TreeMap<Integer, ArrayList<BinaryTreeNode<Integer>>>();
		Map<BinaryTreeNode<Integer>, Integer> nodeMap = new HashMap<BinaryTreeNode<Integer>, Integer>();
		
		// Using BFS to initial index of each node
		Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
		queue.add(root);
		ArrayList<BinaryTreeNode<Integer>> list = new ArrayList<BinaryTreeNode<Integer>>();
		list.add(root);
		indexMap.put(0, list);
		nodeMap.put(root, 0);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<Integer> node = queue.poll();
			int vLevel = nodeMap.get(node);
			if(node.left!=null) {
				queue.offer(node.left);
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
				if(indexMap.containsKey(vLevel+1)) {
					indexMap.get(vLevel+1).add(node.right);
				} else {
					list = new ArrayList<BinaryTreeNode<Integer>>();
					list.add(node.right);
					indexMap.put(vLevel+1, list);
				}
				nodeMap.put(node.right, vLevel+1);
			}
		}
		
		// print according to the vertical level index
		Iterator<Entry<Integer, ArrayList<BinaryTreeNode<Integer>>>> it = indexMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer, ArrayList<BinaryTreeNode<Integer>>> entry = it.next();
			ArrayList<BinaryTreeNode<Integer>> nodesOnSameVLevel = entry.getValue();
			int currColumnSum = 0;
			for(BinaryTreeNode<Integer> node : nodesOnSameVLevel) {
				currColumnSum += node.value;
			}
			System.out.print(currColumnSum + " ");
		}
	}
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(8);
		TraversalBFS.traversalWithLine(root, null);
		
		System.out.println("===========================");
		getSum(root);
	}

}
