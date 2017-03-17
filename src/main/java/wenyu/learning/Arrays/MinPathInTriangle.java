package wenyu.learning.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wenyu on 12/14/16.
 *
 * Problem: Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     For example, given the following triangle
     [
     [2],
     [3,4],
     [6,5,7],
     [4,1,8,3]
     ]
     The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 * Solution 1: Brute force, and using recursive to calculate left and then right.
 * Solution 2: Use aux list to store previous' line's min sum of each value.
 *             Then next line's min sum of each value is [curr_value + last_lines' smaller one of left and right]
 */
public class MinPathInTriangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        } else if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        List<Integer> preMin = new ArrayList<Integer>();
        preMin.add(triangle.get(0).get(0));
        int minRes = Integer.MAX_VALUE;
        for (int i=1; i<triangle.size(); i++) {
            List<Integer> currMin = new ArrayList<Integer>();
            for(int j=0; j<triangle.get(i).size(); j++) {
                if (j == 0) {
                    currMin.add(preMin.get(0) + triangle.get(i).get(j));
                } else if (j == triangle.get(i).size()-1) {
                    currMin.add(preMin.get(preMin.size()-1) + triangle.get(i).get(j));
                } else {
                    int min = triangle.get(i).get(j) + Math.min(preMin.get(j-1), preMin.get(j));
                    currMin.add(min);
                }
                if (i == triangle.size()-1) {
                    minRes = Math.min(minRes, currMin.get(j));
                }
            }
            preMin = currMin;
        }

        return minRes;
    }
}
