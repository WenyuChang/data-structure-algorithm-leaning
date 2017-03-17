package wenyu.learning.Maths.Prime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem 1: Find all the prime factors for a particular number
 * 
 * Problem 2: Find the number of factors for a particular number.
 * Logic: 72 = 2^3 * 3^2 == > factor: (3+1) * (2+1) = 12 factors
 * 
 * 
 */
public class FactorOfNumberProblems {

	public static List<Long> primeFactors_solution1(long number) {
		long n = number;
		List<Long> factors = new ArrayList<Long>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add((long)i);
				n /= i;
			}
		}
		return factors;
	}
	
	public static List<Long> primeFactors_solution2(long number) {
		long n = number;
		List<Long> factors = new ArrayList<Long>();
		for (int i = 2; i <= n / i; i++) {
			// This uses the fact that if we know that a loop i n
			// has no divisors less then or equal then i. 
			// it can also not have a divisor which is larger then n/i.
			while (n % i == 0) {
				factors.add((long)i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}
		return factors;
	}

	public static int factorCount(long number, boolean print) {
		long n = number;
		Map<Long, Integer> factors = new HashMap<Long, Integer>();
		for (int i = 2; i <= n / i; i++) {
			// This uses the fact that if we now that a loop i n 
			// has no divisors less then or equal then i. 
			// it can also not have a divisor which is larger then n/i.
			while (n % i == 0) {
				if(factors.containsKey((long)i)) {
					factors.put((long)i, factors.get((long)i)+1);
				} else {
					factors.put((long)i, 1);
				}
				n /= i;
			}
		}
		if (n > 1) {
			if(factors.containsKey((long)n)) {
				factors.put((long)n, factors.get((long)n)+1);
			} else {
				factors.put((long)n, 1);
			}
		}
		
		int count = 1;
		for(long factor : factors.keySet()) {
			count *= (factors.get(factor)+1);
		}
		
		if(print) System.out.println("Result is " + count);
		return count;
	}
	
	public static void main(String[] args) {
		factorCount(72, true);
	}

}
