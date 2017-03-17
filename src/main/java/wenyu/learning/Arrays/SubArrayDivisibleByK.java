package wenyu.learning.Arrays;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * WAP a program to find a longest continuous subset whose sum is divisible by K. 
 * We are given a array of number (negative+positive).  
 * calculate the complexity of your algorithm
 * 
 * Logic:
 * 	1. using one aux array to store the mod of k:
 * 		s[i] = (a[1] + a[2] + ... + a[i]) % k
 *  2. And we can note that, if there is such [i, j] that we are looking for, then:
		s[j]-s[i] == (a[1]+...+a[j]) - (a[1]+...+a[i]) == (a[i]+...+a[j]) == 0
	3. So, for each s[i] == s[j], sum of sub-set [i, j] is divisible by k. 
	   Now we need to find the most appropriate range. 
 */
public class SubArrayDivisibleByK {

	public static void find(int[] arr, int k) {
		int[] aux = new int[arr.length];
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum += arr[i];
			aux[i] = sum%k;
			if(aux[i]==0) {
				System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + 0 + "," + i +")");
			}
			for(int j=0;j<i;j++) {
				if(aux[j] == aux[i]) {
					//Find subset whose sum is divisible by K
					if(aux[j]==0) {
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + 0 + "," + i +")");
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + (j+1) + "," + i +")");
					} else {
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + (j+1) + "," + i +")");
					}
				}
			}
		}
	}
	
	public static void find_optimized(int[] arr, int k) {
		// O(n) & O(n)
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> alForFirstIdx = new ArrayList<Integer>();
		alForFirstIdx.add(-1);
		map.put(0, alForFirstIdx);
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum += arr[i];
			if(map.containsKey(sum%k)) {
				for(int idx : map.get(sum%k)) {
					if(sum%k == 0) {
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + 0 + "," + i +")");
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + (idx+1) + "," + i +")");
					} else {
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + (idx+1) + "," + i +")");
					}
				}
			}
			
			if(map.containsKey(sum%k)) {
				map.get(sum%k).add(i);
			} else {
				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(i);
				map.put(sum%k, al);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {7,1,2,5,7,8,13};
		int k = 8;
		find(arr, k);
		
		System.out.println("=============================");

		find_optimized(arr, k);
	}

}
