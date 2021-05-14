package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.List;

/**
 * We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas,
 * decimal points, and spaces, and ended up with the string s.  Return a list of strings representing
 * all possibilities for what our original coordinates could have been.
 *
 * Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0",
 * "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point
 * within a number never occurs without at least one digit occuring before it, so we never started with
 * numbers like ".1".
 *
 * The final answer list can be returned in any order.  Also note that all coordinates in the final answer have
 * exactly one space between them (occurring after the comma.)
 */
public class AmbiguousCoordinates {

    public static void main(String[] args) {
        List<String> res = solution("(100)");
        res = solution("(123)");
        solution("(00011)");
    }

    private static List<String> solution(String s) {
        int n = s.length();
        List<String> res = new LinkedList<>();
        for (int i = 1; i < n - 2; i++) {
            List<String> left = find(s.substring(1, i + 1));
            List<String> right = find(s.substring(i+1, n-1));
            for (String l: left){
                for (String r: right) {
                    res.add("(" + l + ", " + r + ")");
                }
            }
        }
        return res;
    }

    private static List<String> find(String s) {
        int n = s.length();
        List<String> res = new LinkedList<>();
        if (n == 0)
            return res;
        //0XXXX0 - not valid
        if (n > 1 && s.charAt(0) == '0' && s.charAt(n - 1) == '0')
            return res;
        //0XXXXXD - only one valid option
        if (n > 1 && s.charAt(0) == '0') {
            res.add("0." + s.substring(1));
            return res;
        }
        //string is valid by itself
        res.add(s);
        //D || XXXXX0 - return
        if (n == 1 || s.charAt(n - 1) == '0')
            return res;
        for (int i = 1; i < n; i++) {
            res.add(s.substring(0, i) + '.' + s.substring(i));
        }
        return res;
    }

}
