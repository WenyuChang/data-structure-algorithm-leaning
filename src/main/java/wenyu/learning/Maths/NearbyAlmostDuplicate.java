package wenyu.learning.Maths;

import java.util.TreeSet;

/**
 * Created by Wenyu on 1/17/17.
 *
 * Problem:
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference
 * between i and j is at most k.
 *
 * Solution 1: Using treeset. O(nlogn)
 * Solution 2: O(n) / O(n)
 */
public class NearbyAlmostDuplicate {
    public boolean Solution1(final int[] nums, int k, long t) {
        if (nums.length < 2) {
            return false;
        }

        if (k == 0) {
            return false;
        }

        TreeSet<Long> treeSet = new TreeSet<Long>();
        for(int i=0; i<nums.length; i++) {
            // Better statement
            if (treeSet.floor(nums[i] + t) != null && treeSet.floor(nums[i] + t) >= nums[i] - t) {
                // if there is a number less than nums[i] + t exists, there are two cases:
                // a. this number is bigger than nums[i], but smaller than nums[i]+t, meet the requirement
                // b. this number is smaller than nums[i],
                //    another condition is needed to make sure it is bigger than nums[i]-t
                return true;
            }

            treeSet.add(new Long(nums[i]));


            if (i >= k) {
                //remove one, the size has to be k on the next fresh step
                treeSet.remove(new Long(nums[i-k]));
            }
        }

        return false;
    }
}
