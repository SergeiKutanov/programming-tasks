package com.sergeik.dynamic;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 */
public class PalindromicSubstrings {

    private static int count = 0;

    public static void main(String[] args) {
        assert 4 == solution("aba");
        assert 3 == solution("abc");
        assert 6 == solution("aaa");

        assert 4 == prevSolution("aba");
        assert 3 == prevSolution("abc");
        assert 6 == prevSolution("aaa");
    }

    private static int prevSolution(String s) {
        count = 0;
        for (int i = 0; i < s.length(); i++) {
            extendGlobal(s, i, i);
            extendGlobal(s, i, i + 1);
        }
        return count;
    }

    private static void extendGlobal(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            count++;
        }
    }

    private static int solution(String s) {
        /*
        aaa
        dp = 1  0   0
        dp = 1  3   0
        dp = 1  3   6

        aba
        dp = 1  0   0
        dp = 1  3   0
        dp = 1  3   4
         */
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int count = i - 1 >= 0 ? dp[i - 1] : 0;
            int maxExtend = extend(s, i, i) + extend(s, i, i + 1);
            dp[i] = count + maxExtend;
        }
        return dp[s.length() - 1];
    }

    private static int extend(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            count++;
        }
        return count;
    }

}
