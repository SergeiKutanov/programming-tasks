package com.sergeik.dynamic;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 */
public class PalindromePartitioningII {

    public static void main(String[] args) {
        assert 1 == solution("aab");
    }

    private static int solution(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                /*
                Why i - j < 3  ?
                1. String of length 1 is always palindrome so no need to check in boolean table
                2. String of length 2 is palindrome if Ci == Cj which is already checked in first part so no need to
                    check again
                3. String of length 3 is palindrome if Ci == Cj which is already checked in first part and Ci+1 and
                    Cj-1 is same character which is always a palindrome
                 */
                if (s.charAt(i) == s.charAt(j) && (i - j < 3 || pal[j + 1][i - 1])) {
                    pal[i][j] = true;
                    min = j == 0 ? 0 : Math.min(min, count[j - 1] + 1);
                }
            }
            count[i] = min;
        }
        return count[n - 1];
    }

}
