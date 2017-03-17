package wenyu.learning.Tree.kAryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Connect adjacent cousins
 * But need to attention on the not complete tree: 9 to 10
 * 	         		1            -------------- Level 0
		          /    \
		        2        3       -------------- Level 1
		       / \      /  \
		      4   5    6    7    -------------- Level 2
		     / \           / \
		    8   9        10   11 -------------- Level 3
 * 
 * Solution1: using BFS, then connect adjacent cousins
 * Solution2: pre-order traversal O(n) time and O(n) recursion space
 * Solution3: without recursion to connect adjacent cousins
 */

public class ConnectCousin {

	public static <E> void solution1(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		KAryTreeNode<E> preNode = null;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<KAryTreeNode<E>> queue = new LinkedList<KAryTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			KAryTreeNode<E> node = queue.poll();
			if(preNode!=null) {
				preNode.nextBrother = node;
			}
			
			if(node.children != null) {
				for(int i=0;i<node.children.length;i++) {
					if(node.children[i] != null) {
						queue.offer(node.children[i]);
						nextLineNodeCount++;
					}
				}
			}
			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				preNode = null;
			} else {
				preNode = node;
			}
		}
	}
	
	public static <E> void solution2(KAryTreeNode<E> node) {
		if(node == null) {
			return;
		}
		
		KAryTreeNode<E> pre = null;
		for(int i=0;i<node.children.length;i++) {
			if(node.children[i] != null) {
				if(pre != null) {
					pre.nextBrother = node.children[i];
				}
				pre = node.children[i];
			}
		}
		
		if(pre!=null) {
			KAryTreeNode<E> tmpNode = node.nextBrother;
			while(tmpNode!=null) {
				boolean find = false;
				for(int i=0;i<tmpNode.children.length;i++) {
					if(tmpNode.children[i] != null) {
						pre.nextBrother = tmpNode.children[i];
						find = true;
						break;
					}
				}
				if(find) {
					break;
				} else {
					tmpNode = tmpNode.nextBrother;
				}
			}
		}
		
		for(int i=0;i<node.children.length;i++) {
			if(node.children[i] != null) {
				solution2(node.children[i]);
			}
		}
	}
	
	public static <E> void solution3(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		root.nextBrother = null;
		KAryTreeNode<E> node = root;
		while(node != null) {
			KAryTreeNode<E> internalNode = node;
			node = null;
			KAryTreeNode<E> pre = null;

			while(internalNode != null) {
				for(int i=0;i<internalNode.children.length;i++) {
					if(internalNode.children[i] != null) {
						if (node == null) {
							node = internalNode.children[i];
						}

						if(pre != null) {
							pre.nextBrother = internalNode.children[i];
						}
						pre = internalNode.children[i];
					}
				}

				internalNode = internalNode.nextBrother;
			}
		}
	}
}
