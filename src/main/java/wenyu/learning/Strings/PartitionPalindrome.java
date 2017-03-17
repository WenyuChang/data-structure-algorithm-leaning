package wenyu.learning.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 1: Created by Wenyu on 12/19/16.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
     For example, given s = "aab",
     Return

     [
     ["aa","b"],
     ["a","a","b"]
     ]
 *
 *
 * Problem2:
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PartitionPalindrome {
    public class Problem1 {
        /*
         * Solution:
         * Loop string from first character.
         * Check if substring(0,i) is palindrome, if it is, then recursive with substring(i, end);
         */
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<List<String>>();

            if (s == null || s.length() == 0) {
                return result;
            }

            List<String> partition = new ArrayList<String>();//track each possible partition
            addPalindrome(s, 0, partition, result);

            return result;
        }

        private void addPalindrome(String s, int start, List<String> partition, List<List<String>> result) {
            if (start == s.length()) {
                List<String> temp = new ArrayList<String>(partition);
                result.add(temp);
                return;
            }

            for (int i = start + 1; i <= s.length(); i++) {
                String str = s.substring(start, i);
                if (isPalindrome(str)) {
                    partition.add(str);
                    addPalindrome(s, i, partition, result);
                    partition.remove(partition.size() - 1);
                }
            }
        }

        private boolean isPalindrome(String str) {
            int left = 0;
            int right = str.length() - 1;

            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }

                left++;
                right--;
            }

            return true;
        }
    }

    public class Problem2 {
        /*
         * int[] cuts = new int[len + 1]; //cuts数组，cuts[i] 表示 以 i 开头到len结尾的子串要达到题意需要的最少切割数
         * 这样子最终 cuts[0]就是我们要的结果
         * 初始化 cuts[i] = len - i, 因为最坏情况以 i 开头到len结尾的子串要切割数就是每个字符都切一次
         *
         * int[][] matrix = new  int[len][len]; 它所表示的意思：如matrix[i][j] = true, 表示子串 sub(i, j) 是满足回文字符串条件的
         * 那么判断matrix[i][j] 是否满足回文字符串的条件是：
         *   1. matrix[i+1][j-1] == true (表示sub(i+1,j-1)是满足回文字符串) && str[i] == str[j]
         *   2. j - i < 2 && str[i] == str[j] （即如果j - i == 1时，为两个字符相等，如果j - i == 0时，为同一个字符）
         * 这两种情况，我们都将matrix[i][j]设置成true
         *
         * cuts[i] = min{cuts[i], cuts[j+1] + 1};状态转移方程式
         * 这样最后cuts[0] - 1便为 字符串str的最小的切割数！！！！
         */
        public int minCut(String s) {
            int min = 0;
            int len = s.length();
            boolean[][] matrix = new boolean[len][len];
            int cuts[] = new int[len+1];

            if (s == null || s.length() == 0)
                return min;
            //初始化cuts里面的值为最坏情况的值
            for (int i=0; i<len; ++i){
                cuts[i] = len - i;
            }
            //dp过程
            for (int i=len-1; i>=0; --i){
                for (int j=i; j<len; ++j){
                    if ((s.charAt(i) == s.charAt(j) && (j-i<2))
                            || (s.charAt(i) == s.charAt(j) && matrix[i+1][j-1]))
                    {
                        matrix[i][j] = true;
                        cuts[i] = Math.min(cuts[i], cuts[j+1]+1);
                    }
                }
            }
            min = cuts[0];
            return min-1;
        }
    }

    public static void main(String[] args) {

    }
}
