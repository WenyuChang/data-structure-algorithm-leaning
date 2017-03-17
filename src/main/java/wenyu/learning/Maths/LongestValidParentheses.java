package wenyu.learning.Maths;

import java.util.HashMap;

/**
 * Created by Wenyu on 11/28/16.
 *
 * Problem: Given a string containing just the characters '(' and ')', find the length of the longest valid
 * (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *
 * Solution:
 * Define f(i) to be the longest length of the valid parentheses ended at s[i],
 * and only the following two cases, dp[i] could be calculated from a recursive
 * definition, otherwise it is zero.
 * 1) if s[i] is ‘)’ and s[i-1] is equal to ‘(‘, then dp[i] = dp[i-2] + 2
 * 2) if s[i] is ‘)’ and s[i-1] is equal to ‘)’ and s[i - dp[i-1] – 1] is equal to ‘(‘, then dp[i] =  dp[i-1] + 2 + dp[i - dp[i-1] – 2]
 */
public class LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        int n = s.length();
        int currMax = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < n; ++i) {
            if (')' == s.charAt(i)) {
                if ('(' == s.charAt(i-1)) {
                    dp[i] = 2;
                    if (i >= 2) dp[i] += dp[i-2];
                } else {
                    int idx = i - dp[i-1] - 1;
                    if (idx >= 0 && '(' == s.charAt(idx)) {
                        dp[i] = dp[i-1] + 2;
                        if (idx > 0)
                            dp[i] += dp[idx -1];
                    }
                }
            }
            currMax = Math.max(currMax, dp[i]);
        }
        return currMax;
    }

    public static void main(String[] args) {
        int result = longestValidParentheses("(()");
        System.out.println(result);
    }

}
