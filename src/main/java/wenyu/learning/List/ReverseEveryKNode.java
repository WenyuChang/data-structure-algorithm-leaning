package wenyu.learning.List;

/*
 * Reverse every k node in the link
 * Logic: 
 * 	1. Scan the link from the head, and count k every time
 *  2. Passed in the current start node and end node
 *  	a) reverse current sub-link
 *      b) current end becomes the new start of sub-link
 *      c) current start becomes the new end of sub-link
 *  3. tmpPre's next point the new start if tmpPre is not null 
 *     or the new start will be the new head of link    
 *  4. new end's next will be the tmpNext   
 *  5. next start node to be reverse will be the tmpNext
 */
public class ReverseEveryKNode {

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


	public static <T> MyListNode<T> reverseEveryKList1(MyListNode<T> head, int k) {
		if(head == null ) {
			return null;
		}
		if(k <= 1) {
			return head;
		}

		MyListNode<T> tmpHead = new MyListNode<T>(null);
		tmpHead.next = head;
		MyListNode<T> pre = tmpHead;
		MyListNode<T> node = head;

		MyListNode<T> start = head;

		int currCount = 0;
		while (node != null) {
			MyListNode<T> next = node.next;
			if (currCount == k-1 || next == null) {
				reversePartialList(start, node);
				pre.next = node;
				start.next = next;
				node = start.next;
				pre = start;
				start = node;
				currCount = 0;
			} else {
				node = next;
				currCount ++;
			}
		}

		return tmpHead.next;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9,10,11);
		
		DemoUtils.printList(reverseEveryKList1(head, 3));
	}

}
