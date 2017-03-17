package wenyu.learning.Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Given a Perfect Binary Tree, reverse the alternate level nodes of the binary tree.
 * Given tree: 
               a
            /     \
           b       c
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
       h  i j  k l  m  n  o 

 * Modified tree:
  	           a
            /     \
           c       b
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
      o  n m  l k  j  i  h 
      
 * Logic:
 * Solution 1: A simple solution is to do following steps.
 * 1) Access nodes level by level.
 * 2) If current level is odd, then store nodes of this level in an array.
 * 3) Reverse the array and store elements back in tree.
 * 
 * Solution 2: two inorder traversals. Following are steps to be followed.
 * 1) Traverse the given tree in inorder fashion and store all odd level nodes in an auxiliary array. 
 *    For the above example given tree, contents of array become {h, i, b, j, k, l, m, c, n, o}
 * 2) Reverse the array. The array now becomes {o, n, c, m, l, k, j, b, i, h}
 * 3) Traverse the tree again inorder fashion. While traversing the tree, one by one take elements from 
 *    array and store elements from array to every odd level traversed node.
 *    For the above example, we traverse ‘h’ first in above array and replace ‘h’ with ‘o’. Then we traverse ‘i’ 
 *    and replace it with n.
 *    
 * Solution 3:
 * 1) Using recursion.
 * 	  a. if current level number is even number, switch current two nodes.
 * 	  b. recurse(node1.left, node2.right)
 * 	  c. recurse(node1.right, node2.left)
 */
public class ReverseAlternateLevel {

	public static <E> void solution1(BinaryTreeNode<E> root) {
		if(root==null) {
			return;
		}
		
		int currLineNum = 1;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		Stack<E> stack = new Stack<E>();
		queue.offer(root);

		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			if(currLineNum%2 == 0) {
				node.value = stack.pop();
			}
			
			if(node.left != null) {
				queue.offer(node.left);
				nextLineNodeCount++;
				if(currLineNum%2 == 1) {
					stack.add(node.left.value);
				}
			}
			if(node.right != null) {
				queue.offer(node.right);
				nextLineNodeCount++;
				if(currLineNum%2 == 1) {
					stack.add(node.right.value);
				}
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				currLineNum++;
			}
		}
	}
	
	public static <E> void solution2(BinaryTreeNode<E> root) {
		// Not Implemented
		// Just read the logic of solution2
	}
	
	public static <E> void solution3(BinaryTreeNode<E> root) {
		solution3_core(root.left, root.right, 2);
	}
	private static <E> void solution3_core(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2, int level) {
		if(node1==null || node2==null) {
			return;
		}
		
		if(level%2 == 0) {
			E tmp = node1.value;
			node1.value = node2.value;
			node2.value = tmp;
		}
		
		solution3_core(node1.left, node2.right, level+1);
		solution3_core(node1.right, node2.left, level+1);
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(31);
		TraversalBFS.traversalWithLine(root, null);

		//solution1(root);
		//solution2(root);
		solution3(root);
		System.out.println("============================================");
		
		TraversalBFS.traversalWithLine(root, null);
	}

}
