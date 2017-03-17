package wenyu.learning.Maths;

/*
 * The reflected binary code, also known as Gray code after Frank Gray, 
 * is a binary numeral system where two successive values differ in only 
 * one bit (binary digit). The reflected binary code was originally designed 
 * to prevent spurious output from electromechanical switches.
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Example: 001, 011, 010, ...
 * Gray code generation:
 * 		nth = (n>>1)^n
 * 
 * Problem 1: Given two hexadecimal numbers find if they can be consecutive in gray code
 * 			  Logic: xor them, and verify if the result is the power of 2
 *
 * Problem 2: Given a non-negative integer n representing the total number of bits in the code, print the
 * sequence of gray code. A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
	00 - 0
	01 - 1
	11 - 3
	10 - 2
 */
public class VerifyGrayCode {

	public static boolean verify(long num1, long num2) {
		long n = num1 ^ num2;
		boolean result = (n!=0) && (((n-1)&n)==0);
		return result;
	}
	
//	public static int binaryToGray(int num)
//	{
//		return (num >> 1) ^ num;
//	}
	 
//	public static int grayToBinary(int num)
//	{
//	    int mask;
//	    for (mask = num >> 1; mask != 0; mask = mask >> 1) {
//	        num = num ^ mask;
//	    }
//	    return num;
//	}
	
	public static void main(String[] args) {
		long num1 = 1l;
		long num2 = 10l;
		
		if(verify(num1, num2)) {
			System.out.println(num1 + " and " + num2 + " can be consecutive in gray code.");
		} else {
			System.out.println(num1 + " and " + num2 + " cannot be consecutive in gray code.");
		}
	}
}
