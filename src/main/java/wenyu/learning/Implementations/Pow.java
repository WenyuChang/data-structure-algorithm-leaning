package wenyu.learning.Implementations;

/**
 * Created by Wenyu on 11/30/16.
 *
 * Problems: Implement pow(x, n).
 *
 * Attention: if n is very big, normal way (loop to multiplicate x) will timeout.
 * Need the following approach to calculate. (Calculate pow of half of n)
 */
public class Pow {
    private double pow(double x, int n) {

        if (n == 0) {
            return 1;
        }

        double currRes = pow(x, n/2);
        if (n % 2 == 0) {
            return currRes * currRes;
        } else {
            return currRes * currRes * x;
        }
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1f;
        } else if (n == 1) {
            return x;
        }

        boolean neg = false;
        if (n < 0) {
            if (x == 0) {
                return Double.MAX_VALUE;
            }
            n *= -1;
            neg = true;
        }

        double ret = pow(x, n);

        if (neg) {
            ret = 1/ret;
        }
        return ret;
    }
}
