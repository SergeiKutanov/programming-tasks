package com.sergeik.dynamic;

/**
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length
 * and nums[i] > nums[j].
 *
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such
 * that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
 */
public class KInversedPairsArray {

    public static void main(String[] args) {
        assert 2 == solution(3, 1);
    }

    /**
     * From the Dynamic Programming solution, we can also note that we only need the values of the previous row
     * in the dpdp array, and not any other row. Thus, instead of storing the whole 2-D dpdp in memory, we can make
     * use of a 1-D dpdp to store the previous row's entries only. The updations can be done in a 1-D temptemp array
     * of the same size as dpdp and dpdp can be updated using this temptemp everytime a row is finished.
     * @param n
     * @param k
     * @return
     */
    private static int solution(int n, int k) {
        int[] dp = new int[k + 1];
        int M = 1000000007;
        for (int i = 1; i <= n; i++) {
            int[] temp = new int[k + 1];
            temp[0] = 1;
            for (int j = 1; j <= k ; j++) {
                int val = (dp[j] + M - ((j - i) >= 0 ? dp[j - i] : 0)) % M;
                temp[j] = (temp[j - 1] + val) % M;
            }
            dp = temp;
        }
        return ((dp[k] + M - (k > 0 ? dp[k - 1] : 0)) % M);
    }

    private static int dpSolution(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else
                    for (int p = 0; p <= Math.min(j, i - 1); p++)
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
            }
        }
        return dp[n][k];
    }

}
