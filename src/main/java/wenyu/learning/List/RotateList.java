package wenyu.learning.List;

/**
 * Created by Wenyu on 12/1/16.
 *
 * Problem: Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 *
 * Attention the special case:
 *  a. k bigger than list length
 *  b. k bigger than list length but k%len == 0, for this case, change k to list length
 */
public class RotateList {
    private <T> MyListNode<T> reversePartialList(MyListNode<T> start, MyListNode<T> end) {
        if (start==null) {
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
        return preNode;
    }

    public <T> MyListNode<T> rotateRight(MyListNode<T> head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        int count = 1;
        // Reverse all the nodes
        MyListNode<T> tmpHead = new MyListNode<T>(null);
        tmpHead.next = head;
        MyListNode<T> node = head;
        while (node.next != null) {
            node = node.next;
            count++;
        }
        reversePartialList(head, node);
        tmpHead.next = node;

        if (k > count) {
            // For case like [1,2,3]/2000000000
            k %= count;
        }
        if (k == 0) {
            // For case like [1,2,3]/6, after k%count==0
            k = count;
        }

        // Reverse the first part of nodes
        MyListNode<T> start = tmpHead.next;
        node = start;
        while (--k > 0 && node.next != null) {
            node = node.next;
        }
        reversePartialList(start, node);
        tmpHead.next = node;

        // Reverse the second part of nodes
        MyListNode<T> pre = start;
        start = start.next;
        node = start;
        while (node != null && node.next != null) {
            node = node.next;
        }
        if (start != null) {
            reversePartialList(start, node);
            pre.next = node;
        }

        return tmpHead.next;
    }
}
