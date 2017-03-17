package wenyu.learning.Strings;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Problem 1: Check two string are anagram
 * Anagram: strings contain the all the same characters
 * Solution 1:
 * 		1) Sort both strings
 * 		2) Compare the sorted strings
 * Solution 2:
 * 		1) Create count arrays of size 256 for both strings. 
 *         Initialize all values in count arrays as 0.
 * 		2) Iterate through every character of both strings and 
 *    	   increment the count of character in the corresponding count arrays.
 * 		3) Compare count arrays. If both count arrays are same, 
 * 	       then return true.
 * 
 * Problem 2: Given an array of words, print all anagrams together. 
 * For example, if the given array is {"cat", "dog", "tac", "god", "act"}, then output may be "cat tac act dog god".
 * Solution 1: Take two auxiliary arrays, index array and word array. Populate the word array with the given sequence of words. 
 * 			   Sort each individual word of the word array. Finally, sort the word array and keep track of the corresponding indices. 
 * 			   After sorting, all the anagrams cluster together. Use the index array to print the strings from the original array of 
 *             strings. Time Complexity: Let there be N words and each word has maximum M characters. The upper bound is O(NMLogM + MNLogN).
 *             For Example: 
 *             		Let us understand the steps with following input Sequence of Words: "cat", "dog", "tac", "god", "act"
 *             		1) Create two auxiliary arrays index[] and words[]. Copy all given words to words[] and store the original indexes in index[]
 *             		   index[]:  0   1   2   3   4
 *                     words[]: cat dog tac god act
 *                  2) Sort individual words in words[]. Index array doesn’t change.
 *                     index[]:   0    1    2    3    4
 *                     words[]:  act  dgo  act  dgo  act
 *                  3) Sort the words array. Compare individual words using strcmp() to sort
 *                     index:     0    2    4    1    3
 *                     words[]:  act  act  act  dgo  dgo
 *                  4) All anagrams come together. But words are changed in words array. To 
 *                     print the original words, take index from the index array and use it in
 *                     the original array. We get "cat tac act dog god".
 * Solution 2: Trie data structure can be used for a more efficient solution. Insert the sorted order of each word in the trie. 
 * 			   Since all the anagrams will end at the same leaf node. We can start a linked list at the leaf nodes where each 
 *             node represents the index of the original array of words. Finally, traverse the Trie. While traversing the Trie, 
 *             traverse each linked list one line at a time. Following are the detailed steps.
 *             1) Create an empty Trie
 *             2) One by one take all words of input sequence. Do following for each word
 *             		a) Copy the word to a buffer.
 *             		b) Sort the buffer
 *             		c) Insert the sorted buffer and index of this word to Trie. Each leaf node of Trie is head of a Index list. 
 *                     The Index list stores index of words in original sequence. If sorted buffe is already present, we insert 
 *                     index of this word to the index list.
 *             3) Traverse Trie. While traversing, if you reach a leaf node, traverse the index list. And print all words using 
 *                the index obtained from Index list.
 */

public class AnagramProblems {

	public static boolean ifAnagram_solution1(String str1, String str2, boolean print) {
		
		if(str1.length() != str2.length()) {
			if(print) System.out.println("Is not anagram...");
			return false;
		}
		
		char[] tmp = str1.toCharArray();
		Arrays.sort(tmp);
		String sortedStr1 = String.valueOf(tmp);
		
		tmp = str2.toCharArray();
		Arrays.sort(tmp);
		String sortedStr2 = String.valueOf(tmp);
		
		if(print) {
			if(sortedStr1.equals(sortedStr2)) {
				System.out.println("Is anagram...");
			} else {
				System.out.println("Is not anagram...");
			}
		}
		return sortedStr1.equals(sortedStr2);
	}
	
	public static boolean ifAnagram_solution2(String str1, String str2, boolean print) {
		if(str1.length() != str2.length()) {
			if(print) System.out.println("Is not anagram...");
			return false;
		}
		
		int[] hash = new int[256];
		for(int i=0;i<str1.length();i++) {
			hash[str1.charAt(i)]++;
		}
		
		for(int i=0;i<str2.length();i++) {
			if(hash[str2.charAt(i)]>0) {
				hash[str2.charAt(i)]--;
			} else {
				if(print) System.out.println("Is not anagram...");
				return false;
			}
		}
		
		for(int i=0;i<256;i++) {
			if(hash[i]>0) {
				if(print) System.out.println("Is not anagram...");
				return false;
			}
		}
		if(print) System.out.println("Is anagram...");
		return true;
	}
	
	public static void problem2_solution1(String[] arr) {
		// Sort words array
		class InternalStructure {
			char[] str;
			int idx;
		}
		
		Comparator<InternalStructure> comp = new Comparator<InternalStructure>() {
			public int compare(InternalStructure o1, InternalStructure o2) {
				String str1 = String.valueOf(o1.str);
				String str2 = String.valueOf(o2.str);
				if(str1.compareTo(str2) > 0) return 1;
				else if(str1.compareTo(str2) < 0) return -1;
				else return 0;
			}
		};
		
		InternalStructure[] newArr = new InternalStructure[arr.length];
		for(int i=0; i<arr.length; i++) {
			newArr[i] = new InternalStructure();
			char[] tmp = arr[i].toCharArray();
			Arrays.sort(tmp);
			newArr[i].str = tmp;
			newArr[i].idx = i;
		}
		
		Arrays.sort(newArr, comp);
		String[] result = new String[arr.length];
		for(int i=0; i<arr.length; i++) {
			result[i] = arr[newArr[i].idx];
		}
		
		System.out.println(Arrays.toString(result));
	}
	public static void main(String[] args) {
		// Problem 1
		String str1 = "adbc";
		String str2 = "cabd";
		ifAnagram_solution1(str1, str2, true);
		ifAnagram_solution2(str1, str2, true);
		
		// Problem 2
		String[] strArr = {"cat", "dog", "tac", "god", "act", "ccc", "eee"};
		problem2_solution1(strArr);
	}

}
