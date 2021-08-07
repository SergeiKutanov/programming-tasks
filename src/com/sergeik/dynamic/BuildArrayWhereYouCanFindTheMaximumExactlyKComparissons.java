package com.sergeik.dynamic;

/**
 * Given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of
 * positive integers:
 *
 *
 * You should build the array arr which has the following properties:
 *
 * arr has exactly n integers.
 * 1 <= arr[i] <= m where (0 <= i < n).
 * After applying the mentioned algorithm to arr, the value search_cost is equal to k.
 * Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large,
 * the answer must be computed modulo 10^9 + 7.
 */
public class BuildArrayWhereYouCanFindTheMaximumExactlyKComparissons {

    public static void main(String[] args) {
        assert 34549172 == solution(50, 100, 25);
        assert 418930126 == solution(37,17,7);
        assert 1 == solution(9,1,1);
        assert 0 == solution(5,2,3);
        assert 6 == solution(2,3,1);
    }

    private static int solution(int n, int m, int k) {
        Integer[][][] dp = new Integer[n + 1][m + 1][k + 1];
        return dfs(n, m, k, 0, 0, 0, dp);
    }

    private static int dfs(int n, int m, int k, int idx, int cMax, int cCost, Integer[][][] dp) {
        if (idx == n)
            return k == cCost ? 1 : 0;
        if (dp[idx][cMax][cCost] != null)
            return dp[idx][cMax][cCost];
        int ans = 0;
        for (int num = 1; num <= m; num++) {
            int nCost = cCost;
            int nMax = cMax;
            if (num > cMax) {
                nCost++;
                nMax = num;
            }
            if (nCost > k)
                break;
            ans += dfs(n, m, k, idx + 1, nMax, nCost, dp);
            ans %= 1_000_000_007;
        }
        return dp[idx][cMax][cCost] = ans;
    }

}
