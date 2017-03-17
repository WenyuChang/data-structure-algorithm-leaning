package wenyu.learning.Arrays;

import java.util.Stack;

/**
 * Created by Wenyu on 12/2/16.
 *
 * Problem1: Find the largest rectangular area possible in a given histogram where the largest rectangle
 * can be made of a number of contiguous bars. For simplicity, assume that all bars have same width and
 * the width is 1 unit.
 *
 * For example, consider the following histogram with 7 bars of heights {6, 2, 5, 4, 5, 2, 6}.
 * The largest possible rectangle possible is 12
 *
 * http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
 *
 * Solution1: A simple solution is to one by one consider all bars as starting points and calculate area of all
 * rectangles starting with every bar. Finally return maximum of all possible areas. Time complexity of this solution
 * would be O(n^2).
 *
 * Solution2: Find the minimum value in the given array. Once we have index of the minimum value, the max area is
 * maximum of following three values.
 *   a) Maximum area in left side of minimum value (Not including the min value)
 *   b) Maximum area in right side of minimum value (Not including the min value)
 *   c) Number of bars multiplied by minimum value.
 * The areas in left and right of minimum value bar can be calculated recursively.
 * Time complexity of this solution would be O(nlogn). Worst case is O(n^2)
 *
 * Solution2-improved: How to find the minimum in each range efficiently? Range Minimum Query using Segment Tree can be
 * used for this. We build segment tree of the given histogram heights. Once the segment tree is built, all range minimum
 * queries take O(Logn) time. So over all complexity of the algorithm becomes.
 *
 * Solution3: For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
 * If we calculate such area for every bar ‘x’ and find the maximum of all areas, our task is done.
 * How to calculate area with ‘x’ as smallest bar? We need to know index of the first smaller (smaller than ‘x’) bar
 * on left of ‘x’ and index of first smaller bar on right of ‘x’. Let us call these indexes as ‘left index’ and
 * ‘right index’ respectively.
 * Time complexity of this solution would be O(n)
 */
public class LargestRectangularAreainHistogram {
    public static class Solution2 {
        private static int helper(int[] heights, int start, int end) {
            if (start == end) {
                return heights[start];
            }

            // First: find minumum value of that range
            // This step can be improved by using Range Minimum Query (RMQ)
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i=start; i<=end; i++) {
                if (heights[i] < min) {
                    min = heights[i];
                    minIdx = i;
                }
            }

            int sizeLeft = -1;
            int sizeRight = -1;
            if (minIdx > start) {
                sizeLeft = helper(heights, start, minIdx-1);
            }

            if (minIdx < end) {
                sizeRight = helper(heights, minIdx+1, end);
            }

            int sizeSelf = min * (end-start+1);
            return Math.max(Math.max(sizeLeft, sizeRight), sizeSelf);
        }

        public static int largestRectangleArea(int[] heights) {
            return helper(heights, 0, heights.length-1);
        }
    }


    public static class Solution3 {
        public static int largestRectangleArea(int[] heights) {
            int[] aux = new int[heights.length];
            Stack<Integer> stack = new Stack<Integer>();

            for (int i=0; i<heights.length; i++) {
                if (stack.isEmpty() || heights[stack.peek()] < heights[i]) {
                    stack.push(i);
                    aux[i] = i;
                } else {
                    while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        aux[i] = 0;
                    } else {
                        aux[i] = stack.peek()+1;
                    }
                    stack.push(i);
                }
            }

            stack.clear();
            int max = 0;
            for (int i=heights.length-1; i>=0; i--) {
                int area = heights[i] * (i-aux[i]+1);
                if (stack.isEmpty() || heights[stack.peek()] < heights[i]) {
                    stack.push(i);
                    max = Math.max(area, max);
                } else {
                    while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        area += heights[i] * (heights.length-1 - i);
                        max = Math.max(area, max);
                    } else {
                        area += heights[i] * (stack.peek()-1 - i);
                        max = Math.max(area, max);
                    }
                    stack.push(i);
                }
            }

            return max;
        }
    }


    public static void main(String[] args) {
        Solution3 aa = new Solution3();
        System.out.println(aa.largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}
