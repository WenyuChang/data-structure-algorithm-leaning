package wenyu.learning.Tree.BinaryTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import wenyu.learning.Arrays.RangeMinimumQueryProblem;

/*
 * Problem1: Lowest common ancestor problem in Binary Search Tree
 * Logic: We can solve this problem using BST properties. We can recursively traverse the BST from root. 
 *        The main idea of the solution is, while traversing from top to bottom, the first node n we encounter with value between n1 and n2, 
 *        i.e., n1 < n < n2 or same as one of the n1 or n2, is LCA of n1 and n2 (assuming that n1 < n2). So just recursively traverse the BST 
 *        in, if node's value is greater than both n1 and n2 then our LCA lies in left side of the node, if it's is smaller than both n1 and 
 *        n2, then LCA lies on right side. Otherwise root is LCA (assuming that both n1 and n2 are present in BST)
 * 
 * Problem2: Lowest common Ancestor Problem in Binary Tree
 * Solution 1: Use recursion
 * Solution 2: Use Range Minimum Query (RMQ) [Please refer to RMQ Logic]
 */

class LCA_BST {
	public static <E extends Comparable<E>> BinaryTreeNode<E> LCA(BinaryTreeNode<E> root, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2) {
		if(root==null || child1==null || child2==null) {
			return null;
		}
		
		if(child1 == child2) {
			return child1;
		}
		
		BinaryTreeNode<E> biggerChild = (child1.value.compareTo(child2.value)>=0)? child1:child2;
		BinaryTreeNode<E> smallerChild = (child1.value.compareTo(child2.value)<0)? child1:child2;
		
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			E value = node.value;
			if(value.compareTo(biggerChild.value)<=0 && value.compareTo(smallerChild.value)>=0) {
				return node;
			} else if(value.compareTo(biggerChild.value)>0 && node.left!=null) {
				stack.push(node.left);
			} else if(value.compareTo(smallerChild.value)<0 && node.right!=null) {
				stack.push(node.right);
			} else {
				return null;
			}
		}
		return null;
	}
}

class LCA_Solution1 {
	// For binary tree, find two nodes' lowest common ancestor
	public static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> root, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2) {
		if(child1 == child2) {
			return child1;
		}
		boolean[] findChildren = new boolean[2];
		findChildren[0] = false;
		findChildren[1] = false;
		BinaryTreeNode<E> possibleCommonAncestor = LCA(root, child1, child2, findChildren);
		if(findChildren[0] && findChildren[1]) {
			return possibleCommonAncestor;
		} else {
			return null;
		}
	}
	private static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> node, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2, boolean[] findChildren) {
		if (node==null) {
			return null;
		}

		BinaryTreeNode<E> leftRoot = LCA(node.left, child1, child2, findChildren);
		BinaryTreeNode<E> rightRoot = LCA(node.right, child1, child2, findChildren);

		if(node==child1) {
			findChildren[0] = true;
			return child1;
		} else if(node==child2) {
			findChildren[1] = true;
			return child2;
		} else if(leftRoot!=null && rightRoot!=null) {
			// if child1 and child2 are on both sides
			return node; 
		} else {
			// either one of child1 or child2 is on one side 
			// OR child1 or child2 is not in L&R subtrees
			return (leftRoot != null) ? leftRoot : rightRoot;
		}
	}

	// For binary tree, find several nodes' lowest common ancestor
	public static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> root, BinaryTreeNode<E>[] nodes) {
		// remove duplicate nodes
		Set<BinaryTreeNode<E>> set = new HashSet<BinaryTreeNode<E>>();
		for(BinaryTreeNode<E> node : nodes) {
			set.add(node);
		}
		@SuppressWarnings("unchecked")
		BinaryTreeNode<E>[] tmpNodes = (BinaryTreeNode<E>[]) Array.newInstance(BinaryTreeNode.class, set.size());
		nodes = set.toArray(tmpNodes);
		int[] findChildCount = new int[1];
		findChildCount[0] = 0;
		BinaryTreeNode<E> possibleCommonAncestor = LCA(root, nodes, findChildCount);
		if(findChildCount[0] == nodes.length) {
			return possibleCommonAncestor;
		} else {
			return null;
		}
	}
	private static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> node, BinaryTreeNode<E>[] nodes, int[] findChildCount) {
		if (node == null) {
			return null;
		}

		BinaryTreeNode<E> leftRoot = LCA(node.left, nodes, findChildCount);
		BinaryTreeNode<E> rightRoot = LCA(node.right, nodes, findChildCount);

		for(int i=0;i<nodes.length;i++) {
			if (node == nodes[i]) {
				findChildCount[0]++;
				return node;
			}
		}

		if (leftRoot != null && rightRoot != null) {
			// if child1 and child2 are on both sides
			return node; 
		} else { 		
			// either one of child1 or child2 is on one side 
			// OR child1 or child2 is not in L&R subtrees
			return (leftRoot != null) ? leftRoot : rightRoot;
		}
	}
}

