package wenyu.learning.Others;

/**
 * Created by Wenyu on 11/29/16.
 *
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 * which together with x-axis forms a container, such that the container contains the most water.
 *
 * https://leetcode.com/problems/container-with-most-water/
 *
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }

        int left = 0;
        int right = height.length-1;
        int max = -1;
        while (left < right) {
            int minH = Math.min(height[left], height[right]);
            int area = minH * (right - left);
            max = Math.max(area, max);

            if (height[left] <= height[right]) left++;
            else right--;
        }

        return max;
    }
}
