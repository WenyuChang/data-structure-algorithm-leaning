package wenyu.learning.BinarySearch;

import java.util.Random;

import wenyu.learning.Arrays.UtilsForArray;

public class SearchLower extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static final int ArrayLen = 6;
	private static final int MaxItem = 100;

	public static int SEQSmallerThanSearch(int[] array, int k, boolean print) {
		if (array == null || array.length == 0) {
			return -1;
		}

		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] < k) {
				if(print) System.out.println("Result is " + array[i]);
				return i;
			}
		}
		if(print) System.out.println("No Result");
		return -1;
	}
	
	public static int BSSmallerThanSearch_solution1(int[] array, int key, boolean print) {
		if(key<array[0]) {
			if(print) System.out.println("No Result");
			return -1;
		}
		if(key>array[array.length-1]) {
			if(print) System.out.println("Result is " + array[array.length-1]);
			return array.length-1;
		}
		
		int start = 0;
		int end = array.length-1;
		int mid;
		
		while(start<end-1) {
			mid = start + (end-start)/2;
			if(array[mid] < key) {
				start = mid;
			} else if(array[mid] >= key) {
				end = mid-1;
			}
		}
		
		if(array[end] >= key) {
			if(print) System.out.println("Result is " + array[start]);
			return start;
		} else {
			if(print) System.out.println("Result is " + array[end]);
			return end;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int count = 10;
		while(count-- > 0) {
			int[] array = UtilsForArray.generateSortedIntegerArray(ArrayLen, MaxItem);
			UtilsForArray.printArray(array);
			int k = new Random().nextInt(MaxItem - 1);
			System.out.println("k=" + k);
	
	//		demoEntry(new SearchLower(), runtime, SearchLower.class.getMethod("SEQSmallerThanSearch", int[].class, int.class), array, k);
	//		demoEntry(new SearchLower(), runtime, SearchLower.class.getMethod("BSSmallerThanSearch_solution1", int[].class, int.class), array, k);
			
			SEQSmallerThanSearch(array, k, true);
			BSSmallerThanSearch_solution1(array, k, true);
			System.out.println();
		}
	}
}
