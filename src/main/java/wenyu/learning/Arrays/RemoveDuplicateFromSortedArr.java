package wenyu.learning.Arrays;

/**
 * Created by Wenyu on 12/2/16.
 *
 * Problem1: Given a sorted array, remove the duplicates in place such that each element appear
 * only once and return the new length. Do not allocate extra space for another array, you must do this
 * in place with constant memory.
 *
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 *
 * Problem2: What if duplicates are allowed at most twice or k times?
 */
public class RemoveDuplicateFromSortedArr {
    public int problem1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int currLen = 1;
        int curr = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == curr) {
                continue;
            } else {
                nums[currLen++] = nums[i];
                curr = nums[i];
            }
        }

        return currLen;
    }


    public static int problem2(int[] nums, int k) {
        int curr = 0;
        int currV = nums[0];
        int currVC = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == currV && currVC < k) {
                currVC++;
                nums[curr++] = nums[i];
            } else if (nums[i] == currV && currVC >= k) {

            } else if (nums[i] != currV) {
                currV = nums[i];
                currVC = 1;
                nums[curr++] = nums[i];
            }
        }
        return curr;
    }
}
