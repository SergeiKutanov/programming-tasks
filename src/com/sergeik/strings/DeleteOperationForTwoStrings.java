package com.sergeik.strings;

/**
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 */
public class DeleteOperationForTwoStrings {

    public static void main(String[] args) {
        assert 8 == solution("intention", "execution");
        assert 8 == solution("industry", "interest");
        assert 3 == solution("park", "spake");
        assert 2 == solution("a", "b");
        assert 4 == solution("sea", "ate");
        assert 2 == solution("sea", "eat");
        assert 4 == solution("leetcode", "etco");
    }

    /**
     * Build matrix like
     *
     *      0    s   p   a   k   e
     *  0   0   0   0   0   0   0
     *  p   0   0   1   1   1   1
     *  a   0   0   1   2   2   2
     *  r   0   0   1   2   2   2
     *  k   0   0   1   2   3   3
     *
     *  bottom right corner will have the longest common subsequence
     *  deduct that subsequence length from the sum of chars in both words
     *
     * @param word1
     * @param word2
     * @return
     */
    private static int solution(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int r = 1; r <= word1.length(); r++) {
            for (int c = 1; c <= word2.length(); c++) {
                if (word1.charAt(r - 1) == word2.charAt(c - 1)) {
                    dp[r][c] = dp[r - 1][c - 1] + 1;
                } else {
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
                }
            }
        }
        int changes = dp[word1.length()][word2.length()];
        return word1.length() - changes + word2.length() - changes;
    }

}
