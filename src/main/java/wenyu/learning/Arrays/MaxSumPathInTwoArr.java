package wenyu.learning.Arrays;

/*
 * Given two sorted arrays such the arrays may have some common elements. Find the sum of the maximum sum 
 * path to reach from beginning of any array to end of any of the two arrays. We can switch from one array 
 * to another array only at common elements.
 * Expected time complexity is O(m+n) where m is the number of elements in ar1[] and n is the number of elements in ar2[].
 * 
 * Examples:
 * Input:  ar1[] = {2, 3, 7, 10, 12}, ar2[] = {1, 5, 7, 8}
 * Output: 35
 * 35 is sum of 1 + 5 + 7 + 10 + 12. We start from first element of arr2 which is 1, then we move to 5, then 7.  From 7, 
 * we switch to ar1 (7 is common) and traverse 10 and 12.
 * 
 * Input:  ar1[] = {10, 12}, ar2 = {5, 7, 9}
 * Output: 22
 * 22 is sum of 10 and 12. Since there is no common element, we need to take all elements from the array with more sum.
 * 
 * Input:  ar1[] = {2, 3, 7, 10, 12, 15, 30, 34}, ar2[] = {1, 5, 7, 8, 10, 15, 16, 19}
 * Output: 122
 * 122 is sum of 1, 5, 7, 8, 10, 12, 15, 30, 34
 */
public class MaxSumPathInTwoArr {

	public static int findMaxPath(int[] arr1, int[] arr2, boolean print) {
		int sum = 0;
		
		if(arr1==null && arr2==null) {
			return sum;
		} else if(arr1==null && arr2!=null) {
			for(int ele : arr2) {
				sum += ele;
			}
		} else if(arr1!=null && arr2==null) {
			for(int ele : arr1) {
				sum += ele;
			}
		} else {
			int idx1 = 0;
			int idx2 = 0;
			int tmpSum1 = 0;
			int tmpSum2 = 0;
			
			while(idx1<arr1.length && idx2<arr2.length) {
				if(arr1[idx1] == arr2[idx2]) {
					sum += (tmpSum1>tmpSum2)?tmpSum1:tmpSum2;
					sum += arr1[idx1];
					tmpSum1 = 0;
					tmpSum2 = 0;
					idx1++;
					idx2++;
				} else if(arr1[idx1] < arr2[idx2]) {
					tmpSum1 += arr1[idx1++];
				} else {
					tmpSum2 += arr2[idx2++];
				}
			}
			
			while(idx1<arr1.length) {
				tmpSum1 += arr1[idx1++];
			}
			while(idx2<arr2.length) {
				tmpSum2 += arr2[idx2++];
			}
			sum += (tmpSum1>tmpSum2)?tmpSum1:tmpSum2;
		}
		
		if(print) System.out.println("Maximum sum is " + sum);
		return sum;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {2, 3, 7, 10, 12};
		int[] arr2 = {1, 5, 7, 8};
		findMaxPath(arr1, arr2, true);
	}

}
