package wenyu.learning.Strings;

import java.util.ArrayList;

/*
 *  Given a string you need to print all possible strings that can be made by placing spaces 
 *  (zero or one) in between them. For example : ABC ->A BC,AB C,ABC,A B C
 *  
 *  Logic: Almost same as the logic of combination.
 *  Solution1: Using recursion to insert space.
 *  Solution2: Using bitwise solution of combination.
 */
public class PlaceSpaceIntoStr {

	public static ArrayList<String> solution1(String str, boolean print) {
		ArrayList<String> ret = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		solution1_core(str, 0, ret, builder);
		if(print) {
			for(String retStr : ret) {
				System.out.println(retStr);
			}
		}
		return ret;
	}
	private static void solution1_core(String str, int idx, ArrayList<String> ret, StringBuilder builder) {
		builder.append(str.charAt(idx));
		if(idx == str.length()-1) {
			ret.add(builder.toString());
			builder.deleteCharAt(builder.length()-1);
			return;
		}
		
		// Case of place blanket space
		{
			builder.append(" ");
			solution1_core(str, idx+1, ret, builder);
			builder.deleteCharAt(builder.length()-1);
		}
		
		// Case of not placing blanket space {
		{
			solution1_core(str, idx+1, ret, builder);
		}

		builder.deleteCharAt(builder.length()-1);
	}
	
	public static ArrayList<String> solution2(String str, boolean print) {
		if (str.length()-1 > 32) {
			return null;
		}
		
		ArrayList<String> ret = new ArrayList<String>();
		double max = Math.pow(2, str.length()-1);
		for (int i=0; i<max; i++) {
			StringBuilder builder = new StringBuilder();
			int idx = 0;
			builder.append(str.charAt(idx++));
			String binary = Integer.toBinaryString(i);
			while(binary.length()<str.length()-1) {
				binary = "0" + binary;
			}
			for (int j=0; j<binary.length(); j++) {
				if (binary.charAt(j) == '1') {
					builder.append(" ");
				}
				builder.append(str.charAt(idx++));
			}
			ret.add(builder.toString());
		}
		
		if(print) {
			for(String retStr : ret) {
				System.out.println(retStr);
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		//solution1("abc", true);
		
		solution2("abcdefghi", true);
	}
}
