package wenyu.learning.Tree.BinaryTree;

/**
 * Created by Wenyu on 12/12/16.
 *
 * Problem: Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 */
public class RecoverBST<E extends Comparable<E>> {
    BinaryTreeNode<E> first;
    BinaryTreeNode<E> second;
    BinaryTreeNode<E> pre;

    public void inorder(BinaryTreeNode<E> root){
        if(root==null)
            return;

        inorder(root.left);

        if(pre==null){
            pre=root;
        }else{
            if(root.value.compareTo(pre.value) < 0){
                if(first==null){
                    first=pre;
                }

                second=root;
            }
            pre=root;
        }

        inorder(root.right);
    }

    public void recoverTree(BinaryTreeNode<E> root) {
        if(root==null)
            return;

        inorder(root);
        if(second!=null && first !=null){
            E val = second.value;
            second.value = first.value;
            first.value = val;
        }

    }
}
