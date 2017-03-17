package wenyu.learning.Maths;

import java.util.Random;

/*
 * Given an integer, find the next biggest integer whose digits are in increasing order.
 * Example:
 * Input: 118
 * Output: 123
 * 
 * Input: 127
 * Output: 234
 * 
 * Input: 987
 * Output: 1234
 * 
 * Logic:
 * 		1. Form the number which has increasing order digit with the same first digit as the origin one.
 * 		2. If the above number is smaller than origin one. Add one to each digit.
 * 		3. Pay attention to the digit bigger than 9!!!
 */
public class NextBiggerIncreasingOrderNumber {
	private static int formNum(int firstDigit, int digitCount) {
		int result = 0;
		int currDigit = firstDigit;
		int currDigitCount = digitCount;
		while(currDigitCount-- > 0) {
			if(currDigit>9) {
				currDigitCount = digitCount+1;
				result = 0;
				int digit = 1;
				while(currDigitCount-- > 0) {
					if(digit > 9) {
						return -1;
					}
					result = result*10 + digit;
					digit++;
				}
				return result;
			}
			result = result*10 + currDigit;
			currDigit++;
		}
		
		return result;
	}
	
	private static int[] getNumInfo(int num) {
		/*
		 * Function to get digit number and first digit
		 */
		int[] result = new int[2];
		int digitCount = 0;
		int firstDigit = 0;
		
		// Get first digit and digit count
		// Approach 1: divide and calcluate
		/*
				int tmp = num;
				while(tmp > 0) {
					tmp /= 10;
					digitCount++;
				}
				firstDigit = (int) (num/Math.pow(10, digitCount-1));
		 */

		// Approach 2: Using string
		String numStr = String.valueOf(num);
		digitCount = numStr.length();
		firstDigit = numStr.charAt(0)-'0';
		
		result[1] = firstDigit;
		result[0] = digitCount;
		return result;
	}
	
	public static void find(int num) {
		if(num<=0) {
			System.out.println("1");
			return;
		}
		
		int[] numInfo = getNumInfo(num);
		int digitCount = numInfo[0];
		int firstDigit = numInfo[1];
		if(digitCount > 9) {
			System.out.println("No such number.");
			return;
		}
		
		int result = formNum(firstDigit, digitCount);
		if(result < 0) {
			System.out.println("No such number.");
			return;
		}
		
		if(result < num) {
			numInfo = getNumInfo(result);
			result = formNum(numInfo[1]+1, numInfo[0]);
			if(result < 0) {
				System.out.println("No such number.");
				return;
			} else {
				System.out.println(result);
			}
		} else {
			System.out.println(result);
		}
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		int testCount = 10;
		while(testCount-- > 0) {
			int num = random.nextInt(9999);
			System.out.println(num);
			find(num);
			System.out.println("=================================");
		}
	}
}
