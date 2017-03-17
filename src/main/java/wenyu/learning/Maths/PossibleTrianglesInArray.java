package wenyu.learning.Maths;

import java.util.ArrayList;
import java.util.Arrays;

import wenyu.learning.BinarySearch.SearchLower;

/*
 * Given an unsorted array of positive integers. Find the number of triangles that can be formed with three different array 
 * elements as three sides of triangles. For a triangle to be possible from 3 values, the sum of any two values (or sides) 
 * must be greater than the third value (or third side).
 * 
 * For example, if the input array is {4, 6, 3, 7}, the output should be 3. There are three triangles possible {3, 4, 6}, {4, 6, 7} 
 * and {3, 6, 7}. Note that {3, 4, 7} is not a possible triangle.
 * 
 * As another example, consider the array {10, 21, 22, 100, 101, 200, 300}. There can be 6 possible triangles: 
 * {10, 21, 22}, {21, 100, 101}, {22, 100, 101}, {10, 100, 101}, {100, 101, 200} and {101, 200, 300}
 * 
 * Logic: 
 * Solution 1: Brute Force O(n^3)
 * Solution 2: Sort first, then use O(n^2) to find. (If need print, it still will be O(n^3))
 */
public class PossibleTrianglesInArray {

	public static ArrayList<Integer[]> solution1(int[] arr, boolean print) {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		if(arr==null || arr.length<=2) {
			return result;
		}
		
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				for(int k=j+1; k<arr.length; k++) {
					if(arr[i]+arr[j]<=arr[k]) {
						break;
					}
					if(arr[j]+arr[k]<=arr[i]) {
						break;
					}
					if(arr[i]+arr[k]<=arr[j]) {
						break;
					}
					result.add(new Integer[] {arr[i],arr[j],arr[k]});
				}
			}
		}
		
		if(print) {
			for(Integer[] triangle:result) {
				System.out.println(Arrays.toString(triangle));
			}
		}
		return result;
	}
	
	public static ArrayList<Integer[]> solution2(int[] arr, boolean print) {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		if(arr==null || arr.length<=2) {
			return result;
		}
		
		// Step 1: Sort array
		Arrays.sort(arr);
		
		// Step 2: 
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length;j++) {
				int sum = arr[i]+arr[j];
				int idx = SearchLower.SEQSmallerThanSearch(arr, sum, false);
				if(idx<0 || idx<=j) {
					continue;
				}
				while(idx > j) {
					result.add(new Integer[] {arr[i],arr[j],arr[idx--]});
				}
			}
		}
		
		if(print) {
			for(Integer[] triangle:result) {
				System.out.println(Arrays.toString(triangle));
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {4, 6, 3, 7};
		solution1(arr, true);
		
		System.out.println("=========================================");
		
//		int[] arr = {4, 6, 3, 7};
		solution2(arr, true);
	}

}
