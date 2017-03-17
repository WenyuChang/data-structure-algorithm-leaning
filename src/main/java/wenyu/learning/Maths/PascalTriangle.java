package wenyu.learning.Maths;

import wenyu.learning.Maths.PermutationAndCombination.Combination;

import java.util.ArrayList;
import java.util.List;

/*
 * Pascal’s triangle is a triangular array of the binomial coefficients. 
 * Write a function that takes an integer value n as input and prints first 
 * n lines of the Pascal’s triangle. Following are the first 6 rows of 
 * Pascal’s Triangle.
	1  
	1 1 
	1 2 1 
	1 3 3 1 
	1 4 6 4 1 
	1 5 10 10 5 1 
 *	
 * Logic:
 * Solution 1: (O(n^3) time complexity)
 * Number of entries in every line is equal to line number. For example, 
 * the first line has “1”, the second line has “1 1″, the third line has “1 2 1″,.. and so on. 
 * Every entry in a line is value of a Binomial Coefficient. The value of ith entry in line number 
 * line is C(line, i). The value can be calculated using following formula.
 * C(line, i)   = line! / ( (line-i)! * i! ) 
 * 
 * Solution 2: (O(n^2) time and O(n^2) extra space)
 * 		If we take a closer at the triangle, we observe that every entry is sum of the two values above it. 
 *      So we can create a 2D array that stores previously generated values. To generate a value in a line, 
 *      we can use the previously stored values from array.
 *      
 * Solution 3: (O(n^2) time and O(1) extra space)
 *      This method is based on method 1. We know that ith entry in a line number line is Binomial Coefficient C(line, i) 
 *      and all lines start with value 1. The idea is to calculate C(line, i) using C(line, i-1). It can be calculated in O(1) 
 *      time using the following.
 *      
 *      C(line, i)   = line! / ( (line-i)! * i! )
 *      C(line, i-1) = line! / ( (line - i + 1)! * (i-1)! )
 *      We can derive following expression from above two expressions.
 *      C(line, i) = C(line, i-1) * (line - i + 1) / i
 *      So C(line, i) can be calculated from C(line, i-1) in O(1) time
 *
 * Solution 4: O(n^2 / 2) time and O(1) extra space
 * 		Use the same solution as solution 3, but only calculate half part of each line, as for each line, left part is same as right part.
 */
public class PascalTriangle {

	public static void print_solution1(int line) {
		if(line <= 0) {
			return;
		}
		
		for(int i=0; i<line; i++) {
			for(int j=0; j<i+1; j++) {
				System.out.print(Combination.selectCount(i, j, false) + " ");
			}
			System.out.println();
		}
	}
	
	public static void print_solution2(int line) {
		if(line <= 0) {
			return;
		}
		long[][] array = new long[line][];
		
		for(int i=0; i<line; i++) {
			array[i] = new long[i+1];
			for(int j=0; j<i+1; j++) {
				if(j==0 || j==i) {
					array[i][j] = 1;
				} else if(i>=1) {
					array[i][j] = array[i-1][j-1] + array[i-1][j];
				}
			}
		}
		
		for(int i=0; i<line; i++) {
			for(int j=0; j<i+1; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void print_solution3(int line) {
		for(int i=1; i<=line; i++) {
			long value = 1;
			for(int j=1; j<=i; j++) {
				System.out.print(value + " ");
				value = value * (i - j) / j;
			}
			System.out.println();
		}
	}

	public static void print_solution4(int line) {
		for (int i = 1; i <= line; i++) {
			System.out.println(getRow(i-1));
		}
	}

	public static List<Integer> getRow(int rowIndex) {
		rowIndex += 1;
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> leftPart = new ArrayList<Integer>();
		List<Integer> rightPart = new ArrayList<Integer>();

		if (rowIndex == 1) {
			result.add(1);
			return result;
		} else if (rowIndex == 2) {
			result.add(1);
			result.add(1);
			return result;
		}

		leftPart.add(1);
		rightPart.add(1);
		if (rowIndex % 2 == 0) {
			for(int j=1; j<rowIndex/2; j++) {
				long value = leftPart.get(leftPart.size() - 1) * (long)(rowIndex-j) / j;
				leftPart.add((int)value);
				rightPart.add(0, (int)value);
			}
		} else {
			for(int j=1; j<rowIndex/2; j++) {
				long value = leftPart.get(rightPart.size() - 1) * (long)(rowIndex-j) / j;
				leftPart.add((int)value);
				rightPart.add(0, (int)value);
			}

			long value = (leftPart.get(rowIndex/2 - 1) *  (long)(rowIndex-rowIndex/2)) / (rowIndex/2);
			leftPart.add((int)value);
		}

		result.addAll(leftPart);
		result.addAll(rightPart);
		return result;
	}
	
	public static void main(String[] args) {
//		print_solution1(6);
//		print_solution2(6);
		print_solution3(31);

		System.out.println();

		print_solution4(31);

		// System.out.println(getRow(30).toString());
	}

}
