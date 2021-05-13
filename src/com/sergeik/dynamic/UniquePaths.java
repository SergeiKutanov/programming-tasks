package com.sergeik.dynamic;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 */
public class UniquePaths {

    public static void main(String[] args) {
        assert 28 == solution(3, 7);
        assert 3 == solution(3, 2);
        assert 28 == solution(7, 3);
        assert 6 == solution(3, 3);
        assert 1 == solution(0, 1);
        assert 1 == solution(1, 0);
        assert 1 == solution(1, 1);
    }

    private static int solution(int m, int n) {
        return mathSolution(m, n);
    }

    /**
     * This is a combinatorial problem and can be solved without DP. For mxn grid, robot has to move exactly
     * m-1 steps down and n-1 steps right and these can be done in any order.
     *
     * For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps with 2 down and 6 right
     * in any order. That is nothing but a permutation problem. Denote down as 'D' and right as 'R',
     * following is one of the path :-
     *
     * D R R R D R R R
     *
     * We have to tell the total number of permutations of the above given word. So, decrease both m & n
     * by 1 and apply following formula:-
     *
     * Total permutations = (m+n)! / (m! * n!)
     * @param m
     * @param n
     * @return
     */
    private static int mathSolution(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if (m < n) {        //swap so m is bigger
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for (int i = m + 1; i <= m + n; i++, j++) {  // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }
        return (int)res;
    }

    private static int dpAllSolution(int m, int n) {

        if (m == 0 || n == 0)
            return 1;

        int[][] dirs = new int[][] {
                {0, -1},
                {-1, 0}
        };
        int dp[][] = new int[m][n];
        dp[0][0] = 1;
        for (int r = 0; r < dp.length; r++) {
            for (int c = 0; c < dp[0].length; c++) {
                for (int[] d: dirs) {
                    int x = r + d[0];
                    int y = c + d[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        dp[r][c] += dp[x][y];
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
