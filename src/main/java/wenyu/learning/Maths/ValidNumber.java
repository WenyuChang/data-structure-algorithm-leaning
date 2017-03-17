package wenyu.learning.Maths;

/**
 * Created by Wenyu on 12/1/16.
 */
public class ValidNumber {
    private int scanDigits(String input, int idx) {
        //Scan Digits
        if (idx == input.length() && input.length() > 1) {
            return input.length();
        } else if (idx == input.length() && input.length() <= 1) {
            return -1;
        } else if (idx > input.length()) {
            return -1;
        }
        if (input.charAt(idx) == 'e' || input.charAt(idx) == 'E') {
            return -1;
        }

        for(;idx<input.length();idx++) {
            if (input.charAt(idx) == '.' || input.charAt(idx) == 'e' || input.charAt(idx) == 'E') {
                return idx;
            } else {
                try {
                    int figure = Integer.valueOf(String.valueOf(input.charAt(idx)));
                } catch (Exception ex) {
                    return -1;
                }
            }
        }

        return idx;
    }

    private int scanDigital(String input, int idx) {
        double digital = 0;
        idx++;

        idx = scanDigits(input, idx);
        if(idx<0 || (idx<input.length() && input.charAt(idx) == '.')) {
            return -1;
        }
        return idx;
    }

    private int scanExponential(String input, int idx) {
        idx++;
        if (idx >= input.length()) {
            return -1;
        }

        if(input.charAt(idx) == '+' || input.charAt(idx) == '-') {
            idx++;
        }

        idx = scanDigits(input, idx);
        if(idx<0 || (idx<input.length() && (input.charAt(idx) == '.' || input.charAt(idx) == 'e' || input.charAt(idx) == 'E'))) {
            return -1;
        }
        return idx;
    }

    public boolean isNumber(String input) {
        if(input==null || input.trim().length() == 0) {
            return false;
        }

        input = input.trim();

        double number = 0;
        int idx = 0;
        if(input.charAt(0) == '+' || input.charAt(0) == '-') {
            idx++;
        }

        idx = scanDigits(input, idx);
        if (idx < 0) {
            return false;
        } else if (idx == input.length()) {
            return true;
        }

        if (idx<input.length() && input.charAt(idx) == '.') {
            idx = scanDigital(input, idx);
            if (idx < 0) {
                return false;
            } else if (idx == input.length()) {
                return true;
            }
        }

        if(idx<input.length() && (input.charAt(idx) == 'e' || input.charAt(idx) == 'E')) {
            idx = scanExponential(input, idx);
            if (idx < 0) {
                return false;
            } else if (idx == input.length()) {
                return true;
            }
        }

        if(idx<input.length()) {
            return false;
        }

        return true;
    }
}
