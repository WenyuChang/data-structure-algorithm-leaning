package wenyu.learning.Strings;

import java.util.HashMap;

/**
 * Created by Wenyu on 11/28/16.
 *
 * Problem: Given a string, find the longest substring that contains only two unique characters.
 *
 * For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character
 * is "bcbbbbcccb".
 */
public class LongestSubstrContainsAtMostKDistinctChar {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = -1;
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i=0; i<s.length(); i++) {
            if (!map.containsKey(s.charAt(i)) && map.size() >= 2) {
                int min = Integer.MAX_VALUE;
                char minChar = '\0';
                for (Character ch : map.keySet()) {
                    if (map.get(ch) < min) {
                        minChar = ch;
                        min = map.get(ch);
                    }
                }
                start = min + 1;
                map.remove(minChar);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i-start+1);
        }
        return max;
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = -1;
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i=0; i<s.length(); i++) {
            if (!map.containsKey(s.charAt(i)) && map.size() >= k) {
                int min = Integer.MAX_VALUE;
                char minChar = '\0';
                for (Character ch : map.keySet()) {
                    if (map.get(ch) < min) {
                        minChar = ch;
                        min = map.get(ch);
                    }
                }
                start = min + 1;
                map.remove(minChar);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i-start+1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abaaaaacbaaaffffffffffffsssdddcccbbbcccbdddadacb"));
        System.out.println(lengthOfLongestSubstringKDistinct("abaaaaacbaaaffffffffffffsssdddcccbbbcccbdddadacb", 2));
    }
}
