package wenyu.learning.Arrays;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Given an array containing only 0s and 1s, find the largest subarray which contain equal no of 0s and 1s. 
 * Expected time complexity is O(n).
 * 
 * Examples:
 * Input: arr[] = {1, 0, 1, 1, 1, 0, 0}
 * Output: 1 to 6 (Starting and Ending indexes of output subarray)
 * 
 * Input: arr[] = {1, 1, 1, 1}
 * Output: No such subarray
 * 
 * Input: arr[] = {0, 0, 1, 1, 0}
 * Output: 0 to 3 Or 1 to 4
 * 
 * Logic:
 * 1) Consider all 0 values as -1. The problem now reduces to find out the maximum length subarray with sum = 0.
 * 2) Use almost the same logic of sub array divisible by K.
 */
public class LargestSubarrWithEqual0sAnd1s {

	public static void find(int[] arr) {
		int longest = -1;
		int idx1 = -1;
		int idx2 = -1;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] == 0) {
				sum += -1;
			} else if(arr[i] == 1) {
				sum += 1; 
			}
			if(map.containsKey(sum)) {
				int idx = map.get(sum);
				if(i-(idx+1)+1 > longest) {
					idx1 = idx+1;
					idx2 = i;
					longest = i-(idx+1)+1;
				}
			}
			
			if(!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		
		if(longest!=-1) {
			System.out.println(idx1 + " to " + idx2);
		} else {
			System.out.println("No such subarray");
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {0, 0, 1, 1, 0};
		find(arr);
	}

}
