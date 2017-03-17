package wenyu.learning.Maths.PermutationAndCombination;

/*
 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. X is the 
 * summation of values on each face when all the dice are thrown.
 * 
 * Solution1:
 * The Naive approach is to find all the possible combinations of values from n dice and keep on counting the results that sum to X.
 * 
 * Solution2:
 * This problem can be efficiently solved using Dynamic Programming (DP).
 * Let the function to find X from n dice is: Sum(m, n, X)
 * The function can be represented as:
 * Sum(m, n, X) = Finding Sum (X - 1) from (n - 1) dice plus 1 from nth dice
 *              + Finding Sum (X - 2) from (n - 1) dice plus 2 from nth dice
 *              + Finding Sum (X - 3) from (n - 1) dice plus 3 from nth dice
 *                 ...................................................
 *                 ...................................................
 *                 ...................................................
 *             + Finding Sum (X - m) from (n - 1) dice plus m from nth dice
 *
 * So we can recursively write Sum(m, n, x) as following 
 * Sum(n, m, X) = Sum(n - 1, m, X - 1) + 
 *                Sum(n - 1, m, X - 2) +
 *                .................... + 
 *                Sum(n - 1, m, X - m)
 */
public class DiceThrowProblem {

	public static int solution1(int n, int m, int X) {
		if (n==0 && X==0) {
			return 1;
		} else if (n==0 && X<0) {
			return 0;
		}

		int sum = 0;
		for (int i=1; i<=m; i++) {
			if (m * (n-1) + i < X) {
				continue;
			}

			sum += solution1(n-1, m, X-i);
		}


		return sum;
	}

	public static int dp_solution(int n, int m, int X) {		
		if((n<=0 || m<=0) && X>0) {
			return 0;
		} else if((n<=0 || m<=0) && X==0) {
			return 1;
		}
		
		int currSum = 0;
		for(int i=1; i<=m&&X>=i; i++) {
			int lastSum = dp_solution(n-1, m, X-i);
			if(lastSum<0) {
				return lastSum;
			}
			currSum += lastSum;
		}
		return currSum;
	}
	
	public static void main(String[] args) {
		System.out.println(dp_solution(10,8,50));
		System.out.println(solution1(10, 8, 50));
	}

}
