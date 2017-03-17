package wenyu.learning.Implementations;

import java.util.HashMap;
import java.util.Random;

/*
 * Problem1: Create a data structure that has fast [O(1)] insertion, removal, membership testing, and random selection
	 * Logic:
	 * A: Consider a data structure composed of a hashtable H and an array A. The hashtable keys are the elements in the data structure, 
	 * and the values are their positions in the array.
	 * 1. insert(value): append the value to array and let i be it's index in A. Set H[value]=i.
	 * 2. remove(value): We are going to replace the cell that contains value in A with the last element in A. let d be the last element in the array A at index m. let i be H[value], the index in the array of the value to be removed. Set A[i]=d, H[d]=i, decrease the size of the array by one, and remove value from H.
	 * 3. contains(value): return H.contains(value)
	 * 4. getRandomElement(): let r=random(current size of A). return A[r].
	 * 
 */

class StructureDesignProblem1<E> {
	private final E[] array;
	private int currentIdx;
	private final HashMap<E, Integer> map;
	public StructureDesignProblem1(int length) {
		array = (E[]) new Object[length];
		map = new HashMap<E, Integer>();
		currentIdx = 0;
	}
	
	public void insert(E value) {
		array[currentIdx] = value;
		map.put(value, currentIdx);
		currentIdx++;
	}
	
	public void remove(E value) {
		int idx = map.get(value);
		array[idx] = array[currentIdx-1];
		array[currentIdx-1] = null;
		map.put(array[idx], idx);
		currentIdx--;
	}
	
	public boolean contain(E value) {
		return map.containsKey(value);
	}
	
	public E random() {
		int idx = new Random().nextInt(currentIdx);
		return array[idx];
	}
}

public class StructureWithO1Ops{
	public static void main(String[] args) {
		StructureDesignProblem1<Integer> my = new StructureDesignProblem1<Integer>(10);
		my.insert(1);
		my.insert(2);
		System.out.println(my.contain(1));
	}
}
