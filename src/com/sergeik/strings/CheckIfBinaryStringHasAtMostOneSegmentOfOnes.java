package com.sergeik.strings;

/**
 * Given a binary string s ​​​​​without leading zeros, return true​​​
 * if s contains at most one contiguous segment of ones. Otherwise, return false.
 */
public class CheckIfBinaryStringHasAtMostOneSegmentOfOnes {

    public static void main(String[] args) {
        assert solution("1");
        assert solution("10");
        assert solution("11");
        assert !solution("11101101");
        assert !solution("1110101");
        assert solution("110");
    }

    private static boolean solution(String s) {
        if (s == null)
            return false;
        boolean inSegment = true;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                inSegment = false;
            } else if (!inSegment)
                return false;
        }
        return true;
    }

}