class LCA_Solution2 {
	/*
	 * Using RMQ
	 * Logic: Euler Tour of Tree is just pre-order traversal and mark every node
	 * 		       5(1)
	 *           /   \
	 *          2(2)  6(3)     ==> Euler Tour: [5, 2, 1, 2, 3, 2, 5, 6, 8, 6, 5]
	 *        /   \    \
	 *        1(4) 3(5) 8(6)
	 *       
     * 		  * E[1, 2*N-1] - the nodes visited in an Euler Tour of T; E[i] is the label of i-th visited node in the tour
     * 		  * L[1, 2*N-1] - the levels of the nodes visited in the Euler Tour; L[i] is the level of node E[i]
     * 		  * H[1, N] - H[i] is the index of the first occurrence of node i in E (any occurrence would be good, so it's not bad if we consider the first one)
	 *		  For example: E[] = [5,2,1,2,3,2,5,6,8,6,5]
	 *					   L[] = [0,1,2,1,2,1,0,1,2,1,0]
	 *					   H[] = [5=1,2=2,6=8,1=3,3=5,8=9]	  
	 *   
	 *	
	 *		  Nodes between the first occurrence of u and the first occurrence of v are E[H[u]...H[v]]. 
	 *        Now, we must find the node situated on the smallest level in L[]. 
	 *        For this, we can use RMQ. So, LCAT(u, v) = E[RMQL(H[u], H[v])] 
	 */
	
	public static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> root, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2) {
		ArrayList<BinaryTreeNode<E>> E = new ArrayList<BinaryTreeNode<E>>();
		ArrayList<Integer> L = new ArrayList<Integer>();
		HashMap<BinaryTreeNode<E>, Integer> H = new HashMap<BinaryTreeNode<E>, Integer>();
		
		// Step 1. prepare E/L/H, using pre-order traversal
		preOrder(root, E, L, H, 0);
		
		// Step 2. Find LCA.
		//		   Logic: 1. Find first occurence index of child1&child2 from H
		//				  2. According to the indexs above, find the RMQ from L
		//			      3. According to the RMQ index above, find Node from E
		if(!H.containsKey(child1) || !H.containsKey(child2)) {
			return null;
		}
		
		// Step 2.1
		int idx1 = H.get(child1)<H.get(child2)? H.get(child1):H.get(child2);
		int idx2 = H.get(child1)<H.get(child2)? H.get(child2):H.get(child1);
		int[] tmpL = new int[L.size()];
		for(int i=0; i<L.size(); i++) {
			tmpL[i] = L.get(i);
		}
		
		// Step 2.2
		int minIdx = RangeMinimumQueryProblem.usingST(tmpL, idx1, idx2, false);
		if(minIdx<0 || minIdx>L.size()) {
			return null;
		}
		
		// Step 2.3
		BinaryTreeNode<E> LCA = E.get(minIdx);
		return LCA;
	}
	
	private static <E> void preOrder(BinaryTreeNode<E> node, 
									 ArrayList<BinaryTreeNode<E>> E, 
									 ArrayList<Integer> L, 
									 HashMap<BinaryTreeNode<E>, Integer> H,
									 int level) {
		E.add(node);
		L.add(level);
		if(!H.containsKey(node)) {
			H.put(node, E.size());
		}
		
		if(node.left!=null) {
			preOrder(node.left, E, L, H, level+1);
		}
		
		E.add(node);
		L.add(level);
		
		if(node.right!=null) {
			preOrder(node.right, E, L, H, level+1);
		}
		
		E.add(node);
		L.add(level);
	}
}

