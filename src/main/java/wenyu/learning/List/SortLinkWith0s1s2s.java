package wenyu.learning.List;

/*
 * Given a linked list of 0s, 1s and 2s, sort it.
 *
 * See Arrays/SortArrayWith0s1s2s
 */
public class SortLinkWith0s1s2s {

	public static MyListNode<Integer> sort(MyListNode<Integer> head) {
		MyListNode<Integer> tmpZeroHead = new MyListNode<Integer>(-1);
		MyListNode<Integer> tmpOneHead = new MyListNode<Integer>(-1);
		MyListNode<Integer> tmpTwoHead = new MyListNode<Integer>(-1);
		MyListNode<Integer> tmpZeroTail = null;
		MyListNode<Integer> tmpOneTail = null;
		
		MyListNode<Integer> currNode = head;
		while(currNode != null) {
			MyListNode<Integer> next = currNode.next;
			if(currNode.getValue() == 0) {
				if(tmpZeroHead.next == null) {
					tmpZeroTail = currNode;
				}
				currNode.next = tmpZeroHead.next;
				tmpZeroHead.next = currNode;
			} else if(currNode.getValue() == 1) {
				if(tmpOneHead.next == null) {
					tmpOneTail = currNode;
				}
				currNode.next = tmpOneHead.next;
				tmpOneHead.next = currNode;
			} else if(currNode.getValue() == 2) {
				currNode.next = tmpTwoHead.next;
				tmpTwoHead.next = currNode;
			} else {
				return null;
			}
			
			currNode = next;
		}
		
		if(tmpOneTail != null) {
			tmpOneTail.next = tmpTwoHead.next;
			if(tmpZeroTail != null) {
				tmpZeroTail.next = tmpOneHead.next;
				return tmpZeroHead.next;
			} else {
				return tmpOneHead.next;
			}
		} else if(tmpZeroTail != null) {
			tmpZeroTail.next = tmpTwoHead.next;
			return tmpZeroHead.next;
		} else {
			return tmpTwoHead.next;
		}
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 0,1,1,2,1,2,2,2,1,2,0,1,2,2,1,1,0,0,2,1,0,0);
		DemoUtils.printList(head);
		
		System.out.println("===============================");
		head = sort(head);
		DemoUtils.printList(head);
		
	}

}
