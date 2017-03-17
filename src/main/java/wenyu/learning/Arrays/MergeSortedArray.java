package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Problem 1: Merge two sorted array to a new array
 * Problem 2: Given two arrays in sorted form. The first array has some empty space equal to the size of 
 *            second array at its end. You have to merge both array in the smartest possible way in the first array. 
 *            With constraint that auxiliary space O(1).
 *            Ex – arr1[9] = {2,4,5,7,8,_,_,_,_};
 *            arr2[4] = {3,6,8,9};
 *            After merging arr1 should be = {2,3,4,5,6,7,8,8,9};
 */

public class MergeSortedArray {
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] problem1(T[] array1, T[] array2) {
		T[] result = (T[]) new Comparable[array1.length+array2.length];
		int resultIdx = 0;
		int i = 0;
		int j = 0;
		for(;i<array1.length&&j<array2.length;) {
			if(array1[i].compareTo(array2[j])<=0) {
				result[resultIdx++] = array1[i++];
			} else {
				result[resultIdx++] = array2[j++];
			}
		}
		
		for(;i<array1.length;) {
			result[resultIdx++] = array1[i++];
		}
		for(;j<array2.length;) {
			result[resultIdx++] = array2[j++];
		}
		
		return result;
	}
	
	public static <T extends Comparable<T>> void problem2(T[] array1, T[] array2) {
		int idx1 = -1;
		int idx2 = array2.length-1;
		for(int i=0; i<array1.length; i++) {
			if(array1[i] == null) {
				idx1 = i-1;
				break;
			}
		}
		
		// Check if has enough space left for array2
		if(idx1==-1 || array1.length-idx1-1!=idx2+1) {
			return;
		}

		// Merge from tail
		int currIdx = array1.length-1;
		for(; currIdx>=0&&idx2>=0&&idx2>=0; currIdx--) {
			if(array1[idx1].compareTo(array2[idx2])>0) {
				array1[currIdx] = array1[idx1--];
			} else {
				array1[currIdx] = array2[idx2--];
			}
		}

		// For case that all element in array1 is added
		// but there is still items in array2 not added.
		// e.g. [4,5,6,0,0,0], [1,2,3]
		while (idx2 >= 0) {
			array1[currIdx--] = array2[idx2--];
		}
	}
	
	public static void main(String[] args) {
		Integer[] numbers1 = {1,2,3,5,7,9,10};
		Integer[] numbers2 = {2,4,6,8,10,11};
		Comparable<Integer>[] merged = problem1(numbers1, numbers2);
		System.out.println(Arrays.toString(merged));
		
		
		System.out.println("===========================================");
		Integer[] numbers3 = {1,2,3,5,7,9,10,null,null,null};
		Integer[] numbers4 = {1,8,13};
		problem2(numbers3, numbers4);
		System.out.println(Arrays.toString(numbers3));
	}
}
