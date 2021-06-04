package com.sergeik.arrays;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
 *
 * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j]
 * where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 *
 * Find the kth largest value (1-indexed) of all the coordinates of matrix.
 */
public class FindKthLargestXorCoordinateValue {

    public static void main(String[] args) {
        assert 7 == solution(new int[][]{
                {5,2},
                {1,6}
        }, 1);
        assert 5 == solution(new int[][]{
                {5,2},
                {1,6}
        }, 2);
        assert 4 == solution(new int[][]{
                {5,2},
                {1,6}
        }, 3);
        assert 0 == solution(new int[][]{
                {5,2},
                {1,6}
        }, 4);
    }

    private static int solution(int[][] matrix, int k) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        Queue<Integer> heap = new PriorityQueue<>(k);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                dp[i][j] = matrix[i][j];
                int x = i - 1;
                int y = j - 1;
                if (x >= 0)
                    dp[i][j] ^= dp[x][j];
                if (y >= 0)
                    dp[i][j] ^= dp[i][y];
                if (x >= 0 && y >= 0)
                    dp[i][j] ^= dp[x][y];
                if (heap.size() < k)
                    heap.offer(dp[i][j]);
                else if (heap.peek() < dp[i][j]) {
                    heap.poll();
                    heap.offer(dp[i][j]);
                }
            }
        }
        return heap.poll();
    }

}
