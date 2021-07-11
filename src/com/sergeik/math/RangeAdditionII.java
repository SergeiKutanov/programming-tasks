package com.sergeik.math;

/**
 * You are given an m x n matrix M initialized with all 0's and an array of operations ops, where
 * ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
 *
 * Count and return the number of maximum integers in the matrix after performing all the operations.
 */
public class RangeAdditionII {

    public static void main(String[] args) {
        assert 2 == solution(18, 3, new int[][] {
                {16,1},{14,3},{14,2},{4,1},{10,1},{11,1},{8,3},
                {16,2},{13,1},{8,3},{2,2},{9,1},{3,1},{2,2},{6,3}
        });
    }

    private static int solution(int m, int n, int[][] ops) {
        int minX = m, minY = n;
        for (int[] op: ops) {
            minX = Math.min(minX, op[0]);
            minY = Math.min(minY, op[1]);
        }
        return minX * minY;
    }

}
