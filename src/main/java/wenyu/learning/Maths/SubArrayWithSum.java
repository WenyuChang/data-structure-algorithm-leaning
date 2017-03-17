package wenyu.learning.Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import wenyu.learning.Arrays.UtilsForArray;

/*
 * WAP a program to find a longest continuous subset whose sum is divisible by K. 
 * We are given a array of number (negative+positive).  
 * calculate the complexity of your algorithm
 * 
 * Logic:
 * 	1. using one aux array to store the mod of k:
 * 		s[i] = a[1] + a[2] + ... + a[i]
 *  2. And we can note that, if there is such [i, j] that we are looking for, then:
		s[j]-s[i] == (a[1]+...+a[j]) - (a[1]+...+a[i]) == (a[i]+...+a[j]) == 0
	3. So, for each s[i] == s[j], sum of sub-set [i+1, j]'s sum is K. 
	   Now we need to find the most appropriate range. 
 */
public class SubArrayWithSum {

	public static void find(int[] arr, int k) {
		// O(n^2) & O(n)
		int[] aux = new int[arr.length];
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum += arr[i];
			aux[i] = sum;
			if(aux[i]==0) {
				System.out.println("Find sub-set whose sum is " + k + ". (" + 0 + "," + i +")");
			}
			for(int j=0;j<i;j++) {
				if(aux[i]-aux[j] == k) {
					//Find subset whose sum is K
					if(aux[j] == 0) {
						System.out.println("Find sub-set whose sum is " + k + ". (" + 0 + "," + i +")");
						System.out.println("Find sub-set whose sum is " + k + ". (" + (j+1) + "," + i +")");
					} else {
						System.out.println("Find sub-set whose sum is " + k + ". (" + (j+1) + "," + i +")");
					}
				}
			}
		}
	}
	
	public static void find_optimized(int[] arr, int k) {
		// O(n) & O(n)
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum += arr[i];
			if(map.containsKey(sum-k)) {
				for(int idx : map.get(sum-k)) {
					if(k-sum == 0) {
						System.out.println("Find sub-set whose sum is " + k + ". (" + 0 + "," + i +")");
						System.out.println("Find sub-set whose sum is " + k + ". (" + (idx+1) + "," + i +")");
					} else {
						System.out.println("Find sub-set whose sum is " + k + ". (" + (idx+1) + "," + i +")");
					}
				}
			}
			
			if(map.containsKey(sum)) {
				map.get(sum).add(i);
			} else {
				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(i);
				map.put(sum, al);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = UtilsForArray.generateRandomIntegerArray(10, 20);
		arr = new int[] {1,2,3,4,5,6,7,8,9};
		System.out.println(Arrays.toString(arr));
		int k = 18;
		find(arr, k);
		
		System.out.println("=============================");
		
		find_optimized(arr, k);
	}

}
