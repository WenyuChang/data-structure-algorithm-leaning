package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import wenyu.learning.List.MyListNode;

/*
 * Problem 1: Given an array, containing duplicated numbers except two. Implement a function to find these two numbers.
 * Problem 2: Given an array where all numbers but one occurs in pairs, Find the unique number. What if the array was sorted?
 * Problem 3: Given an array where all numbers but one occurs in three times. Find it.
 * Problem 4: First/Last non-duplicate element in array
 * Problem 5: First/Last duplicate element in array
 * Problem 6: First/Last non-duplicate element in stream
 * Problem 7: First/Last duplicate element in stream
 */

class DuplicateExceptTwoNum {
	/*
	 * Problem 1
	 * Logic:
	 * 	1. First to XOR all the numbers;
	 *  2. Find the first bit which is 1 in the result above
	 *  3. Divide all the numbers to two parts, one is having the above 1 bit and another is not having
	 *  4. XOR these two parts, and find the results
	 */
		
	public static void find(int numbers[]){
		if (numbers.length < 2) {
			return;
		}
		int resultExclusiveOR = 0;
		for (int i = 0; i < numbers.length; ++ i) {
			resultExclusiveOR ^= numbers[i];
		}
		
		int indexOf1 = findFirstBitIs1(resultExclusiveOR);
		int num1 = 0;
		int num2 = 0;
		for (int j = 0; j < numbers.length; ++ j) {
			if(isBit1(numbers[j], indexOf1)) {
				num1 ^= numbers[j];
			} else {
				num2 ^= numbers[j];
			}
		}
		
		System.out.println("These two numbers are " + num1 + " and " + num2);
	}
	
	// The first 1 bit from the rightmost
	private static int findFirstBitIs1(int num){
		int indexBit = 0;
		while (((num & 1) == 0) && (indexBit < 32)) {
			num = num >> 1;
			++ indexBit;
		}
		return indexBit;
	}
	
	// check whether the bit with index indexBit is 1
	private static boolean isBit1(int num, int indexBit) {
		num = num >> indexBit;
		return (num & 1) == 1;
	}
	
	public static void getOnce_solution1(int[] arr) {
		if(arr==null || arr.length<2) {
			return;
		}
		
		int firstStepXORResult = 0;
		for(int num : arr) {
			firstStepXORResult ^= num;
		}
		
		// find first 1 bit
		String binaryStringForFirstXORResult = Integer.toBinaryString(firstStepXORResult);
		int i=binaryStringForFirstXORResult.length()-1;
		for(; i>=0; i--) {
			if(binaryStringForFirstXORResult.charAt(i) == '1') {
				break;
			}
		}
		int posFromEnd = binaryStringForFirstXORResult.length()-1-i;
		
		// divide arrays
		int xorResultWithOneBit = 0;
		int xorResultWithoutOneBit = 0;
		for(int num: arr) {
			if(Integer.toBinaryString(num).charAt(Integer.toBinaryString(num).length()-1-posFromEnd) == '1') {
				xorResultWithOneBit ^= num;
			} else {
				xorResultWithoutOneBit ^= num;
			}
		}
		
		System.out.println("These two numbers are " + xorResultWithOneBit + " and " + xorResultWithoutOneBit);
	}
}

class DuplicateExceptOneNum {
	/*
	 * Problem 2: 
	 */
	public static int findFromUnsortedArr(int[] arr, boolean print) {
		// Find in an unsorted array 
		// Using XOR to find the unique number in array. O(n)
		if(arr.length%2==0) {
			return -1;
		}
		
		int unique = arr[0];
		for(int i=1;i<arr.length;i++) {
			unique ^= arr[i];
		}
		
		if(print) {
			System.out.println("Unique number is " + unique);
		}
		return unique;
	}
	
	public static int findFromSortedArr(int[] arr, boolean print) {
		// Find in an sorted array 
		// Using binary search to find unique number O(logn)
		if(arr.length%2==0) {
			return -1;
		}
		Arrays.sort(arr); // make sure array is sorted.	
		
		int front = 0;
		int end = arr.length-1;
		while(front<=end) {
			int mid = front + (end-front)/2;
			
			if(mid%2==1) {
				if(mid>0 && arr[mid] == arr[mid-1]) {
					front = mid+1;
				} else if(mid<arr.length-1 && arr[mid] == arr[mid+1]) {
					end = mid-1;
				} else {
					if(print) System.out.println("Unique number is " + arr[mid]);
					return arr[mid];
				}
			} else {
				if(mid<arr.length-1 && arr[mid] == arr[mid+1]) {
					front = mid+2;
				} else if(mid>0 && arr[mid] == arr[mid-1]) {
					end = mid-2;
				} else {
					if(print) System.out.println("Unique number is " + arr[mid]);
					return arr[mid];
				}
			}
			
		}
		return -1;
	}
}

class ThreeTimesExceptOneNum {
	/*
	 * Problem 3
	 */
	public static int find(int[] arr, boolean print) {
		// Find unique in array
		// Using hash set to store numbers in array
		// unique = {sum(hash set number)*3-sum(origin array)}/2
		if(arr==null || arr.length==0) {
			return -1;
		}
		
		int arrSum = 0;
		Set<Integer> set = new HashSet<Integer>();
		for(int item : arr) {
			set.add(item);
			arrSum += item;
		}
		
		int sum = 0;
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			sum += it.next();
		}
		
