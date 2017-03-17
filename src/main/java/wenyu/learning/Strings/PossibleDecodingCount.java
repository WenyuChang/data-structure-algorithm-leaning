package wenyu.learning.Strings;

import java.util.Arrays;

/*
 * Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the number of possible decodings of the given digit sequence.
 * Examples:
 * 
 * Input:  digits[] = "121"
 * Output: 3 // The possible decodings are "ABA", "AU", "LA"
 * 
 * Input: digits[] = "1234"
 * Output: 3 // The possible decodings are "ABCD", "LCD", "AWD"
 * 
 * Solution1: O(n)/O(n)
 * 1) If the last digit is non-zero, recur for remaining (n-1) digits and add the result to total count.
 * 2) If the last two digits form a valid character (or smaller than 27), recur for remaining (n-2) digits 
 * 	  and add the result to total count.
 * 
 * Solution2: O(n)/O(1)
 * 1) f(n) = f(n-1)+0        	if digits[n]|digits[n+1] >26
 *      or = f(n-1)+f(n-2)		if digits[n]|digits[n-1] <=26
 * 2) Be attention on a lot of special case
 */
public class PossibleDecodingCount {

	public static int count_solution1(int[] digits, boolean print) {
		if(digits==null || digits.length==0) {
			if(print) System.out.println("Result: 0");
			return 0;
		}

		int count = count_core(digits, 0);
		if(print) System.out.println("Result: " + count);
		return count;
	}
	private static int count_core(int[] digits, int idx) {
		if(idx==digits.length) {
			return 1;
		}
		if(digits[idx]<=0) {
			return -1;
		}
		
		int count1 = count_core(digits, idx+1);
		if(count1==-1) {
			return -1;
		}
		int count2 = 0;
		if(idx+1<=digits.length-1) {
			int num = digits[idx]*10+digits[idx+1];
			if(num<=26) {
				count2 = count_core(digits, idx+2);
				if(count2==-1) {
					return -1;
				}
			}
		}
		
		return count1+count2;
	}


	public static int count_solution2(String s) {
		// Special case:
		// 01/230: zero is at the begining or end

		if (s == null || s.length() == 0 || s.charAt(0) == '0') {
			// for case null/""/"0"
			return 0;
		}

		if (s.length() == 1) {
			// For case "1"
			return 1;
		}

		int f2 = 1; // f(n-2)
		int f1 = 0; // f(n-1)

		int ch1 = s.charAt(0) - '0';
		int ch2 = s.charAt(1) - '0';
		if (ch2 != 0 && ch1 * 10 + ch2 <= 26) {
			// Case: 11/23
			f1 = 2;
		} else if (ch2 == 0 && ch1 * 10 + ch2 <= 26) {
			// Case: 10/20
			f1 = 1;
		} else if (ch2 == 0 && ch1 * 10 + ch2 > 26) {
			// Case: 30/50
			return 0;
		} else {
			// Case: 28
			f1 = 1;
		}

		for (int i=2; i<s.length(); i++) {
			int currF = -1;
			ch1 = s.charAt(i-1) - '0';
			ch2 = s.charAt(i) - '0';
			if (ch1 == 0 && ch2 == 0) {
				return 0;
			} else if (ch1 == 0 && ch2 != 0) {
				currF = f1;
			} else if (ch1 != 0 && ch2 == 0 && ch1 * 10 + ch2 <= 26) {
				currF = f2;
			} else if (ch1 != 0 && ch2 == 0 && ch1 * 10 + ch2 > 26) {
				return 0;
			} else if (ch1 * 10 + ch2 <= 26) {
				currF = f1 + f2;
			} else {
				currF = f1;
			}

			f2 = f1;
			f1 = currF;
		}

		return f1;
	}
	
	public static void main(String[] args) {
		int[] digits = {1,2,3,4,5,2,6,7,8,9,1,2,3,1,2,1,2,1,7,2};
		count_solution1(digits, true);
		count_solution2("12345267891231212172");
	}

}
