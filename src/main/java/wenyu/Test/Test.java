package wenyu.Test;

import wenyu.learning.List.DemoUtils;
import wenyu.learning.List.MyListNode;
import wenyu.learning.Sort.SortingUtils;
import wenyu.sample.Algorithms4th.In;

import java.util.*;

/**
 * Created by Wenyu on 11/4/16.
 */
public class Test {
    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }

        return false;
    }

    public static void main(String[] args) {


        System.out.println(containsDuplicate(new int[] {3,3}));


        TreeSet<Long> window = new TreeSet<Long>();
        window.floor(1)
    }
}
