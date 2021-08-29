package com.sergeik.dynamic;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 */
public class PalinromePartitioningIII {

    public static void main(String[] args) {
        assert 1 == solution("abc", 2);
    }

    private static int solution(String s, int k) {
        int[][] pal = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                pal[i][j] = getChanges(s, i, j);
            }
        }
        int[][] dp = new int[k + 1][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[1][i] = pal[0][i];
        }
        for (int i = 2; i <= k; i++) {
            for (int end = i-1; end < s.length(); end++) {
                int min = s.length();
                for (int start = end-1; start >= i-2; start--) {
                    min = Math.min(min, dp[i-1][start] + pal[start+1][end]);
                }
                dp[i][end] = min;
            }
        }
        return dp[k][s.length()-1];
    }

    private static int getChanges(String s, int start, int end) {
        int changes = 0;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                changes++;
            start++; end--;
        }
        return changes;
    }

}
