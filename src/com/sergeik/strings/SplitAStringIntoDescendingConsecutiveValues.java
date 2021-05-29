package com.sergeik.strings;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given a string s that consists of only digits.
 *
 * Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings
 * are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.
 *
 * For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89].
 * The values are in descending order and adjacent values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"].
 * However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively,
 * all of which are not in descending order.
 * Return true if it is possible to split s​​​​​​ as described above, or false otherwise.
 *
 * A substring is a contiguous sequence of characters in a string.
 */
public class SplitAStringIntoDescendingConsecutiveValues {

    public static void main(String[] args) {
        assert solutionLong("200100");
        assert solutionLong("10");
        assert !solutionLong("64424509442147483647");
        assert !solutionLong("9080701");
        assert !solutionLong("1234");
        assert solutionLong("050043");
        assert solutionLong("10009998");

    }

    private static boolean solutionLong(String s) {
        return backtrackLong(s, 0, null);
    }

    private static boolean backtrackLong(String s, int start, Long previous) {
        if (start == s.length())
            return false;
        else {
            long current = 0;
            for (int i = start; i < s.length(); i++) {
                current = current * 10 + s.charAt(i) - '0';
                if (current >= 10000000000L)
                    return false;
                if (previous == null || previous - current == 1) {
                    if (i + 1 == s.length() && previous != null)
                        return true;
                    if (backtrackLong(s, i + 1, current)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean solutionStringOnly(String s) {
        return backtrack(s, 0, new LinkedList<>());
    }

    private static boolean backtrack(String s, int start, List<String> list) {
        if (start == s.length()) {
            return list.size() > 1;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String v = subString(s, start, i);
            if (list.size() > 0 && !diff(list.get(list.size() - 1), v)) {
                continue;
            }
            list.add(v);
            if (backtrack(s, i, list))
                return true;
            list.remove(list.size() - 1);
        }
        return false;
    }

    private static String subString(String s, int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();
        while (start < end) {
            if (stringBuilder.length() == 0 && s.charAt(start) == '0')
                start++;
            else
                stringBuilder.append(s.charAt(start++));
        }
        if (stringBuilder.length() == 0) {
            return "0";
        }
        return stringBuilder.toString();
    }

    private static boolean diff(String s1, String s2) {
        int carryOver = 1;
        int idx = s2.length() - 1;
        StringBuilder s2PlusOne = new StringBuilder();
        while (idx >= 0) {
            int ch = s2.charAt(idx) - '0' + carryOver;
            if (ch == 10) {
                carryOver = 1;
                ch = 0;
            } else {
                carryOver = 0;
            }
            s2PlusOne.insert(0, (char) ('0' + ch));
            idx--;
        }
        if (carryOver > 0)
            s2PlusOne.insert(0, '1');
        return s1.equals(s2PlusOne.toString());
    }

}
