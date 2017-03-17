package wenyu.learning.Matrix;

import java.util.Stack;

/*
 * A table composed of N x M cells, each having a certain quantity of apples, is given. 
 * You start from the upper-left corner. At each step you can go down or right one cell. 
 * Find the maximum number of apples you can collect.
 * 
 * Logic: 
 * Solution 1: Using Backtracking to calculate every possible path, and record the maximum sum
 * Solution 2: f(n,m) = max(f(n-1, m), f(n, m-1))
 */
public class MaxSumPathInMatrix {

	public static int solution1(int[][] matrix, boolean print) {
		if(matrix == null) {
			return -1;
		}
		
		Stack<String> path = new Stack<String>();
		int currSum = 0;
		int[] maxSum = {Integer.MIN_VALUE};
		String[] maxPath = {""};
		solution1_core(matrix, 0, 0, path, currSum, maxSum, maxPath);
		
		if(print) System.out.println("Max sum is " + maxSum[0] + ". And path is " + maxPath[0]);
		return maxSum[0];
	}
	public static void solution1_core(int[][] matrix, int row, int col, Stack<String> path, int currSum, int[] maxSum, String[] maxPath) {
		path.push("(" + row + "," + col + ")");
		currSum += matrix[row][col];
		if(col < matrix[0].length-1) {
			solution1_core(matrix, row, col+1, path, currSum, maxSum, maxPath);
		}
		
		if(row < matrix.length-1) {
			solution1_core(matrix, row+1, col, path, currSum, maxSum, maxPath);
		}
		
		if(row==matrix.length-1 && col==matrix[0].length-1) {
			if(currSum > maxSum[0]) {
				maxSum[0] = currSum;
				maxPath[0] = path.toString();
			}
		}
		
		path.pop();
		currSum -= matrix[row][col];
		return;
	}
	
	public static int solution2(int[][] matrix, boolean print) {
		int maxSum = solution2_core(matrix, matrix.length-1, matrix[0].length-1);
		if(print) System.out.println("Max sum is " + maxSum + ".");
		return maxSum;
	}
	private static int solution2_core(int[][] matrix, int row, int col) {
		int curr = 0;
		if(row==0 && col==0) {
			curr = 0;
		} else if(row==0) {
			curr = solution2_core(matrix, row, col-1) ;
		} else if(col==0) {
			curr = solution2_core(matrix, row-1, col);
		} else {
			curr = Math.max(solution2_core(matrix, row, col-1), solution2_core(matrix, row-1, col));
		}
		
		curr += matrix[row][col];
		return curr;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,11}, {9,11,10}, {11,6,3}};
		solution1(matrix, true);

		solution2(matrix, true);
	}

}
