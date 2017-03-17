package wenyu.learning.Others;

/**
 * Created by Wenyu on 12/2/16.
 *
 * Problem 1: You are playing the following Nim Game with your friend: There is a heap of stones on the table,
 * each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner.
 * You will take the first turn to remove the stones.
 */
public class CoinPickGame {

    public static class Problem1 {
        public boolean canWinNim(int n) {
            // f(n) = !f(n-1) or !f(n-2) or !f(n-3)
            return n%4 != 0;
        }
    }
}
