package wenyu.learning.Others;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Wenyu on 12/1/16.
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 *
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }

        Stack<String> stack = new Stack<String>();
        String[] splitPath = path.trim().split("/");
        for (String curr : splitPath) {
            curr = curr.trim();
            if (curr.length() == 0) {
                continue;
            }

            if (curr.equals(".")) {
                continue;
            }

            if (curr.equals("..") && !stack.isEmpty()) {
                stack.pop();
                continue;
            }

            if (curr.equals("..") && stack.isEmpty()) {
                continue;
            }

            stack.push(curr);
        }

        Iterator<String> it = stack.iterator();
        Stack<String> reverse = new Stack<String>();
        while (it.hasNext()) {
            reverse.push(it.next());
        }

        StringBuilder builder = new StringBuilder("/");
        it = reverse.iterator();
        while (it.hasNext()) {
            builder.append(it.next() + "/");
        }

        if (builder.length() > 1) {
            return builder.substring(0, builder.length()-1);
        } else {
            return builder.toString();
        }
    }
}
