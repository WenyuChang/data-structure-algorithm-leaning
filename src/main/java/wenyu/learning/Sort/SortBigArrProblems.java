package wenyu.learning.Sort;

/*
 * Problem 1: Given a set of 1 Trillion integers on hard disk, find the smallest 1 million of them. 
 * You can fit at most 1 million integers in memory at a time.
 */
public class SortBigArrProblems {

	public static void problem1(int[] arr) {
		/*
		 * Logic of problem 1:
		 * You can do this efficiently in O(n log m) by using a heap. ( n = all the numbers, 
		 * m = the size of the set of numbers you want to find ).
		 * 
		 * Go through the trillion numbers one at a time. For each new number do one of the following.
		 * 1. If the heap has < 1 million nodes insert the new number into the heap.
		 * 2. If the heap has exactly 1 million nodes and the top node is > than the new number, then pop 
		 *    the top node from the heap, and insert a node with the new number.
		 * 3. If neither 1 or 2 are true then toss the number.
		 * 
		 * After you go through all the trillion entries then the resulting heap will have the 1 million smallest numbers.
		 * Inserting and deleting from the heap is O(log m). The single pass through the heap is n. So, the algorithm is n*log (m)
		 */
	}
}
