package wenyu.learning.Tree.BinaryTree;

import java.util.HashMap;
import java.util.Stack;


/*
 * Given a Binary tree, full_path_sum is sum of all nodes from root to leaf in a path. 
 * Given a min_sum value, delete nodes if path has full_path_sum less than min_sum . 
 * Delete all such nodes . 
 * 
 * For example,
 * Given min_sum =8		
			1
		2		3
	  4   5   6   7
 * So we delete 4.
 */
public class RemoveNodePathLessThanSpecSum {
	public static BinaryTreeNode<Integer> deleteNodes(BinaryTreeNode<Integer> root, int minSum) {
		if(root == null) {
			return null;
		}
		
		int currSum = 0;
		HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> parentMap = new HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>>();
		Stack<BinaryTreeNode<Integer>> stack = new Stack<BinaryTreeNode<Integer>>();

		BinaryTreeNode<Integer> tmpRoot = new BinaryTreeNode<Integer>();
		tmpRoot.right = root;
		parentMap.put(root, tmpRoot);
		stack.push(root);
		BinaryTreeNode<Integer> lastVisited = null;
		while(!stack.isEmpty()) {
			BinaryTreeNode<Integer> node = stack.peek();
			while(node!=null) {
				stack.push(node.left);
				parentMap.put(node.left, node);
				currSum += node.value;
				node = node.left;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.peek();
				if(node.right==null || node.right==lastVisited) {
					node = stack.pop();

					if(node.left==null && node.right==null && currSum<minSum) {
						BinaryTreeNode<Integer> parent = parentMap.get(node);
						if(parent.left==node) {parent.left = null;}
						else if(parent.right==node) {parent.right = null;}
					}
					currSum -= node.value;
					
					if(lastVisited!=null && node.right == lastVisited) {
						lastVisited = node;
						stack.push(null);
						continue;
					}
					lastVisited = node;
				}
				if(node.right != null) {
					parentMap.put(node.right, node);
				}
				stack.push(node.right);
			}
		}
		
		return tmpRoot.right;
	}
	
	public static BinaryTreeNode<Integer> deleteNodesRec(BinaryTreeNode<Integer> root, int minSum) {
		if(root == null) {
			return null;
		}
		
		BinaryTreeNode<Integer> tmpRoot = new BinaryTreeNode<Integer>();
		tmpRoot.right = root;
		
		deleteNodesRec_core(root, tmpRoot, minSum, 0);
		return tmpRoot.right;
	}
	
	public static void deleteNodesRec_core(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> parent, int minSum, int currSum) {
		if(node == null) {
			return;
		}
		
		if(node.left!=null) {
			deleteNodesRec_core(node.left, node, minSum, currSum+node.value);
		}
		if(node.right!=null) {
			deleteNodesRec_core(node.right, node, minSum, currSum+node.value);
		}
		
		if(node.left==null && node.right==null && currSum+node.value<minSum) {
			if(parent.left==node) {parent.left = null;}
			else if(parent.right==node) {parent.right = null;}
		}
	}
	
	public static void main(String[] args) {
		Integer[] arr = new Integer[] {9,4,6,5,7,8,2,1};
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(arr, 0, arr.length-1);
		TraversalBFS.traversalWithLine(root, null);
		
//		System.out.println("=======================================");
//		root = deleteNodesRec(root, 18);
//		TraversalBFS.traversalWithLine(root, null);
		
		System.out.println("=======================================");
		root = deleteNodes(root, 18);
		TraversalBFS.traversalWithLine(root, null);
	}

}
