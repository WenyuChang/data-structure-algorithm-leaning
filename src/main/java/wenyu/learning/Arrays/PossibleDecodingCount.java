package wenyu.learning.Arrays;

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
 * 1) f(n) = f(n+1)+0        	if digits[n]|digits[n+1] >26 
 *      or = f(n+1)+f(n+2)		if digits[n]|digits[n+1] <=26
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
	
	
	public static int count_solution2(int[] digits, boolean print) {
		if(digits==null || digits.length==0) {
			if(print) System.out.println("Result: 0");
			return 0;
		}
		
		int fn1 = 1;
		int fn2 = 1;
		
		for(int i=digits.length-2; i>=0; i--) {
			if(digits[i]<=0) {
				if(print) System.out.println("Result: -1");
				return -1;
			}
			
			int currFn = fn1;
			if(i+1<digits.length && digits[i]*10+digits[i+1]<=26) {
				currFn += fn2;
			}
			
			fn2 = fn1;
			fn1 = currFn;
		}
		
		if(print) System.out.println("Result: " + fn1);
		return fn1;
	}
	
	public static void main(String[] args) {
		int[] digits = {1,2,3,4,5,2,6,7,8,9,1,2,3,1,2,1,2,1,7,2};
		count_solution1(digits, true);
		count_solution2(digits, true);
	}

}
