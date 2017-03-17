package wenyu.learning.Strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/*
 * N strings are given.Convert all string to corresponding decimal value typed in an Alphanumeric Keypad 
 * (e.g. “bdg” -> 234). Then print all strings in decreasing order of their decimal value. If they have same 
 * decimal value then print lexicographically smaller first.
 * Input:
 * Amazon
 * sun
 * run
 * Output:
 * 262966  amazon
 * 786 run
 * 786 sun 
 */
public class SortStrByKeypad {

	/*
	 * 2: a,b,c
	 * 3: d,e,f
	 * 4: g,h,i
	 * 5: j,k,l
	 * 6: m,n,o
	 * 7: p,q,r,s
	 * 8: t,u,v
	 * 9: w,x,y,z
	 */
	public static final HashMap<Character, Integer> DIG_KEY_MAP;
	static {
		DIG_KEY_MAP = new HashMap<Character, Integer>();
		DIG_KEY_MAP.put('a',2);
		DIG_KEY_MAP.put('b',2);
		DIG_KEY_MAP.put('c',2);
		DIG_KEY_MAP.put('d',3);
		DIG_KEY_MAP.put('e',3);
		DIG_KEY_MAP.put('f',3);
		DIG_KEY_MAP.put('g',4);
		DIG_KEY_MAP.put('h',4);
		DIG_KEY_MAP.put('i',4);
		DIG_KEY_MAP.put('j',5);
		DIG_KEY_MAP.put('k',5);
		DIG_KEY_MAP.put('l',5);
		DIG_KEY_MAP.put('m',6);
		DIG_KEY_MAP.put('n',6);
		DIG_KEY_MAP.put('o',6);
		DIG_KEY_MAP.put('p',7);
		DIG_KEY_MAP.put('q',7);
		DIG_KEY_MAP.put('r',7);
		DIG_KEY_MAP.put('s',7);
		DIG_KEY_MAP.put('t',8);
		DIG_KEY_MAP.put('u',8);
		DIG_KEY_MAP.put('v',8);
		DIG_KEY_MAP.put('w',9);
		DIG_KEY_MAP.put('x',9);
		DIG_KEY_MAP.put('y',9);
		DIG_KEY_MAP.put('z',9);
	}
	
	public static void sort(String[] strs) {
		Comparator<String> comp = new Comparator<String>() {
			private int convert(String str) {
				int digits = 0;
				for(int i=0; i<str.length(); i++) {
					char ch = str.charAt(i);
					ch = String.valueOf(ch).toLowerCase().charAt(0);
					int key = DIG_KEY_MAP.get(ch);
					digits = digits*10+key;
				}
				return digits;
			}
			
			public int compare(String o1, String o2) {
				int i1 = convert(o1);
				int i2 = convert(o2);
				
				if(i1>i2) {
					return -1;
				} else if(i1<i2) {
					return 1;
				} else {
					return o1.compareTo(o2);
				}
			}
			
		};
		
		Arrays.sort(strs, comp);
	}
	
	public static void main(String[] args) {
		String[] strs = {"Amazon", "sun", "run"};
		sort(strs);
		
		System.out.println(Arrays.toString(strs));
	}

}
