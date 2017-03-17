package wenyu.learning.Arrays;

/**
 * Created by Wenyu on 12/1/16.
 *
 * Given an array A[] consisting 0s, 1s and 2s, write a function that sorts A[]. The functions should put all
 * 0s first, then all 1s and all 2s in last.
 * Example
 * Input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
 * Output = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}
 *
 * Solution1: using sorting
 * Solution2: using bucket sort (two-pass)
 * Solution3: using three pointing
 */
public class SortArrayWith0s1s2s {
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void solution3(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0;

        while (mid <= hi)
        {
            switch (nums[mid])
            {
                case 0:
                    swap(nums, lo++, mid++);
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, hi--);
                    break;
            }
        }
    }
}
