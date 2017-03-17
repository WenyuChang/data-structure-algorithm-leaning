package wenyu.learning.BinarySearch;

import java.util.Arrays;
import java.util.Random;

import wenyu.learning.Arrays.UtilsForArray;

/*
 * Find the turning index of a array
 * For example: arr: 1,2,3,5,2,1 --> turning index is 3
 * 
 * Logic:
 * 	Solution1: sequence scan
 *  Solution2: O(logn)
 *  	1. find the mid of array,
 *  	2. if array[mid]>=array[mid-1] and array[mid]<=array[mid+1]
 *  	   means mid is in the increase sub-array
 *         then start=mid
 *      3. reverse...
 */
public class TurningArraySearch extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static final int ArrayLen = 20;
	private static final int MaxItem = 1000;

	private static int[] generateTunningArray() {
		int[] tmpArr = UtilsForArray.generateSortedIntegerArray(ArrayLen, MaxItem);
		int turningIdx = new Random().nextInt(ArrayLen - 1);		
		
		int start = 0;
		int end = tmpArr.length-1;
		
		while(start<end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		start = 0;
		end = turningIdx;
		
		while(start<end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		return tmpArr;
	}
	
	public static int sequentialSearch(int[] array, boolean print) {
		for(int i=0;i<array.length-1;i++) {
			if(array[i] > array[i+1]) {
				if(print) System.out.println("Find such value. Index is " + i + ".");
				return i;
			}
		}
		if(print) System.out.println("No such value in the array.");
		return -1;
	}
	
	public static int binarySearch(int[] array, boolean print) {
		if (array.length <= 2) {
			if(print) System.out.println("No such value in the array.");
			return -1;
		}
		
		int left = 0;
		int right = array.length - 1;
		while (right > left + 1) {
			int middle = (left + right) / 2;
			if (middle == 0 || middle == array.length - 1) {
				if(print) System.out.println("No such value in the array.");
				return -1;
			}
			if (array[middle] >= array[middle - 1] && array[middle] > array[middle + 1]) {
				if(print) System.out.println("Find such value. Index is " + middle + ".");
				return middle;
			} else if (array[middle] >= array[middle - 1] && array[middle] <= array[middle + 1]) {
				left = middle;
			} else {
				right = middle;
			}
		}
		
		if(print) System.out.println("No such value in the array.");
		return -1;
	}
	
	public static int findItemInTurningArray(int[] array, int k, boolean print) {
		int turnIdx = binarySearch(array, false);
		if(turnIdx<0) {
			int idx = BasicBinarySearch.binarySearch(array, k);
			if(print) System.out.println("Find such value. Index is " + idx + ".");
			return idx;
		}
		
		int result = BasicBinarySearch.binarySearch(Arrays.copyOfRange(array, 0, turnIdx), k);
		if(result >= 0) {
			if(print) System.out.println("Find such value. Index is " + result + ".");
			return result;
		}
		result = BasicBinarySearch.binarySearchNonAsc(Arrays.copyOfRange(array, turnIdx, array.length), k);
		if(result >= 0) {
			if(print) System.out.println("Find such value. Index is " + (result+turnIdx) + ".");
			return result+turnIdx;
		}
		
		if(print) System.out.println("No such item.");
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		int[] array = generateTunningArray();
		System.out.println(Arrays.toString(array));
		
		binarySearch(array, true);
		sequentialSearch(array, true);
		
		findItemInTurningArray(array, array[9], true);
	}


}