public class LowestCommonAncestor {
	public static void test_lca_bst() {
		Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(arr, 0, arr.length-1);
		BinaryTreeNode<Integer> child1 = BSTUtils.findNode(root, 1);
		BinaryTreeNode<Integer> child2 = BSTUtils.findNode(root, 3);
		TraversalBFS.traversalBFSRecWithLine(root, null);
		
		BinaryTreeNode<Integer> commonAncestor = LCA_BST.LCA(root, child1, child2);
		if(commonAncestor!=null) {
			System.out.println("Lowest Common Ancestor is " + commonAncestor.value);
		} else {
			System.out.println("These two nodes has no lowest common ancestor.");
		}
	}
	
	public static void test_lca_bt_solution1() {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
		root.left = new BinaryTreeNode<Integer>(2);
		root.left.right = child1;
		root.right = new BinaryTreeNode<Integer>(5);
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
//		root.left = new BinaryTreeNode<Integer>(2);
//		root.right = child1;
//		root.right.left = child2;
//		root.right.right = new BinaryTreeNode<Integer>(6);
//		root.right.right.left = child3;
		
		//find two nodes' lowest common ancestor
		BinaryTreeNode<Integer> commonAncestor = LCA_Solution1.LCA(root, child2, child3);
		if(commonAncestor!=null) {
			System.out.println("Lowest Common Ancestor is " + commonAncestor.value);
		} else {
			System.out.println("These two nodes has no lowest common ancestor.");
		}
	}
	
	public static void test_lca_of_multiple_nodes() {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
		root.left = new BinaryTreeNode<Integer>(2);
		root.left.right = child1;
		root.right = new BinaryTreeNode<Integer>(5);
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
//		root.left = new BinaryTreeNode<Integer>(2);
//		root.right = child1;
//		root.right.left = child2;
//		root.right.right = new BinaryTreeNode<Integer>(6);
//		root.right.right.left = child3;
		
		@SuppressWarnings("unchecked")
		BinaryTreeNode<Integer>[] nodes = (BinaryTreeNode<Integer>[]) Array.newInstance(BinaryTreeNode.class, 3);
		nodes[0] = child2;
		nodes[1] = child2;
		nodes[2] = child3;
		//find several nodes' lowest common ancestor
		BinaryTreeNode<Integer> commonAncestor = LCA_Solution1.LCA(root, nodes);
		if(commonAncestor!=null) {
			System.out.println("Lowest Common Ancestor is " + commonAncestor.value);
		} else {
			System.out.println("These nodes have no lowest common ancestor.");
		}
	}
	
	public static void test_lca_bt_solution2() {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
		root.left = new BinaryTreeNode<Integer>(2);
		root.left.right = child1;
		root.right = new BinaryTreeNode<Integer>(5);
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
//		root.left = new BinaryTreeNode<Integer>(2);
//		root.right = child1;
//		root.right.left = child2;
//		root.right.right = new BinaryTreeNode<Integer>(6);
//		root.right.right.left = child3;
		
		//find two nodes' lowest common ancestor
		BinaryTreeNode<Integer> commonAncestor = LCA_Solution2.LCA(root, child2, child3);
		if(commonAncestor!=null) {
			System.out.println("Lowest Common Ancestor is " + commonAncestor.value);
		} else {
			System.out.println("These two nodes has no lowest common ancestor.");
		}
	}
	
	public static void main(String[] args) {

		test_lca_bst();
		
//		test_lca_bt_solution1();
		
//		test_lca_of_multiple_nodes();
		
//		test_lca_bt_solution2();
		
	}
}
