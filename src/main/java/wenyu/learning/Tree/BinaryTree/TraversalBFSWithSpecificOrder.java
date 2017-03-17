package wenyu.learning.Tree.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a perfect binary tree.
 * print nodes in a specific manner. 
 * e.g-
             15
            /  \
          13    14
        /  \    /  \
       9   10  11   12
      / \  / \ / \  / \
     1  2  3 4 5 6  7 8
 * print: 15 13 14 9 12 10 11 1 8 2 7 3 6 4 5
 * 
 * Logic:
 * Solution 1:
 * We can do standard level order traversal here too but instead of printing nodes 
 * directly, we have to store nodes in current level in a temporary array or list 1st 
 * and then take nodes from alternate ends (left and right) and print nodes. Keep 
 * repeating this for all levels. This approach takes more memory than standard traversal.
 *
 * Solution 2:
 * The standard level order traversal idea will slightly change here. Instead of processing 
 * ONE node at a time, we will process TWO nodes at a time. And while pushing children into 
 * queue, the enqueue order will be: 1st node’s left child, 2nd node’s right child, 1st node’s 
 * right child and 2nd node’s left child.
 */
public class TraversalBFSWithSpecificOrder {

	public static <E> void solution2(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		Deque<BinaryTreeNode<E>> deque  = new LinkedList<BinaryTreeNode<E>>();
		deque.offer(root);
		while(!deque.isEmpty()) {
			BinaryTreeNode<E> nodeLeft = deque.poll();
			BinaryTreeNode<E> nodeRight = deque.poll();
			
			System.out.print(nodeLeft.value + " ");
			if(nodeRight == null) {
				if(nodeLeft.left != null) {
					deque.offer(nodeLeft.left);
				}
				if(nodeLeft.right != null) {
					deque.offer(nodeLeft.right);
				}
			} else {
				System.out.print(nodeRight.value + " ");
				if(nodeLeft.left != null) {
					deque.offer(nodeLeft.left);
				}
				if(nodeRight.right != null) {
					deque.offer(nodeRight.right);
				}
				if(nodeLeft.right != null) {
					deque.offer(nodeLeft.right);
				}
				if(nodeRight.left != null) {
					deque.offer(nodeRight.left);
				}
			}
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(15);
		TraversalBFS.traversalWithLine(root, null);
		
		System.out.println("======================================");
		solution2(root);
	}

}
