package wenyu.learning.Arrays;

/*
 * Problem 1: Please find the smallest k numbers (in value) out of n numbers.
 * Problem 2: Please find the smallest kth number in two sorted arrays;
 * Problem 3: Find the smallest number in array
 * Problem 4: Find last 2 smallest numbers in array
 */

public class SmallestKthNumbersFinding {
	// !!! Using partition method
	private static int partition(int[] numbers, int start, int end) {
		int i = start;
		int j = end;
		int pivotV = numbers[i];
		
		while(i<j) {
			while(i<j && numbers[j]>=pivotV) {j--;}
			if(i<j) {
				numbers[i] = numbers[j];
				i++;
			}
			while(i<j && numbers[i]<=pivotV) {i++;}
			if(i<j) {
				numbers[j] = numbers[i];
				j--;
			}
		}
		
		numbers[i] = pivotV;
		return i;
	}
	
	// Find smallest k numbers in array
	public static void find(int[] numbers, int k) {
		int start = 0;
		int end = numbers.length-1;
		
		int idx = partition(numbers, start, end);
		while(idx != k) {
			if(idx > k) {
				end = idx-1;
			} else {
				start = idx+1;
			}
			idx = partition(numbers, start, end);
		}
		
		for(int i=0;i<k;i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
	}
	
	// Find the smallest number in array
	public static void findSmallest(int[] numbers) {
		if(numbers==null && numbers.length<=0) {
			return;
		}
		
		int min = Integer.MAX_VALUE;
		for(int number : numbers) {
			if(number < min) {
				min = number;
			}
		}
		
		System.out.println("The smallest number is " + min);
	}
	
	// Find last 2 smallest numbers in array
	public static void findTwoSmallest(int[] numbers) {
		if(numbers==null && numbers.length<=1) {
			return;
		}
		
		int smallest = (numbers[0]<numbers[1])?numbers[0]:numbers[1];
		int secondSmallest = (numbers[0]>=numbers[1])?numbers[0]:numbers[1];
		
		int i=2;
		for(; i<numbers.length-1; i+=2) {
			if(numbers[i]<numbers[i+1]) {
				if(numbers[i]<smallest) {
					smallest = numbers[i];
					secondSmallest = smallest;
				}
				if(numbers[i+1]<secondSmallest) {
					secondSmallest = numbers[i+1];
				}
			} else {
				if(numbers[i+1]<smallest) {
					smallest = numbers[i+1];
					secondSmallest = smallest;
				}
				if(numbers[i]<secondSmallest) {
					secondSmallest = numbers[i];
				}
			}
		}
		
		if(i == numbers.length-1) {
			if(numbers[i]<smallest) {
				smallest = numbers[i];
				secondSmallest = smallest;
			} else if(numbers[i]<secondSmallest) {
				secondSmallest = numbers[i];
			}
		}
		
		System.out.println("The last 2 smallest number are " + smallest + ", " + secondSmallest);
	}
	
	public static int findKthInTwoSortedArray(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2, int k) {
		/*
		 * Base cases:
		 *	If length of one of the arrays is 0, the answer is kth element of the second array.
		 *	Reduction steps:
		 *	
		 *	If mid index of a + mid index of b is less than k
		 *		If mid element of a is greater than mid element of b, we can ignore the first half of b, adjust k.
		 *		else ignore the first half of a, adjust k.
		 *	Else if k is less than sum of mid indices of a and b:
		 *		If mid element of a is greater than mid element of b, we can safely ignore second half of a
		 *		else we can ignore second half of b
		 *
		 *  TODO Not finished - the logic is correct, but the coding is wrong!!!
		 */
		
		if(s1 == e1) {
			return arr2[s2+k-1];
		} else if(s2 == e2) {
			return arr1[s1+k-1];
		}
		
		int mid1 = s1 + (e1 - s1)/2;
		int mid2 = s2 + (e2 - s2)/2;
		
		if(mid1+mid2 < k){
			if(arr1[mid1-1] == arr2[mid2-1]) {
				return findKthInTwoSortedArray(arr1, mid1, e2, arr2, mid2, e2, k-mid1-mid2);
			} else if(arr1[mid1-1] > arr2[mid2-1]) {
				return findKthInTwoSortedArray(arr1, s1, e1, arr2, mid2, e2, k-mid2);
			} else {
				return findKthInTwoSortedArray(arr1, mid1, e1, arr2, s2, e2, k-mid1);
			}
		} else {
			if(arr1[mid1-1] == arr2[mid2-1]) {
				return findKthInTwoSortedArray(arr1, s1, mid1, arr2, s2, mid2, k);
			} else if(arr1[mid1-1] > arr2[mid2-1]) {
				return findKthInTwoSortedArray(arr1, s1, mid1, arr2, s2, e2, k);
			} else {
				return findKthInTwoSortedArray(arr1, s1, e1, arr2, s2, mid2-1, k);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] numbers = new int[] {4, 5, 1, 6, 2, 7, -2, -1};
		// findSmallest(numbers);
		// find(numbers, 2);
		// findTwoSmallest(numbers);
		
		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {4,5,7};
		int kth = findKthInTwoSortedArray(arr1, 1, 5, arr2, 1, 3, 3);
		System.out.println(kth);
	}

}
