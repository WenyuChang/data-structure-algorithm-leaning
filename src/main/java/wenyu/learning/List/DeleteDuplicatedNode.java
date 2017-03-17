package wenyu.learning.List;

import java.util.HashSet;
import java.util.Set;

/*
Problem1: Given a NOT-sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.

Problem2: Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.
For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Problem3: Given a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list.
For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
 */

public class DeleteDuplicatedNode {

	public static <T> MyListNode<T> problem1(MyListNode<T> head) {
		// List is not sorted
		
		if(head == null) {
			return null;
		}
		
		Set<T> set = new HashSet<T>();
		set.add(head.getValue());
		
		MyListNode<T> node = head.next;
		MyListNode<T> pre = head;
		while(node != null) {
			if(set.contains(node.getValue())) {
				pre.next = node.next;
				node = pre.next;
			} else {
				set.add(node.getValue());
				pre = node;
				node = node.next;
			}
		}
		
		return head;
	}

	public <T> MyListNode<T> problem2(MyListNode<T> head) {
		// List is sorted
		
		if(head == null) {
			return null;
		}

		MyListNode<T> pre = head;
		MyListNode<T> node = head.next;

		while(node != null) {
			if (node.getValue().equals(pre.getValue())) {
				pre.next = node.next;
			} else {
				pre = node;
			}
			node = node.next;
		}

		return head;
	}

	public <T> MyListNode<T> deleteDuplicates(MyListNode<T> head) {
		if(head == null) {
			return null;
		}

		//just to make sure value of temp head does not equal to value of head
		MyListNode<T> tmpHead = new MyListNode<T>(null);
		MyListNode<T> pre = tmpHead;
		MyListNode<T> node = head;

		while(node != null) {
			int count = 1;
			while(node.next != null && node.next.getValue().equals(node.getValue())) {
				node = node.next;
				count++;
			}

			if (count == 1) {
				pre.next = node;
				pre = node;
			}

			node = node.next;
			pre.next = null;
		}

		return tmpHead.next;
	}
	
	public static void main(String[] args) {

	}

}
