package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y in arr[]. 
 * The array might also contain duplicates. You may assume that both x and y are different and present in arr[].
 * Same as RMQ (Range Minimum Query)
 * 
 * Examples:
 * Input: arr[] = {1, 2}, x = 1, y = 2
 * Output: Minimum distance between 1 and 2 is 1.
 * 
 * Input: arr[] = {3, 4, 5}, x = 3, y = 5
 * Output: Minimum distance between 3 and 5 is 2.
 * 
 * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
 * Output: Minimum distance between 3 and 6 is 4.
 * 
 * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
 * Output: Minimum distance between 3 and 2 is 1.
 * 
 * Logic: 
 * 1. Find all the occurrence of E1 and E2, store index of them into two array
 * 2. Since above occurrence array is sorted, use MinDiffInTwoArrays to find the minimum difference of the items in two arrays.
 */

public class MinimumDistanceBetweenTwoInArray {

	public static <E> int find(E[] arr, E e1, E e2, boolean print) {
		if(arr==null || arr.length==0) {
			if(print) System.out.println("Result is -1");
			return -1;
		}
		if(e1.equals(e2)) {
			if(print) System.out.println("Result is 0");
			return 0;
		}
		
		int[] occurenceE1Idx = new int[arr.length];
		int e1Idx = 0;
		int[] occurenceE2Idx = new int[arr.length];
		int e2Idx = 0;
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i].equals(e1)) {
				occurenceE1Idx[e1Idx++] = i;
			} else if(arr[i].equals(e2)) {
				occurenceE2Idx[e2Idx++] = i;
			}
		}
		
		occurenceE1Idx = Arrays.copyOfRange(occurenceE1Idx, 0, e1Idx);
		occurenceE2Idx = Arrays.copyOfRange(occurenceE2Idx, 0, e2Idx);
		int minDiff = MinDiffInTwoArrays.findWithSort(occurenceE1Idx, occurenceE2Idx, false);
		if(print) System.out.println("Result is " + minDiff);
		return minDiff;
	}
	
	public static void main(String[] args) {
		Integer[] arr = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
		find(arr, 3, 6, true);
	}

}