		int unique = (sum*3-arrSum)/2;
		if(print) System.out.println("Unique number is " + unique);
		return unique;
	}
}

class EleNonDupInArray {
	/*
	 * Problem 4
	 * 
	 * Note: LinkedHashSet can be replaced by
	 * 		 1. a double link. store the order of non-duplicate element
	 * 		 2. a map connect value and link node.
	 */
	public static <E> E findFirst(E[] arr, boolean print) {
		HashSet<E> set = new LinkedHashSet<E>();
		for(int i=0; i<arr.length; i++) {
			if(!set.contains(arr[i])) {
				set.add(arr[i]);
			} else {
				set.remove(arr[i]);
			}
		}
		if(!set.isEmpty()) {
			E first = set.iterator().next();
			if(print) System.out.println("Result is " + first);
			return first;
		} else {
			if(print) System.out.println("Result is NULL");
			return null;
		}
	}
	
	public static <E> E findLast(E[] arr, boolean print) {
		HashSet<E> set = new LinkedHashSet<E>();
		for(int i=arr.length-1; i>=0; i--) {
			if(!set.contains(arr[i])) {
				set.add(arr[i]);
			} else {
				set.remove(arr[i]);
			}
		}
		if(!set.isEmpty()) {
			E last = set.iterator().next();
			if(print) System.out.println("Result is " + last);
			return last;
		} else {
			if(print) System.out.println("Result is NULL");
			return null;
		}
	}
}

class EleDupInArray {
	/*
	 * Problem 5
	 */
	public static <E> E findFirst(E[] arr, boolean print) {
		HashSet<E> set = new HashSet<E>();
		for(int i=0; i<arr.length; i++) {
			if(set.contains(arr[i])) {
				if(print) System.out.println("Result is " + arr[i]);
				return arr[i];
			} else {
				set.add(arr[i]);
			}
		}
		if(print) System.out.println("Result is NULL");
		return null;
	}
	
	public static <E> E findLast(E[] arr, boolean print) {
		HashSet<E> set = new HashSet<E>();
		for(int i=arr.length-1; i>=0; i--) {
			if(set.contains(arr[i])) {
				if(print) System.out.println("Result is " + arr[i]);
				return arr[i];
			} else {
				set.add(arr[i]);
			}
		}
		if(print) System.out.println("Result is NULL");
		return null;
	}
}

class EleNonDupInStream {
	/*
	 * Problem 6
	 */
	public static <E> void findFirst(E[] arr, boolean print) {
		HashSet<E> set = new LinkedHashSet<E>();
		for(int i=0; i<arr.length; i++) {
			if(!set.contains(arr[i])) {
				set.add(arr[i]);
			} else {
				set.remove(arr[i]);
			}
			if(!set.isEmpty()) {
				E first = set.iterator().next();
				if(print) System.out.println("Current result is " + first);
			} else {
				if(print) System.out.println("Current result is NULL");
			}
		}
	}
	
	public static <E> void findLast(E[] arr, boolean print) {
		MyListNode<E> pre = new MyListNode<E>(null);
		HashMap<E, MyListNode<E>> map = new HashMap<E, MyListNode<E>>();
		for(int i=0; i<arr.length; i++) {
			if(map.containsKey(arr[i])) {
				MyListNode<E> node = map.get(arr[i]);
				if(pre == node) {
					pre = node.previous;
				}
				node.previous.next = node.next;
				if(node.next!=null) {
					node.next.previous = node.previous;
				}
			} else {
				MyListNode<E> newNode = new MyListNode<E>(arr[i]);
				map.put(arr[i], newNode);
				pre.next = newNode;
				newNode.previous = pre;
				pre = newNode;
			}

			if(print) {
				if(pre.getValue() != null) System.out.println("Current result is " + pre.getValue());
				else System.out.println("Current result is NULL");
			}
		}
	}
}

class EleDupInStream {
	/*
	 * Problem 7
	 */
	public static <E> void findFirst(E[] arr, boolean print) {
		HashSet<E> set = new HashSet<E>();
		E first = null;
		for(int i=0; i<arr.length; i++) {
			if(first==null && set.contains(arr[i])) {
				first = arr[i];
			} else if(first == null) {
				set.add(arr[i]);
			}
			if(print) {
				if(first != null) System.out.println("Current result is " + first);
				else System.out.println("Current result is NULL");
			}
		}
	}
	
	public static <E> void findLast(E[] arr, boolean print) {
		HashSet<E> set = new HashSet<E>();
		E last = null;
		for(int i=0; i<arr.length; i++) {
			if(set.contains(arr[i])) {
				last = arr[i];
			} else {
				set.add(arr[i]);
			}

			if(print) {
				if(last != null) System.out.println("Current result is " + last);
				else System.out.println("Current result is NULL");
			}
		}
	}
}

public class DuplicateFindingProblems {
	public static void main(String[] args) {
		//DuplicateExceptTwoNum.find(new int[] {2, 4, 3, 6, 3, 2, 5, 5});
		
//		EleNonDupInStream.findFirst(new Integer[] {2, 4, 2, 3, 4, 3, 2, 5, 5}, true);
		EleNonDupInStream.findLast(new Integer[] {2, 4, 3, 6, 3, 2, 5, 5}, true);
	}
}
