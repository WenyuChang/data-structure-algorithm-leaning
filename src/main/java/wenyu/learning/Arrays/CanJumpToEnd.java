package wenyu.learning.Arrays;

/**
 * Created by Wenyu on 11/30/16.
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
public class CanJumpToEnd {
    public boolean canJump(int[] A) {
        int reach = 0;
        int i = 0;
        for ( ; i < A.length && i <= reach; i++) {
            reach = Math.max(reach, i + A[i]);
        }
        return (i == A.length);
    }
}
