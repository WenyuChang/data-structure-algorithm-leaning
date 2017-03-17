package wenyu.learning.Tree.BinaryTree;

/**
 * Created by Wenyu on 12/14/16.
 *
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections. The path must contain at
 * least one node and does not need to go through the root.
 *
 * For example:
 * Given the below binary tree,
 *      1
 *     / \
 *    2   3
 * Return 6.
 *
 * Logic:
 *  1. For every path, it could be below case:
 *      a. a single node
 *      b. current node with it's left subtree's max
 *      c. current node with it's right subtree's max
 *      d. current node with both it's left and right subtree's max
 *  2. subtree's max could be
 *      a. left subtree's max plus current node's value
 *      b. right subtree's max plus current node's value
 *  3. using recursive to go through each node.
 */
public class MaxPathSumOfTwoNodes {
    private int helper(BinaryTreeNode<Integer> node, int[] max) {
        if (node.left == null && node.right == null) {
            // Leaf node
            max[0] = Math.max(max[0], node.value);
            return node.value;
        }

        int leftMax = 0;
        if (node.left != null) {
            // Get left subtree's max
            leftMax = helper(node.left, max);
        }
        if (leftMax < 0) {
            // If left subtree's max smaller than 0
            // It is no use to add, because it can only make smaller
            leftMax = 0;
        }

        int rightMax = 0;
        if (node.right != null) {
            // Get right subtree's max
            rightMax = helper(node.right, max);
        }
        if (rightMax < 0) {
            // If right subtree's max smaller than 0
            // It is no use to add, because it can only make smaller
            rightMax = 0;
        }

        // Check if current node form a max path.
        int currMax = node.value + leftMax + rightMax;
        max[0] = Math.max(max[0], currMax);

        // Return current node's max subtree value to it's parent
        return node.value + Math.max(leftMax, rightMax);
    }

    public int maxPathSum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int[] max = new int[] {Integer.MIN_VALUE};
        helper(root, max);
        return max[0];
    }
}
