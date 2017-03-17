package wenyu.learning.Tree.BinaryTree;

/*
 * Given a Binary Tree where each node has positive and negative values. Convert this to a tree where 
 * each node contains the sum of the left and right sub trees in the original tree. The values of leaf
 * nodes are changed to 0.
 * 
 * For example, the following tree
                  10
               /      \
	         -2        6
           /   \      /  \ 
	      8    -4    7    5
 * should be changed to
              20(4-2+12+6)
               /      \
	       4(8-4)      12(7+5)
            /   \      /  \ 
	       0     0    0    0
 */
public class SumTreeConverter {

	public static void transfer(BinaryTreeNode<Integer> node) {
		if(node==null) {
			return;
		}
		
		int originLeftValue = node.left==null ? 0 : node.left.value;
		int originRightValue = node.right==null ? 0 : node.right.value;
		
		transfer(node.left);
		transfer(node.right);
		
		int currLeftValue = node.left==null ? 0 : node.left.value;
		int currRightValue = node.right==null ? 0 : node.right.value;
		
		node.value = originLeftValue + originRightValue + currLeftValue + currRightValue;
		return;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(new Integer[]{2, 3, 5, 8, 1, 2}, 0, 5);
		TraversalBFS.traversalWithLine(root, null);
		transfer(root);
		System.out.println("======================================");
		TraversalBFS.traversalWithLine(root, null);
	}

}
