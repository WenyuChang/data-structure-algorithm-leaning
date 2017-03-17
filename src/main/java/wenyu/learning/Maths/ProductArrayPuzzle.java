package wenyu.learning.Maths;

import java.util.Arrays;

/*
 * Given an array arr[] of n integers, construct a Product Array prod[] (of same size) such that prod[i] is equal 
 * to the product of all the elements of arr[] except arr[i]. Solve it without division operator and in O(n).
 * 
 * Example:
 * arr[] = {10, 3, 5, 6, 2}
 * prod[] = {180, 600, 360, 300, 900}
 * 
 * Logic:
 * 1. Initialize prod array to set elements to 1
 * 2. loop original array to get the left product of every element
 * 3. loop original array again from end to get the right product of every element
 */
public class ProductArrayPuzzle {

	public static long[] calculate(int[] array, boolean print) {
		long[] prod = new long[array.length];
		
		// Step 1: initialize prod array (can be omitted)
		for(int i=0; i<prod.length; i++) {
			prod[i] = 1;
		}
		
		// Step 2: calculate left product
		long product = 1;
		for(int i=0; i<array.length; i++) {
			prod[i] = product;
			product *= array[i];
		}
		
		// Step 3: calculate right product
		product = 1;
		for(int i=array.length-1; i>=0; i--) {
			prod[i] *= product;
			product *= array[i];
		}
		
		if(print) System.out.println(Arrays.toString(prod));
		return prod;
	}
	
	public static void main(String[] args) {
		int[] arr = {10, 3, 5, 6, 2};
		calculate(arr, true);
	}

}
