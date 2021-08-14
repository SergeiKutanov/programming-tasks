package com.sergeik.dynamic;

/**
 * You are given several boxes with different colors represented by different positive numbers.
 *
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some
 * continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 *
 * Return the maximum points you can get.
 */
public class RemoveBoxes {

    public static void main(String[] args) {
        assert 23 == bottomUpDpSolution(new int[]{1,3,2,2,2,3,4,3,1});
    }

    private static int bottomUpDpSolution(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k <= j; k++) {
                dp[j][j][k] = (k + 1) * (k + 1);
            }
        }

        for (int l = 1; l < n; l++) {
            for (int j = l; j < n; j++) {
                int i = j - l;

                for (int k = 0; k <= i; k++) {
                    int res = (k + 1) * (k + 1) + dp[i + 1][j][0];

                    for (int m = i + 1; m <= j; m++) {
                        if (boxes[m] == boxes[i]) {
                            res = Math.max(res, dp[i + 1][m - 1][0] + dp[m][j][k + 1]);
                        }
                    }

                    dp[i][j][k] = res;
                }
            }
        }

        return (n == 0 ? 0 : dp[0][n - 1][0]);
    }

    private static int topDownDpSolution(int[] boxes) {
        int[][][] memo = new int[boxes.length][boxes.length][boxes.length];
        return dfs(boxes, 0, boxes.length - 1, 0, memo);
    }

    private static int dfs(int[] boxes, int i, int j, int k, int[][][] memo) {
        if (i > j) return 0;
        if (memo[i][j][k] > 0) return memo[i][j][k];
        for (; i + 1 <= j && boxes[i] == boxes[i + 1]; i++, k++);
        int res = (k + 1) * (k + 1) + dfs(boxes, i + 1, j, 0, memo);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[i] == boxes[m]) {
                res = Math.max(
                        res,
                        dfs(boxes, i + 1, m - 1, 0, memo) + dfs(boxes, m, j, k + 1, memo)
                );
            }
        }
        return memo[i][j][k] = res;
    }

}
