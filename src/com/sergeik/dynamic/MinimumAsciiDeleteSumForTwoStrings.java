package com.sergeik.dynamic;

/**
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 */
public class MinimumAsciiDeleteSumForTwoStrings {

    public static void main(String[] args) {
        assert 100 == solution("d", "l");
        assert 878 == solution("bbccacacaab", "aabccb");
        assert 1399 == solution("ccaccjp", "fwosarcwge");
        assert 231 == solution("sea", "eat");
        assert 403 == solution("delete", "leet");
    }

    private static int solution(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++)
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        for (int i = 1; i <= s2.length(); i++)
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            dp[i - 1][j] + s1.charAt(i - 1),
                            dp[i][j - 1] + s2.charAt(j - 1)
                    );
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }


    private static int dpTopBottomSolution(String s1, String s2) {
        return dfs(s1, s2, 0, 0, new Integer[s1.length() + 1][s2.length() + 1]);
    }

    private static int dfs(String s1, String s2, int i, int j, Integer[][] dp) {
        //base case
        if (i == s1.length() && j == s2.length()) {
            return 0;
        }
        if (dp[i][j] != null)
            return dp[i][j];

        if (i == s1.length()) {
            int resSum = 0;
            while (j < s2.length())
                resSum += s2.charAt(j++);
            dp[i][j] = resSum;
            return resSum;
        }
        if (j == s2.length()) {
            int resSum = 0;
            while (i < s1.length())
                resSum += s1.charAt(i++);
            dp[i][j] = resSum;
            return resSum;
        }
        //recurse
        int sum = 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            sum = dfs(s1, s2, i + 1, j + 1, dp);
        } else {
            int res1 = dfs(s1, s2, i, j + 1, dp) + s2.charAt(j);
            int res2 = dfs(s1, s2, i + 1, j, dp) + s1.charAt(i);
            sum = Math.min(res1, res2);
        }
        dp[i][j] = sum;
        return dp[i][j];
    }

}
