package wenyu.learning.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wenyu on 12/9/16.
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class RestoreIPAddresses {
    private void helper(String s, int curr, List<String> currSeq, List<String> result) {
        if (currSeq.size() == 4 && curr == s.length()) {
            StringBuilder ip = new StringBuilder();
            for (String subIp : currSeq) {
                if (subIp.length() > 1 && subIp.charAt(0) == '0') {
                    return;
                }
                ip.append(subIp + ".");
            }
            result.add(ip.substring(0, ip.length()-1));
            return;
        }

        // Only 1 digit for this sub-ip case
        if (s.length() - curr - 1 <= 3*(3-currSeq.size()) && s.length() - curr - 1 >= 3-currSeq.size()) {
            currSeq.add(String.valueOf(s.charAt(curr)));
            helper(s, curr+1, currSeq, result);
            currSeq.remove(currSeq.size()-1);
        }

        // Only 2 digit for this sub-ip case
        if (s.length() - curr - 2 <= 3*(3-currSeq.size()) && s.length() - curr - 2 >= 3-currSeq.size()) {
            int num = s.charAt(curr) - '0';
            num = num*10 + s.charAt(curr+1) - '0';
            if (num > 0 && s.charAt(curr) != '0') {
                currSeq.add(String.valueOf(num));
                helper(s, curr + 2, currSeq, result);
                currSeq.remove(currSeq.size() - 1);
            }
        }

        // Only 3 digit for this sub-ip case
        if (s.length() - curr - 3 <= 3*(3-currSeq.size()) && s.length() - curr - 3 >= 3-currSeq.size()) {
            int num = s.charAt(curr) - '0';
            num = num*10 + s.charAt(curr+1) - '0';
            num = num*10 + s.charAt(curr+2) - '0';
            if (num <= 255 && num > 0 && s.charAt(curr) != '0') {
                currSeq.add(String.valueOf(num));
                helper(s, curr+3, currSeq, result);
                currSeq.remove(currSeq.size()-1);
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        helper(s, 0, new ArrayList<String>(), result);
        return result;
    }
}
