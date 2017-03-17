package wenyu.learning.Strings;

/**
 * Created by Wenyu on 12/2/16.
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
     Below is one possible representation of s1 = "great":
           great
          /    \
         gr    eat
        / \    /  \
       g   r  e   at
      / \
     a   t
     To scramble the string, we may choose any non-leaf node and swap its two children.
     For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
            rgeat
           /    \
         rg    eat
        / \    /  \
       r   g  e   at
      / \
     a   t
     We say that "rgeat" is a scrambled string of "great".
     Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
           rgtae
          /    \
         rg    tae
        / \    /  \
       r   g  ta  e
      / \
     t   a
     We say that "rgtae" is a scrambled string of "great".
     Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

 Solution 1: Solution: if s1 is a scrambled string of s2, then (a) they must contain the same chars, and (b) you must can
 find out some point i to break s1 into two parts s1_1 and s1_2, and some point j to break s2 into two parts s2_1 and s2_2
 such that one part of s1 is a scrambled string of one part of s2, and the rest part of s1 is also a scrambled string of
 another part of s2.

 Solution 2: f(i,j,l)=
                    (f(i,j,k) AND f(i+k,j+k,l-k))
                 OR (f(i,j+l-k,k) AND f(i+k,j,l-k))
            where f(i,j,l) is true iff substring starts at s1[i]
            and substring starts at s2[j] both with length l are scrambled,
 */
public class ScrambleString {
    public boolean solution1(String s1, String s2) {
        if(s1.equals(s2)) {
            return true;
        }
        if(s1.length() != s2.length()) {
            return false;
        }

        // Better to have
        // string s1Copy = s1, s2Copy = s2;
        // sort(s1Copy.begin(), s1Copy.end());
        // sort(s2Copy.begin(), s2Copy.end());
        // if(s1Copy!=s2Copy)
        //    return false;

        int n = s1.length();
        for(int i=1; i<n; ++i){
            //check (s1_1, s2_1) and (s1_2, s2_2)
            if(solution1(s1.substring(0, i), s2.substring(0, i)) && solution1(s1.substring(i), s2.substring(i))) {
                return true;
            }

            //check (s1_1, s2_2) and (s1_2, s2_1)
            if(solution1(s1.substring(0, i), s2.substring(n-i)) && solution1(s1.substring(i), s2.substring(0, n-i)))
                return true;
        }
        return false;
    }

    public boolean solution2(String s1, String s2) {
        if(s1.equals(s2)) {
            return true;
        }
        if(s1.length() != s2.length()) {
            return false;
        }

        // Better to have
        // string s1Copy = s1, s2Copy = s2;
        // sort(s1Copy.begin(), s1Copy.end());
        // sort(s2Copy.begin(), s2Copy.end());
        // if(s1Copy!=s2Copy)
        //    return false;

        int n = s1.length();
        boolean cache[][][] = new boolean[n][n][n+1];
        for(int i=0; i<n; ++i) {
            for(int j=0; j<n; ++j){
                cache[i][j][0] = true;
                cache[i][j][1] = (s1.charAt(i) == s2.charAt(j));
            }
        }

        for(int l=2; l<=n; ++l) {
            for(int i=0; i<=n-l; ++i) {
                for(int j=0; j<=n-l; ++j) {
                    cache[i][j][l] = false;
                    for(int k=1; k<=l; ++k){
                        if(cache[i][j][k] && cache[i+k][j+k][l-k]
                                || cache[i][j+l-k][k] && cache[i+k][j][l-k]){
                            cache[i][j][l] = true;
                            break;
                        }
                    }
                }
            }
        }
        return cache[0][0][n];
    }
}
