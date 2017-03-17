package wenyu.learning.BigNumbers;

import wenyu.learning.List.DemoUtils;
import wenyu.learning.List.MyListNode;
import wenyu.learning.List.ReverseNode;

/*
 * Add two linked lists. And code it.
 * Input : 1->2->3->4->NULL
           8->2->NULL
 * Output : 1->3->1->6->NULL
 */
public class AddLink {
	public static MyListNode<Integer> add(MyListNode<Integer> l1, MyListNode<Integer> l2) {
		// Logic: 
		// 1. Reverse two links
		// 2. Add one by one, (including carry)
		// 3. Reverse the result
		
		if(l1==null) {
			return l2;
		} else if(l2==null) {
			return l1;
		}
		
		// Step 1: Reverse two links
		MyListNode<Integer> newL1 = ReverseNode.reverseList(l1);
		MyListNode<Integer> newL2 = ReverseNode.reverseList(l2);
		
		// Step 2: Add one by one
		int carry = 0;
		MyListNode<Integer> tmpHead = new MyListNode<Integer>();
		MyListNode<Integer> pre = tmpHead;
		while(newL1!=null && newL2!=null) {
			int value = newL1.getValue() + newL2.getValue() + carry;
			if(value >= 10) {
				carry = value/10;
				value = value%10;
			} else {
				carry = 0;
			}
			MyListNode<Integer> node = new MyListNode<Integer>(value);
			pre.next = node;
			pre = node;
			newL1 = newL1.next;
			newL2 = newL2.next;
		}
		
		while(newL1 != null) {
			int value = newL1.getValue() + carry;
			if(value >= 10) {
				carry = value/10;
				value = value%10;
			} else {
				carry = 0;
			}
			MyListNode<Integer> node = new MyListNode<Integer>(value);
			pre.next = node;
			pre = node;
			newL1 = newL1.next;
		}
		
		while(newL2 != null) {
			int value = newL2.getValue() + carry;
			if(value >= 10) {
				carry = value/10;
				value = value%10;
			} else {
				carry = 0;
			}
			MyListNode<Integer> node = new MyListNode<Integer>(value);
			pre.next = node;
			pre = node;
			newL2 = newL2.next;
		}
		
		// Step 3: Reverse result
		tmpHead = ReverseNode.reverseList(tmpHead.next);
		return tmpHead;
	}
	
	public static MyListNode<Integer> addRec(MyListNode<Integer> l1, MyListNode<Integer> l2) {
		if(l1==null) {
			return l2;
		} else if(l2==null) {
			return l1;
		}
		
		// Step 1: Check the length of l1 and l2, and get the difference
		int l1Count = 0;
		MyListNode<Integer> node = l1;
		while(node!=null) {
			l1Count++;
			node = node.next;
		}
		int l2Count = 0;
		node = l2;
		while(node!=null) {
			l2Count++;
			node = node.next;
		}
		int diff = l1Count - l2Count;
		
		// According to the difference recursion
		MyListNode<Integer> head = new MyListNode<Integer>();
		int carry = addRec_core(l1, l2, diff, head);
		if(carry>0) {
			head.setValue(carry);
		} else {
			head = head.next;
		}
		return head;
	}
	private static int addRec_core(MyListNode<Integer> l1, MyListNode<Integer> l2, int diff, MyListNode<Integer> currNode) {
		if(l1==null && l2==null) {
			return 0;
		}
		
		MyListNode<Integer> node = new MyListNode<Integer>();
		int value = 0;
		int carry = 0;
		if(diff==0) {
			carry = addRec_core(l1.next, l2.next, diff, node);
			value = carry + l1.getValue() + l2.getValue();
		} else if(diff>0) {
			carry = addRec_core(l1.next, l2, diff-1, node);
			value = carry + l1.getValue();
		} else {
			carry = addRec_core(l1, l2.next, diff+1, node);
			value = carry + l2.getValue();
		}
		
		if(value >= 10) {
			carry = value/10;
			value = value%10;
		} else {
			carry = 0;
		}
		node.setValue(value);
		currNode.next = node;
		return carry;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head1 = DemoUtils.generateLinkedList(false, 1,2,3,5,6,7,8,9);
		MyListNode<Integer> head2 = DemoUtils.generateLinkedList(false, 9,9,9);
		
		head1 = addRec(head1, head2);
		DemoUtils.printList(head1);
	}
}
