package wenyu.learning.List;

/*
 * WAP to merge two linked lists like:
 *    list1: 1->2->3->4
 *    list2: 5->6->7
      o/p list: 1->5->2->6->3->7->4
 */
public class MergeTwoListsAlternatively {
	public static <E> MyListNode<E> merge(MyListNode<E> head1, MyListNode<E> head2) {
		if(head1==null && head2==null) {
			return null;
		} else if(head1==null) {
			return head2;
		} else if(head2==null) {
			return head1;
		}
		
		MyListNode<E> node1 = head1;
		MyListNode<E> node2 = head2;
		while(node1!=null && node2!=null) {
			MyListNode<E> nextNode1 = node1.next;
			MyListNode<E> nextNode2 = node2.next;
			node1.next = node2;
			node2.next = nextNode1;
			if(nextNode1==null && nextNode2!=null) {
				node2.next = nextNode2;
				break;
			}
			node1 = nextNode1;
			node2 = nextNode2;
		}
		
		return head1;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head1 = DemoUtils.generateLinkedList(false, 1,2,3,4);
		MyListNode<Integer> head2 = DemoUtils.generateLinkedList(false, 5,6,7,8,9);
		
		head1 = merge(head1, head2);
		DemoUtils.printList(head1);
	}
}
