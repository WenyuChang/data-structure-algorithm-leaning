package wenyu.learning.Strings;

/**
 * Created by Wenyu on 12/12/16.
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     For example,
     Given:
     s1 = "aabcc",
     s2 = "dbbca",

     When s3 = "aadbbcbcac", return true.
     When s3 = "aadbbbaccc", return false.

 * Solution 1: Backtracing O(2n)
 * Solution 2: DP: f(i, j) = see code...
 */
public class InterleavingString {
    public static class Solution1 {
        private static boolean helper(String s1, int i1, String s2, int i2, String s3, int i3) {
            while (i1 < s1.length() && i2 < s2.length() && i3 < s3.length()) {
                if (s1.charAt(i1) != s2.charAt(i2) && s3.charAt(i3) == s1.charAt(i1)) {
                    i1++;
                    i3++;
                } else if (s1.charAt(i1) != s2.charAt(i2) && s3.charAt(i3) == s2.charAt(i2)) {
                    i2++;
                    i3++;
                } else if (s1.charAt(i1) == s2.charAt(i2) && s3.charAt(i3) == s2.charAt(i2)) {
                    boolean result = helper(s1, i1, s2, i2+1, s3, i3+1);
                    if(result) {
                        return true;
                    }
                    result = helper(s1, i1+1, s2, i2, s3, i3+1);
                    return result;
                } else {
                    return false;
                }
            }

            while (i1 < s1.length() && i3 < s3.length() && s1.charAt(i1) == s3.charAt(i3)) {
                i1++;
                i3++;
            }
            while (i2 < s2.length() && i3 < s3.length() && s2.charAt(i2) == s3.charAt(i3)) {
                i2++;
                i3++;
            }

            if (i3 == s3.length() && i1 == s1.length() && i2 == s2.length()) {
                return true;
            } else {
                return false;
            }
        }

        public static boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            return helper(s1, 0, s2, 0, s3, 0);
        }
    }

    public static class Solution2 {
        public static boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            if (s1.length() == 0 && s2.length() == 0) {
                return true;
            }

            boolean[][] aux = new boolean[s1.length() + 1][s2.length() + 1];
            for(int i=0; i<=s1.length(); i++) {
                for (int j=0; j<=s2.length(); j++) {
                    if (i == 0 && j==0) {
                        aux[i][j] = true;
                    }

                    else if (i == 0 && s2.charAt(j-1) == s3.charAt(j-1)) {
                        aux[i][j] = aux[i][j-1];
                    }

                    else if(j == 0 && s1.charAt(i-1) == s3.charAt(i-1)) {
                        aux[i][j] = aux[i-1][j];
                    }

                    else if (i != 0 && j!= 0 && s1.charAt(i-1) != s2.charAt(j-1) && s1.charAt(i-1) == s3.charAt(i+j-1)) {
                        aux[i][j] = aux[i-1][j];
                    }

                    else if (i != 0 && j!= 0 && s1.charAt(i-1) != s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(i+j-1)) {
                        aux[i][j] = aux[i][j-1];
                    }

                    else if (i != 0 && j!= 0 && s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(i+j-1)) {
                        aux[i][j] = aux[i][j-1] || aux[i-1][j];
                    }

                    else {
                        aux[i][j] = false;
                    }
                }
            }

            return aux[s1.length()][s2.length()];
        }
    }
}
