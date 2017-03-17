package wenyu.learning.Maths;

/*
 * Take a integer as a input and replace all the ‘0’ with ‘5’. Do not use any array for replacing the '0' to '5'
 * For example:
 * 102 - 152
 * 1020 - 1525
 */
public class ReplaceDigitInNumber {
	public static long replace(long number, boolean print) {
		long result = 0;
		int idx = 0;
		while(number > 0) {
			long mod = number%10;
			number /= 10;
			if(mod == 0) {
				result += 5*Math.pow(10, idx++);
			} else {
				result += mod*Math.pow(10, idx++);
			}
		}
		if(print) System.out.println("Result is " + result);
		return result;
	}
	
	public static void main(String[] args) {
		replace(1020, true);
	}

}
