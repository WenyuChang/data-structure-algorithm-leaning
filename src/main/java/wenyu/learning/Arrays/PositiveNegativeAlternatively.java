package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Given an array of positive and negative numbers, arrange them in an alternate fashion such that every 
 * positive number is followed by negative and vice-versa maintaining the order of appearance.
 * Number of positive and negative numbers need not be equal. If there are more positive numbers they 
 * appear at the end of the array. If there are more negative numbers, they too appear in the end of 
 * the array.
 * 
 * Example:
 * Input:  arr[] = {1, 2, 3, -4, -1, 4}
 * Output: arr[] = {-4, 1, -1, 2, 3, 4}
 * Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
 * output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0}
 * 
 * Solution 1: Use extra space
 * Solution 2: Scan number one by one, if this number is not in correct place, 
 *             then find next correct number after this one and swap these two.
 */
public class PositiveNegativeAlternatively {
	/*
	 * In order to make coding much easier, change the problem to odd number and even number
	 */
	
	public static void solution1(int[] arr, boolean print) {
		int[] odd = new int[arr.length];
		int oddIdx = 0;
		int[] even = new int[arr.length];
		int evenIdx = 0;
		
		for(int i=0; i<arr.length; i++) {
			boolean flag = arr[i]%2==0; // This can be replace to arr[i]>0 to server original problems.
			if(flag) {
				even[evenIdx++] = arr[i];
			} else {
				odd[oddIdx++] = arr[i];
			}
		}
		
		for(int i=0; i<arr.length; i++) {
			if(evenIdx>0 && i%2==0) {
				arr[i] = even[--evenIdx];
			} else if(oddIdx>0){
				arr[i] = odd[--oddIdx];
			}
		}
		
		if(print) System.out.println(Arrays.toString(arr));
	}
	
	public static void solution2(int[] arr, boolean print) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i]%2==0&&i%2==0 || arr[i]%2==1&&i%2==1) {
				continue;
			}
			
			int correctIdx = i+1;
			if(i%2 == 0) {
				while(correctIdx<arr.length && arr[correctIdx]%2!=i%2) {
					correctIdx++;
				}
				if(correctIdx == arr.length) {
					return;
				}
			}
			
			int tmp = arr[i];
			arr[i] = arr[correctIdx];
			arr[correctIdx] = tmp;
		}
	}
	
	public static void main(String[] args) {
		int[] array = UtilsForArray.generateRandomIntegerArray(10, 100);
		System.out.println(Arrays.toString(array));
		solution2(array, true);
		System.out.println(Arrays.toString(array));
	}

}
