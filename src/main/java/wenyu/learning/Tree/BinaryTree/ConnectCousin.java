package wenyu.learning.Tree.BinaryTree;

/**
 * Created by Wenyu on 12/13/16.
 */
public class ConnectCousin {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public static void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        root.next = null;

        TreeLinkNode node = root;
        while (node != null) {
            TreeLinkNode internalNode = node;
            node = null;
            TreeLinkNode pre = null;
            while (internalNode != null) {
                if (internalNode.left != null) {
                    if (node == null) {
                        node = internalNode.left;
                    }
                    if (pre != null) {
                        pre.next = internalNode.left;
                    }
                    pre = internalNode.left;
                }

                if (internalNode.right != null) {
                    if (node == null) {
                        node = internalNode.right;
                    }
                    if (pre != null) {
                        pre.next = internalNode.right;
                    }
                    pre = internalNode.right;
                }

                internalNode = internalNode.next;
            }
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(0);
        connect(root);
    }
}
