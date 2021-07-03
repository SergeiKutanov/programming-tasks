package com.sergeik.dynamic;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that
 * its sum is no larger than k.
 *
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 */
public class MaxSumOfRectangleNoLargerThanK {

    private static int result;

    public static void main(String[] args) {

        assert -2 == solution(new int[][]{
                {5,-4,-3,4},
                {-3,-4,4,5},
                {5,1,5,-4}
        }, -2);

        assert -1 == solution(new int[][]{
                {2,2,-1}
        }, 0);
        assert 30 == solution(new int[][]{
                {1,2,3,4},
                {1,2,3,4},
                {1,7,8,4},
                {1,10,5,4},
                {1,2,3,4},
        }, 31);
        assert 3 == solution(new int[][]{
                {2,2,-1}
        }, 3);
        assert 2 == solution(new int[][]{
                {1,0,1},
                {0,-2,3}
        }, 2);
    }

    private static int solution(int[][] matrix, int k) {
        result = Integer.MIN_VALUE;
        int[] rowSum = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(rowSum, 0);
            for (int r = i; r < matrix.length; r++) {
                for (int c = 0; c < matrix[r].length; c++)
                    rowSum[c] += matrix[r][c];
                updateRow(rowSum, k);
                if (k == result)
                    return result;
            }
        }
        return result;
    }

    private static void updateRow(int[] nums, int k) {
        int sum = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        for (int n: nums) {
            sum += n;
            Integer x = set.ceiling(sum - k);
            if (x != null) {
                result = Math.max(result, sum - x);
            }
            set.add(sum);
        }
    }

    private static int bruteSolution(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        for (int r = 1; r <= m; r++)
            for (int c = 1; c <= n; c++)
                prefix[r][c] = prefix[r - 1][c] + prefix[r][c - 1] - prefix[r - 1][c - 1] + matrix[r - 1][c - 1];

        Integer res = null;
        for (int r = 1; r <= m; r++)
            for (int c = 1; c <= n; c++)
                for (int x = r; x <= m; x++)
                    for (int y = c; y <= n; y++) {
                        int sum = prefix[x][y] - prefix[x][c - 1] - prefix[r - 1][y] + prefix[r - 1][c - 1];
                        if (sum > k)
                            continue;
                        int diff = k - sum;
                        if (diff == 0)
                            return sum;
                        if (res == null || diff < (k - res))
                            res = sum;
                    }

        return res;
    }

}
