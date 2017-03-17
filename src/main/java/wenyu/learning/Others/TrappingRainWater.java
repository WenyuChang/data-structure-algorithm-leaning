package wenyu.learning.Others;

/**
 * Created by Wenyu on 11/29/16.
 *
 * Problem: Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Solution: Initialize two aux array.
 * 1. The first aux array: auxL[i] is the left highest from 0 to i;
 * 2. The second aux array: auxR[i] is the right highest from i to end;
 * 3. Go through array, for each height[i], find the min of auxL[i] and auxR[i], sum += height[i] + min(auxL[i], auxR[i]);
 *    when min(auxL[i], auxR[i]) bigger than height[i]
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }

        int[] aux = new int[height.length];
        aux[0] = height[0];
        for (int k=1; k<height.length; k++) {
            if (aux[k-1] > height[k]) {
                aux[k] = aux[k-1];
            } else {
                aux[k] = height[k];
            }
        }

        int[] auxR = new int[height.length];
        auxR[height.length-1] = height[height.length-1];
        for (int k=height.length-2; k>=0; k--) {
            if (auxR[k+1] > height[k]) {
                auxR[k] = auxR[k+1];
            } else {
                auxR[k] = height[k];
            }
        }

        int sum = 0;
        for (int k=0; k<height.length; k++) {
            int highest = Math.min(aux[k], auxR[k]);
            if (highest > height[k]) {
                sum += highest - height[k];
            }
        }

        return sum;
    }
}
