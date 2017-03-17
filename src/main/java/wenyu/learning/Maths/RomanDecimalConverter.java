package wenyu.learning.Maths;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
 * Roman Number and Decimal Converter
 */

public class RomanDecimalConverter {
	private static final Map<Integer, String> DecToRomanMap = new LinkedHashMap<Integer, String>();
	private static final Map<String, Integer> RomanToDecMap = new LinkedHashMap<String, Integer>();
	static {
		DecToRomanMap.put(1000, "M");
		RomanToDecMap.put("M", 1000);
		DecToRomanMap.put(900, "CM");
		DecToRomanMap.put(500, "D");
		RomanToDecMap.put("D", 500);
		DecToRomanMap.put(400, "CD");
		DecToRomanMap.put(100, "C");
		RomanToDecMap.put("C", 100);
		DecToRomanMap.put(90, "XC");
		DecToRomanMap.put(50, "L");
		RomanToDecMap.put("L", 50);
		DecToRomanMap.put(40, "XL");
		DecToRomanMap.put(10, "X");
		RomanToDecMap.put("X", 10);
		DecToRomanMap.put(9, "IX");
		DecToRomanMap.put(5, "V");
		RomanToDecMap.put("V", 5);
		DecToRomanMap.put(4, "IV");
		DecToRomanMap.put(1, "I");
		RomanToDecMap.put("I", 1);
	}
	
	public static long RomanToDecimal(String romanNumber, boolean print) {
		long decimal = 0;
	    int currNumber = 0;
	    int preNumber = 0;
	    String romanNumeral = romanNumber.toUpperCase();
	    
	    /* operation to be performed on upper cases even if user enters roman values in lower case chars */
	    for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
	        char convertToDecimal = romanNumeral.charAt(x);
	        if(RomanToDecMap.containsKey(String.valueOf(convertToDecimal))) {
	        	currNumber = RomanToDecMap.get(String.valueOf(convertToDecimal));
	        }
	        
	        if (preNumber > currNumber) {
	        	decimal = decimal - currNumber;
		    } else {
		    	decimal = decimal + currNumber;
		    }
	        preNumber = currNumber;
	    }
	    if(print) System.out.println(decimal);
	    return decimal;
	}
	
	public static String DecimalToRoman(long decimal, boolean print) {
		String roman = "";  
        long N = decimal;
        Iterator<Integer> mapSet = DecToRomanMap.keySet().iterator();
        while(mapSet.hasNext()) {
        	int num = mapSet.next();
        	while (N >= num) {
                roman += DecToRomanMap.get(num);
                N -= num;
             }
        }
        
        if(print) System.out.println(roman);
        return roman;
	}
	
	public static void main(String[] args) {
		String roman = DecimalToRoman(123l, true);
		RomanToDecimal(roman, true);
	}

}
