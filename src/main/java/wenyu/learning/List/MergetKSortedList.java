package wenyu.learning.List;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wenyu on 11/28/16.
 */
public class MergetKSortedList {
    public MyListNode<Integer> mergeKLists(MyListNode<Integer>[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }

        Comparator<MyListNode<Integer>> comparator = new Comparator<MyListNode<Integer>>() {
            public int compare(MyListNode<Integer> o1, MyListNode<Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        };

        MyListNode<Integer> tmpHead = new MyListNode(-1);
        MyListNode<Integer> pre = tmpHead;
        PriorityQueue<MyListNode<Integer>> queue = new PriorityQueue<MyListNode<Integer>>(comparator);
        Map<MyListNode<Integer>, Integer> map = new HashMap<MyListNode<Integer>, Integer>();
        for (int i=0; i<lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            queue.offer(lists[i]);
            map.put(lists[i], i);
        }

        while (!queue.isEmpty()) {
            MyListNode<Integer> currMin = queue.poll();
            pre.next = currMin;
            pre = currMin;

            int idx = map.get(currMin);
            map.remove(currMin);
            lists[idx] = lists[idx].next;
            if (lists[idx] != null) {
                queue.offer(lists[idx]);
                map.put(lists[idx], idx);
            }
        }

        return tmpHead.next;
    }
}
