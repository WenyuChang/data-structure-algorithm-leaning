package wenyu.learning.Tree.BinaryTree;

public class DeserializeBT {
	private static final Object NULL = null;
	
	public static <E> BinaryTreeNode<E> deserialBTPreOrder(E[] elements, int[] idx) {
		/*
		 * Sample input "027  8  35  6  ";
		 */
		if(idx[0]>=elements.length || elements[idx[0]]==NULL) {
			return null;
		}
		
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(elements[idx[0]]);
		idx[0]++;
		node.left = deserialBTPreOrder(elements, idx);
		idx[0]++;
		node.right = deserialBTPreOrder(elements, idx);
		return node;
	}
	
	public static <E> BinaryTreeNode<E> deserialBTPostOrder(E[] elements, int[] idx) {
		/*
		 * Sample input "  7  82  5  630"
		 */
		if(idx[0]<0 || elements[idx[0]]==NULL) {
			return null;
		}
		
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(elements[idx[0]]);
		idx[0]--;
		node.right = deserialBTPostOrder(elements, idx);
		idx[0]--;
		node.left = deserialBTPostOrder(elements, idx);
		return node;
	}

	public static <E> BinaryTreeNode<E> deserialPreAndInOrder(E[] preorder, int pstart, int pend, E[] inorder, int istart, int iend) throws Exception {
		if (pend - pstart != iend - istart) {
			throw new Exception("Invalid input");
		}

		BinaryTreeNode<E> node = new BinaryTreeNode<E>(preorder[pstart]);
		if (pstart == pend) {
			return node;
		}

		int mid = -1;
		for (int i=istart; i<=iend; i++) {
			if (inorder[i] == preorder[pstart]) {
				mid = i;
				break;
			}
		}

		if (mid == istart) {
			node.left = null;
		} else if (mid < 0) {
			throw new Exception("Invalid input");
		} else {
			node.left = deserialPreAndInOrder(preorder, pstart+1, pend, inorder, istart, mid-1);
		}

		if (mid == iend) {
			node.right = null;
		} else if (mid > iend) {
			throw new Exception("Invalid input");
		} else {
			node.right = deserialPreAndInOrder(preorder, pstart+mid-istart+1, pend, inorder, mid+1, iend);
		}

		return node;
	}

	public static <E> BinaryTreeNode<E> deserialPostAndInOrder(E[] inorder, int istart, int iend, E[] postorder, int pstart, int pend) throws Exception {
		if (pend - pstart != iend - istart) {
			throw new Exception("Invalid input");
		}

		BinaryTreeNode<E> node = new BinaryTreeNode<E>(postorder[pend]);
		if (pstart == pend) {
			return node;
		}

		int mid = -1;
		for (int i=istart; i<=iend; i++) {
			if (inorder[i] == postorder[pend]) {
				mid = i;
				break;
			}
		}


		if (mid == istart) {
			node.left = null;
		} else if (mid < 0) {
			throw new Exception("Invalid input");
		} else {
			node.left = deserialPostAndInOrder(inorder, istart, mid-1, postorder, pstart, pstart+mid-istart-1);
		}

		if (mid == iend) {
			node.right = null;
		} else if (mid > iend) {
			throw new Exception("Invalid input");
		} else {
			node.right = deserialPostAndInOrder(inorder, mid+1, iend, postorder, pstart+mid-istart, pend-1);
		}

		return node;
	}
}
