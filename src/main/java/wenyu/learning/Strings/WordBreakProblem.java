package wenyu.learning.Strings;

import java.util.HashSet;

/*
 * Given an input string and a dictionary of words, find out if the input string can be segmented 
 * into a space-separated sequence of dictionary words. See following examples for more details.
 * This is a famous Google interview question, also being asked by many other companies now a days.
 * 
 * Consider the following dictionary 
 * { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
 * Input:  ilike
 * Output: Yes 
 * The string can be segmented as "i like".
 * 
 * Input:  ilikesamsung
 * Output: Yes
 * The string can be segmented as "i like samsung" or "i like sam sung".
 * 
 * Logic: DP
 */
public class WordBreakProblem {
	private static HashSet<String> dictionary = new HashSet<String>();
	private static boolean inDictionary(String str) {
		return dictionary.contains(str);
	}
	
	public static boolean breakString(String str) {
		boolean[] dp = new boolean[str.length()];
		
		for(int i=0; i<str.length(); i++) {
			if(!dp[i] && dictionary.contains(str.substring(0, i+1))) {
				dp[i] = true;
			}
			if(dp[i]) {
				if(i == str.length()-1) {
					return true;
				}
				for(int j=i+1; j<str.length(); j++) {
					if(!dp[j] && dictionary.contains(str.subSequence(i+1, j+1))) {
						dp[j] = true;
					}
					if(dp[j] && j==str.length()-1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
		dictionary.add("i");
		dictionary.add("like");
		dictionary.add("sam");
		dictionary.add("sumg");
		String str = "iiiiibiii";
		System.out.println("Result:" + breakString(str));
	}

}
