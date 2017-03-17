package wenyu.learning.Arrays;

/*
 * Given an array of integers where each element represents the max number of steps that can be made forward 
 * from that element. Write a function to return the minimum number of jumps to reach the end of the array 
 * (starting from the first element). If an element is 0, then cannot move through that element.
 * 
 * Example:
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 8 ->9)
 * First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.
 * 
 * Solution 1:
 * arr naive approach is to start from the first element and recursively call for all the elements reachable from 
 * first element. The minimum number of jumps to reach end from first can be calculated using minimum number of 
 * jumps needed to reach end from the elements reachable from first.
 * 
 * Solution 2: From back to the first:
 * F(n) = min(f(a), f(b), ... , f(k))+1   [arr(k) >= (arr.length-k)]
 */

public class MinimumJumpsToEnd {

	public static int solution1(int[] arr, int idx) {
		int currMaxJump = arr[idx];
		int step = Integer.MAX_VALUE;
		for(int i=1; i<=currMaxJump; i++) {
			if(idx+i >= arr.length) {
				break;
			} else if(idx+i == arr.length-1) {
				return 1;
			}
			
			int currStep = solution1(arr, idx+i);
			if(currStep>0 && currStep<step) {
				step = currStep;
			};
		}
		
		if(step == Integer.MAX_VALUE) {
			return -1;
		}
		return step+1;
	}
	
	public static int solution2(int[] arr, int idx) {
		if(idx==0) {
			return 0;
		}
		
		int smallest = Integer.MAX_VALUE;
		for(int i=0; i<idx; i++) {
			if(arr[i] >= idx-i) {
				int next = solution2(arr, i);
				if(next!=-1 && next<smallest) {
					smallest = next;
				}
			}
		}
		
		if(smallest == Integer.MAX_VALUE) {
			smallest = -1;
			return -1;
		}
		return smallest+1;
	}
	
	public static int solution3(int[] arr) {
		int steps = 0;
		if(arr.length==0 || arr.length==1) {
			return 0;
		}

		for(int i=1; i<arr.length-1 ;i++) {
			arr[i]=Math.max(arr[i], arr[i-1]-1);
		}

		int i=0;
		while(i<arr.length-1) {
			steps++;
			if(arr[i]==0 && i!=arr.length-1) {
				return -1;
			}
			i = i+arr[i];
		}
		return steps;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 3, 0, 0, 0, 2, 1, 2, 6, 8, 9, 10};
		arr =  new int[] {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		int step = solution1(arr, 0);
		System.out.println("Result is " + step);
		

		step = solution2(arr, arr.length-1);
		System.out.println("Result is " + step);
		
		step = solution3(arr);
		System.out.println("Result is " + step);
	}

}
