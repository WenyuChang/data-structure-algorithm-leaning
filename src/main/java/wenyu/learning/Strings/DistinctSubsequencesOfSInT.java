package wenyu.learning.Strings;

/*
 * Given a string S and a string T, count the number of distinct subsequences of S in T.
 * S = "rabbbit", T = "rabbit".  Return 3.
 * 
 * Explanation: 给定两个字符串S和T，求S有多少个不同的子串与T相同。
 * 
 * Solution:
 * Let W(i, j) stand for the number of subsequences of S(0, i) in T(0, j). 
 * If S.charAt(i) == T.charAt(j), W(i, j) = W(i-1,j) + W(i-1, j-1);
 * Otherwise, W(i, j) = W(i-1,j).
 */
public class DistinctSubsequencesOfSInT {

	public static int calculate(String S, String T, boolean print) {
		int[][] dp = new int[S.length()+1][T.length()+1];
		

		for(int j=0; j<dp[0].length; j++) {
			dp[0][j] = 0; // S(0,0) = empty;
		}
		for(int i=0; i<dp.length; i++) {
			dp[i][0] = 1; // T(0,0) = empty;
		}
		
		for(int i=1; i<=S.length(); i++) {
			for(int j=1; j<=T.length(); j++) {
				if(S.charAt(i-1) == T.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		if(print) System.out.println("Result: " + dp[S.length()][T.length()]);
		return dp[S.length()][T.length()];
	}
	
	public static void main(String[] args) {
		calculate("rabbbit", "rabbit", true);
	}

}
