package com.sergeik.dynamic;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the
 * reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 */
public class DecodeWays {

    public static void main(String[] args) {
        assert 2 == solution("11102");
    }

    /**
     * Using dp to keep track of possible options up to i;
     * Each time look at the substring of one character and two previous characters;
     * @param s
     * @return
     */
    private static int solution(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first > 0)
                dp[i] += dp[i - 1];
            if (second >= 10 && second < 27) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

}
