package wenyu.learning.Tree.kAryTree;

import java.util.Stack;

import wenyu.learning.Tree.BinaryTree.BinaryTreeNode;

public class TraversalDFS {

	public static <E> void dfs(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		Stack<KAryTreeNode<E>> stack = new Stack<KAryTreeNode<E>>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			KAryTreeNode<E> node = stack.pop();
			System.out.print(node.value + " ");
			
			for(int i=node.children.length-1; i>=0; i--) {
				if(node.children[i] != null) {
					stack.push(node.children[i]);
				}
			}
		}
		System.out.println();
	}
	
	public static <E> void dfsRec(KAryTreeNode<E> node) {
		if(node == null) {
			return;
		}
		
		System.out.print(node.value + " ");
		for(int i=0; i<node.children.length; i++) {
			if(node.children[i] != null) {
				dfsRec(node.children[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		KAryTreeNode<Integer> root = KAryTreeUtils.genRandomIntegerKAryTree(8, 20, false);
		TraversalBFS.BFSWithLine(root);
		
		System.out.println("=================================");
		dfsRec(root);
		System.out.println();
		
		System.out.println("=================================");
		dfs(root);
	}
}
 