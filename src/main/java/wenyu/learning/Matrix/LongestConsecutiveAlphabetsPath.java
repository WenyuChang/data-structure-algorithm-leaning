package wenyu.learning.Matrix;

import java.util.LinkedList;
import java.util.Queue;

import wenyu.learning.Queue.QueueDemoUtils;

/*
 * Given matrix like :
	a b e d
	b c f e
	a b d d
 * Find the longest path of consecutive alphabets given a starting alphabet. 
 * You can move in all 8 directions. for eg. a->b(right)->c(down)->d(diagnal down)
 * len = 4 , find max such length.
 */

public class LongestConsecutiveAlphabetsPath {

	public static int find(char[][] matrix, boolean print) {
		Queue<Character> queue = new LinkedList<Character>();
		Queue<Character> longestQueue = new LinkedList<Character>();
		longestQueue = find_core(matrix, 0, 0, queue, longestQueue);
		
		if(longestQueue!=null && longestQueue.size()>0) {
			if(print) {
				System.out.println("Longest count is " + longestQueue.size());
				QueueDemoUtils.printQueue(longestQueue);
			}
			return longestQueue.size();
		}
		return -1;
	}
	private static Queue<Character> find_core(char[][] matrix, int row, int col, Queue<Character> queue, Queue<Character> longestQueue) {
		Character ch = matrix[row][col];
		if(ch == null) {
			return null;
		}
		
		queue.offer(ch);
		if(queue.size() > longestQueue.size()) {
			longestQueue = new LinkedList<Character>(queue);
		}

		// Top
		if(row>0 && matrix[row-1][col]==ch+1) {
			longestQueue = find_core(matrix, row-1, col, queue, longestQueue);
		}
		
		// Bottom
		if(row<matrix.length-1 && matrix[row+1][col]==ch+1) {
			longestQueue = find_core(matrix, row+1, col, queue, longestQueue);
		}
		
		// Left
		if(col>0 && matrix[row][col-1]==ch+1) {
			longestQueue = find_core(matrix, row, col-1, queue, longestQueue);
		}
		
		// Right
		if(col<matrix[0].length-1 && matrix[row][col+1]==ch+1) {
			longestQueue = find_core(matrix, row, col+1, queue, longestQueue);
		}
		
		// Top-Left
		if(row>0 && col>0 && matrix[row-1][col-1]==ch+1) {
			longestQueue = find_core(matrix, row-1, col-1, queue, longestQueue);
		}
		
		// Top-Right
		if(row>0 && col<matrix[0].length-1 && matrix[row-1][col+1]==ch+1) {
			longestQueue = find_core(matrix, row-1, col+1, queue, longestQueue);
		}
		
		// Bottom-Left
		if(row<matrix.length-1 && col>0 && matrix[row+1][col-1]==ch+1) {
			find_core(matrix, row+1, col-1, queue, longestQueue);
		}

		// Bottom-Right
		if(row<matrix.length-1 && col<matrix[0].length-1 && matrix[row+1][col+1]==ch+1) {
			longestQueue = find_core(matrix, row+1, col+1, queue, longestQueue);
		}
		
		queue.poll();
		return longestQueue;
	}
	
	public static void main(String[] args) {
		char[][] matrix = {{'a','b','e','d'},{'b','c','f','e'},{'a','b','d','d'}};
		find(matrix, true);
	}
}
