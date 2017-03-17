package wenyu.learning.Maths.PermutationAndCombination;

import java.util.HashMap;

/*
 * There is a particular sequence only uses the numbers 1, 2, 3, 4 and no two adjacent numbers are the same.
 * Write a program that given n1 1s, n2 2s, n3 3s, n4 4s will output the number of such sequences using all these numbers.
 * input:  there is four 1s, one 2s, one 3s and two 4s
 * so with above input, possible sequences could be 41234 14234 ...
 * output: 36 (different sequences can meet the requirement)
 *
 *
 * Solution 1: Using permutation O(4^n)
 * Solution 2: Using DP O(n1*n2*n3*n4)
 * 			   let dpX[i][j][k][l] stand for the number of such sequences that ends with X, 
 * 			   X is 1,2,3 or 4 with i ones, j twos, k threes, l fours. Then you'll have the formula for dp1:
			   dp1[i][j][k][l] = dp2[i-1][j][k][l] + dp3[i-1][j][k][l] + dp4[i-1][j][k][l]
			   And the analogous for dp2, dp3 and dp4. The answer will be the 
			   dp1[n1][n2][n3][n4] + dp2[n1][n2][n3][n4] + dp3[n1][n2][n3][n4] + dp4[n1][n2][n3][n4].
 * 
 */
public class PermutationCountOfNumSets {
	
	public static long solution1(HashMap<Integer, Integer> resource, Integer preEle) {
		boolean hasLeft = false;
		for(Integer ele : resource.keySet()) {
			if(resource.get(ele)!=0) {
				hasLeft = true;
			}
		}
		if(!hasLeft) return 1;
		
		long count = 0;
		for(Integer ele : resource.keySet()) {
			if(ele==preEle || resource.get(ele)==0) {
				continue;
			}
			
			resource.put(ele, resource.get(ele)-1);
			count += solution1(resource, ele);
			resource.put(ele, resource.get(ele)+1);
		}
		
		return count;
	}
	
	public static long solution2() {
		
		return -1;
	}
	
	
	public static void main(String[] args) {
		HashMap<Integer, Integer> resource = new HashMap<Integer, Integer>();
		resource.put(1, 1);
		resource.put(2, 1);
		resource.put(3, 1);
		resource.put(4, 2);
		
		long result = solution1(resource, null);
		System.out.println("Result is " + result);
	}

}
