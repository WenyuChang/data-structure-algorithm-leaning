package wenyu.learning.Tree.BinaryTree;

/**
 * Created by Wenyu on 12/12/16.
 */
public class VerifyIdenticalTree<E extends Comparable<E>> {
    public boolean isSameTree(BinaryTreeNode<E> p, BinaryTreeNode<E> q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        }

        if (p.value.compareTo(q.value) != 0) {
            return false;
        } else {
            boolean left = isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        }
    }
}
