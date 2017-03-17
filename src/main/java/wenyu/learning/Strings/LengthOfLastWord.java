package wenyu.learning.Strings;

/**
 * Created by Wenyu on 11/30/16.
 *
 * Problem: Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length
 * of last word in the string. If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example
 * Given s = "Hello World"
 * return 5.
 *
 * Special case: " ", "a " --> 1
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s==null || s.length() == 0) {
            return 0;
        }

        boolean startCount = false;
        int last = s.length()-1;
        for (int i=s.length()-1; i>=0; i--) {
            if (!startCount && s.charAt(i) == ' ') {
                continue;
            } else if(!startCount && s.charAt(i) != ' ') {
                last = i;
                startCount = true;
                continue;
            }

            if (s.charAt(i) == ' ') {
                return last-i;
            }
        }

        return startCount ? last+1 : 0;
    }
}
