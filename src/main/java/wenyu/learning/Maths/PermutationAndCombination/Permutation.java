package wenyu.learning.Maths.PermutationAndCombination;

import java.util.*;

import wenyu.learning.Maths.NextBigProblems;

/*
 * P(n,m) ==> n!/(n-m)! ==> n*(n-1)*...*(n-m+1)
 * 
 * Method:
 * permutationCount: get permutation count
 * permutationRecursion: get permutation with recursion
 * permutationNoRecursion: get permutation without recursion
 * allPermutationNoRecursion: get all permutation without recursion
 */

public class Permutation {
	private static int pCountRec = 0;
	public static long permutationCount(int N, int M, boolean print) {
		if(N < M) {
			return -1;
		}
		
		long result = 1;
		int n = N;
		while(n > (N-M)) {
			result *= n--;
		}
		if(print) System.out.println("P(" + N + ", " + M + ") = " + result);
		return result;
	}
	
	public static void permutationRecursion(char[] str, int len) {
		if(str == null)
			return;
		permutationRecCore(str, 0, len);
	}
	
	public static void permutationNoRecursion(char[] str, int len) {
		if(str == null)
			return;
		// ArrayList<String> combinations = Combination.selectRecursion(str, 0, len, new Stack<Character>(), null, false);
		ArrayList<String> combinations = Combination.selectNoRecursion(str, len, false);
		pCountRec = 1;
		for(String combination : combinations) {
			allPermutationNoRecursion(combination.toCharArray());
		}
	}
	
	public static void allPermutationNoRecursion(char[] str) {
		/*
		 * Logic:
		 * 1. change original string to ascending order.
		 * 2. find next bigger string using findNext function (Ref: RearrangeNumberFindNextBig)
		 * 3. Print next
		 * 4. Loop first two steps
		 */
		
		// Step 1: form ascending order intermediate array
		Comparator<Character> comp = new Comparator<Character>() {
			public int compare(Character o1, Character o2) {
				if(o1>o2) return 1;
				else if(o1<02) return -1;
				else return 0;
			}
		};
		Character[] strChar = new Character[str.length];
		for(int i=0; i<str.length; i++) {
			strChar[i] = str[i];
		}
		Arrays.sort(strChar, comp);
		
		// Step 2: call find next function to find next bigger array
		int result = 0;
		while(result >= 0) {
			System.out.println(pCountRec++ + ": " + Arrays.toString(strChar));
			result = NextBigProblems.find(strChar);
		}
	}
	
	private static void permutationRecCore(char[] str, int pBegin, int len) {
		if(pBegin == len) {
			pCountRec++;
			System.out.print(pCountRec+": ");
			for(int i=0;i<len;i++) {
				System.out.print(str[i]);
			}
			System.out.println();
		} else {
			for(int pCh = pBegin; pCh<str.length; ++pCh) {
				char temp = str[pCh];
				str[pCh] = str[pBegin];
				str[pBegin] = temp;
				permutationRecCore(str, pBegin + 1, len);
				temp = str[pCh];
				str[pCh] = str[pBegin];
				str[pBegin] = temp;
			}
		}
	}

	private static void permutationRecCoreWithoutDuplicate(char[] str, int pBegin, int len) {
		/*
		[1,1,2] have the following unique permutations:
			[
			  [1,1,2],
			  [1,2,1],
			  [2,1,1]
			]
		 */
		if(pBegin == len) {
			pCountRec++;
			System.out.print(pCountRec+": ");
			for(int i=0;i<len;i++) {
				System.out.print(str[i]);
			}
			System.out.println();
		} else {
			Set<Character> set = new HashSet<Character>();
			for(int pCh = pBegin; pCh<str.length; ++pCh) {
				if (set.contains(str[pCh])) {
					continue;
				} else {
					set.add(str[pCh]);
				}

				char temp = str[pCh];
				str[pCh] = str[pBegin];
				str[pBegin] = temp;
				permutationRecCore(str, pBegin + 1, len);
				temp = str[pCh];
				str[pCh] = str[pBegin];
				str[pBegin] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		String str = "abcde";
		
		// Permutation using recursion
		// permutationCount(str.length(), 2);
		// permutationRecursion(str.toCharArray(), 3);
		allPermutationNoRecursion(str.toCharArray());
		// permutationNoRecursion(str.toCharArray(), 2);
	}

}
