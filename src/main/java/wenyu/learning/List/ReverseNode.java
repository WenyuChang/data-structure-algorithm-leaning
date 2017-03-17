package wenyu.learning.List;

/*
 * Reverse the given link
 * Logic:
 * 	1. Every swap step need three nodes prepared.
 * 	   a) previous node
 *     b) current node
 *     c) next node
 *  2. Return new head
 */
public class ReverseNode {

	public static <T> MyListNode<T> reverseListRec(MyListNode<T> node, MyListNode<T> head) {
		if(node==null) {
			return null;
		}
		
		MyListNode<T> pre = reverseListRec(node.next, head);
		node.next = null;
		if(pre != null) {
			pre.next = node;
		} else {
			head.next = node;
		}
		return node;
	}
	
	public static <T> MyListNode<T> reverseList(MyListNode<T> head) {
		if(head==null) {
			return null;
		}
		
		MyListNode<T> preNode = head;
		MyListNode<T> currNode = head.next;
		head.next = null;
		
		while(currNode != null) {
			MyListNode<T> nextNode = currNode.next;
			currNode.next = preNode;
			preNode = currNode;
			currNode = nextNode;
		}
		
		return preNode;
	}

	public static <T> MyListNode<T> reversePartialList(MyListNode<T> start, MyListNode<T> end) {
		if(start==null) {
			return null;
		}
		if (start == end) {
			return start;
		}

		MyListNode<T> preNode = start;
		MyListNode<T> currNode = start.next;
		start.next = null;

		while(currNode != null) {
			MyListNode<T> nextNode = currNode.next;
			currNode.next = preNode;
			preNode = currNode;
			currNode = nextNode;

			if (preNode == end) {
				start.next = nextNode;
				return preNode;
			}
		}

		// The node is the new start
		return preNode;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, -1,-1,1,2,3,4,5,6,7,8,9);
		DemoUtils.printList(reversePartialList(head.next.next, head.next.next.next));
		
//		head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9);
//		MyListNode<Integer> tmpHead = new MyListNode<Integer>(null);
//		reverseListRec(head, tmpHead);
//		DemoUtils.printList(tmpHead.next);
		
	}

}
