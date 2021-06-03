package com.sergeik.strings;

/**
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large,
 * return it modulo 109 + 7.
 *
 * A string is homogenous if all the characters of the string are the same.
 *
 * A substring is a contiguous sequence of characters within a string.
 */
public class CountNumberOfHomogenousStrings {

    public static void main(String[] args) {

        assert 15 == countSolution("zzzzz");
        assert 2 == countSolution("xy");
        assert 13 == countSolution("abbcccaa");

        assert 15 == solution("zzzzz");
        assert 2 == solution("xy");
        assert 13 == solution("abbcccaa");
    }

    private static int countSolution(String s) {
        int cur = 0, res = 0, count = 0, mod = 1_000_000_007;
        for (int i = 0; i < s.length(); i++) {
            count = cur == s.charAt(i) ? count + 1 : 1;
            cur = s.charAt(i);
            res = (res + count) % mod;
        }
        return res;
    }

    private static int solution(String s) {
        long count = 0;
        int idx = 0;
        while (idx < s.length()) {
            char ch = s.charAt(idx);
            int start = idx;
            while (idx < s.length() && ch == s.charAt(idx))
                idx++;
            long charCount = idx - start;
            count += charCount * (charCount + 1) / 2;
            count %= 1000000007;
        }
        return (int)(count % 1000000007);
    }

}
