package com.sergeik.strings;

/**
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * At least one digit, followed by a dot '.'.
 * At least one digit, followed by a dot '.', followed by at least one digit.
 * A dot '.', followed by at least one digit.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * At least one digit.
 */
public class ValidNumber {

    public static void main(String[] args) {
        for (String str: new String[]{"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"}) {
            assert solution(str);
        }
        for (String str: new String[]{"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53", "7e69e"}) {
            assert !solution(str);
        }
    }

    private static boolean solution(String s) {
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == 'e' || s.charAt(i) == 'E'))
                return isDecimal(s, 0, i, false) && isDecimal(s, i + 1, s.length(), true);
        }
        return isDecimal(s, 0, s.length(), false);
    }

    private static boolean isDecimal(String s, int start, int end, boolean dotSeen) {
        if (end - start <= 0)
            return false;
        if (s.charAt(start) == '-' || s.charAt(start) == '+')
            start++;
        boolean charSeen = false;
        for (int i = start; i < end; i++) {
            if (s.charAt(i) == '.') {
                if (dotSeen)
                    return false;
                dotSeen = true;
            } else if (!Character.isDigit(s.charAt(i))){
                return false;
            } else {
                charSeen = true;
            }
        }
        return charSeen;
    }

}
