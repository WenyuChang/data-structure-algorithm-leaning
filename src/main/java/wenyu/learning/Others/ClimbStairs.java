package wenyu.learning.Others;

/**
 * Created by Wenyu on 12/1/16.
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbStairs {

    public static int climbStairs(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int n1 = 2; // f(n-1)
        int n2 = 1; // f(n-2)
        for (int i=3; i<=n; i++) {
            int tmp = n2;
            n2 = n1;
            n1 = tmp + n1;
        }

        return n1;
    }
}
