package wenyu.learning.BinarySearch;

import java.util.Random;

import wenyu.learning.Arrays.UtilsForArray;
/*
 * Logic:
 * 	Solution1: 
	 * There are there cases:
	 * 1. the search number is smaller than the first number in array
	 * 		start = 0;
	 *  	end = 1;
	 *  	!!! bigger index will be start
	 * 2. the search number is within the array
	 * 		start = end-1;
	 * 		!!! search number will be number[start] or within number[start] and number[end] 
	 * 
	 * 3. the search number is bigger than the biggest number in array
	 * 		start = len-2;
	 * 		end = len - 1;
	 * 		!!! no bigger number will in the array
 *  Solution2:
 *  	Better way!!!
 */
public class SearchCeiling extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static final int ArrayLen = 6;
	private static final int MaxItem = 100;
	
	public static int SEQLargerThanSearch(int[] array, int k, boolean print) {
		if(array == null || array.length == 0) {
			return -1;
		}
		
		for(int i=0;i<array.length;i++) {
			if(array[i]>k) {
				if(print) System.out.println("Result is " + array[i]);
				return i;
			}
		}
		if(print) System.out.println("No Result");
		return -1;
	}
	
	public static int BSLargerThanSearch_solution1(int[] array, int k, boolean print) {
		int low = 0;
		int high = array.length - 1;
		while (low != high) {
			int mid = (low + high) / 2;
			if (array[mid] < k) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		if(array[low]<k) {
			if(print) System.out.println("No Result");
			return -1;
		} else {
			if(print) System.out.println("Result is " + array[low]);
			return low;
		}
	}
	
	public static int BSLargerThanSearch_solution2(int[] array, int key, boolean print) {
		if(key<array[0]) {
			if(print) System.out.println("Result is " + array[0]);
			return 0;
		}
		if(key>array[array.length-1]) {
			if(print) System.out.println("No Result");
			return -1;
		}
		
		int start = 0;
		int end = array.length-1;
		int mid;
		
		while(start<end) {
			mid = start + (end-start)/2;
			if(array[mid] <= key) {
				start = mid+1;
			} else if(array[mid] > key) {
				end = mid;
			}
		}
		
		if(print) System.out.println("Result is " + array[start]);
		return start;
	}
	
	public static void main(String[] args) throws Exception {
		int count = 10;
		while(count-- > 0) {
			int[] array = UtilsForArray.generateSortedIntegerArray(ArrayLen, MaxItem);
			UtilsForArray.printArray(array);
			int k = new Random().nextInt(MaxItem-1);
			System.out.println(k);
			
//			demoEntry(new SearchCeiling(), runtime, SearchCeiling.class.getMethod("SEQLargerThanSearch", int[].class, int.class), array, k);
//			demoEntry(new SearchCeiling(), runtime, SearchCeiling.class.getMethod("BSLargerThanSearch_solution1", int[].class, int.class), array, k);
//			demoEntry(new SearchCeiling(), runtime, SearchCeiling.class.getMethod("BSLargerThanSearch_solution2", int[].class, int.class), array, k);
//			demoEntry(new SearchCeiling(), runtime, SearchCeiling.class.getMethod("BSLargerThanSearch_solution3", int[].class, int.class), array, k);
			
			SEQLargerThanSearch(array, k, true);
			BSLargerThanSearch_solution1(array, k, true);
			BSLargerThanSearch_solution2(array, k, true);
		}
	}
}
