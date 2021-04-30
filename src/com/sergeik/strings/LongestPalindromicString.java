package com.sergeik.strings;

import java.util.Stack;

/**
 * Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromicString {

    private static int startIndex;
    private static int maxLength;

    public static void main(String[] args) {

        assert "bab".equals(bruteSolution("babad"));
        assert "bb".equals(bruteSolution("cbbd"));
        assert "a".equals(bruteSolution("a"));
        assert "a".equals(bruteSolution("ac"));
        assert "aca".equals(bruteSolution("aacabdkacaa"));

        assert "bab".equals(solution("babad"));
        assert "bb".equals(solution("cbbd"));
        assert "a".equals(solution("a"));
        assert "a".equals(solution("ac"));
        assert "aca".equals(solution("aacabdkacaa"));
    }

    private static String bruteSolution(String s) {
        String maxPalindrome = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + maxPalindrome.length(); j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                String reversed = new StringBuilder(substring).reverse().toString();
                if (substring.equals(reversed) && substring.length() > maxPalindrome.length()) {
                    maxPalindrome = substring;
                }
            }
        }
        return maxPalindrome;
    }

    private static String solution(String s) {

        startIndex = 0;
        maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            extent(i, i, s);
            extent(i, i + 1, s);
        }

        return s.substring(startIndex, startIndex + maxLength);

    }

    private static void extent(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        if (maxLength < j - i - 1) {
            maxLength = j - i - 1;
            startIndex = i + 1;
        }
    }

}
