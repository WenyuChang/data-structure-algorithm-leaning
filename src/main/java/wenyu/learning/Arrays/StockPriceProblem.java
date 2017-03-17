package wenyu.learning.Arrays;

/*
 * Stock prices are stored in an array in the order of date. How do you get the
 * most profit from a sequence of stock prices?
 * For example, the most profit to be gained from the sequence of ordered stock
 * prices {9, 11, 5, 7, 16, 1, 4, 2} is 11, bought when the price was 5 and sold
 * when the price was 16.
 * 
 * Problem 1: Only allow to buy/sell once (Same as max min from unsorted array.)
 * Problem 2: Allow buy/sell multiply times.
 * Problem 3: Allow but/sell only two times. (Not implemented. http://www.geeksforgeeks.org/stock-buy-sell/)
 */

public class StockPriceProblem {
	public static void problem1(int[] prices) {
		/*
		 * Problem 1
		 */
		if (prices == null || prices.length < 2) {
			System.out.println("Max difference is " + 0 );
			return ;
		} else if (prices.length == 2) {
			System.out.println("Max difference is " + (prices[0] <= prices[1] ? prices[1]-prices[0] : 0));
		}

		int min = prices[0];
		int maxDiff = prices[1] - prices[0];
		for (int i=2; i<prices.length; i++) {
			if (prices[i-1] < min) {
				min = prices[i-1];
			}
			maxDiff = Math.max(maxDiff, prices[i] - min);
		}

		System.out.println("Max difference is " + (maxDiff > 0 ? maxDiff : 0));
	}

	public int problem2(int[] prices) {
		if (prices == null || prices.length < 2) {
			System.out.println("Max difference is " + 0 );
			return 0;
		} else if (prices.length == 2) {
			System.out.println("Max difference is " + (prices[0] <= prices[1] ? prices[1]-prices[0] : 0));
			return prices[0] <= prices[1] ? prices[1]-prices[0] : 0;
		}

		int buy = prices[1];
		int currMax = prices[0] <= prices[1] ? prices[1]-prices[0] : 0;
		for (int i=2; i<prices.length; i++) {
			if (prices[i] >= buy) {
				currMax += (prices[i] - buy);
			}
			buy = prices[i];
		}

		System.out.println("Max difference is " + currMax);
		return currMax;
	}

	public int problem3(int[] prices) {
		/*
			Logic:
			Comparing to I and II, III limits the number of transactions to 2. This can be solve by "devide and conquer".
			We use left[i] to track the maximum profit for transactions before i, and use right[i] to track the
			maximum profit for transactions after i. You can use the following example to understand the Java solution:
				Prices: 1 4 5 7 6 3 2 9
				left = [0, 3, 4, 6, 6, 6, 6, 8]
				right= [8, 7, 7, 7, 7, 7, 7, 0]
				The maximum profit = 13
		 */

		if (prices == null || prices.length < 2) {
			return 0;
		}

		int[] left = new int[prices.length];
		int[] right = new int[prices.length];

		// DP from left to right
		left[0] = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			left[i] = Math.max(left[i - 1], prices[i] - min);
		}

		// DP from right to left
		right[prices.length - 1] = 0;
		int max = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			right[i] = Math.max(right[i + 1], max - prices[i]);
		}

		int profit = 0;
		for (int i = 0; i < prices.length; i++) {
			profit = Math.max(profit, left[i] + right[i]);
		}
		return profit;
	}
	
	public static void main(String[] args) {
		int[] numbers = new int[] { 9, 11, 5, 7, 16, 1, 4, 2 };
		problem1(numbers);

	}
}
