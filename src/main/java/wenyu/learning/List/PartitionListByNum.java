package wenyu.learning.List;

import wenyu.sample.DataStructuresAndAlgAnalysys.Comparable;

/**
 * Created by Wenyu on 12/2/16.
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes
 * greater than or equal to x. You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class PartitionListByNum {
    public <T extends Comparable> MyListNode<T> partition(MyListNode<T> head, T x) {
        MyListNode<T> smallerHead = new MyListNode<T>(null);
        MyListNode<T> smallerPre = smallerHead;
        MyListNode<T> smallerTail = smallerHead;
        int smallerSize = 0;
        MyListNode<T> biggerHead = new MyListNode<T>(null);
        MyListNode<T> biggerPre = biggerHead;
        int biggerSize = 0;

        MyListNode<T> node = head;
        while (node != null) {
            MyListNode<T> next = node.next;

            // Attention: Don't forget this step, or there will be a loop in the list at the end
            node.next = null;

            if (node.getValue().compareTo(x) > 0) {
                smallerPre.next = node;
                smallerPre = node;
                smallerTail = node;
                smallerSize++;
            } else {
                biggerPre.next = node;
                biggerPre = node;
                biggerSize++;
            }
            node = next;
        }

        if (smallerSize == 0) {
            return biggerHead.next;
        } else {
            smallerTail.next = biggerHead.next;
            return smallerHead.next;
        }
    }
}
