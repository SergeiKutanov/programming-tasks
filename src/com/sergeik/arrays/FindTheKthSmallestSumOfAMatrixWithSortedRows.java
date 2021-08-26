package com.sergeik.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 *
 * You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum
 * among all possible arrays.
 */
public class FindTheKthSmallestSumOfAMatrixWithSortedRows {

    public static void main(String[] args) {
        assert 17 == solution(new int[][] {
                {1,3,11},
                {2,4,6}
        }, 9);
        assert 9 == solution(new int[][] {
                {1,10,10},
                {1,4,5},
                {2,3,6}},
                7
        );
        assert 7 == solution(new int[][] {
                {1,3,11},
                {2,4,6}
        }, 5);
    }

    private static int solution(int[][] mat, int k) {
        int n = mat.length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] candidate = new int[n + 1];
        for (int row = 0; row < mat.length; row++)
            candidate[0] += mat[row][0];
        heap.offer(candidate);
        int res = 0;
        Set<String> seen = new HashSet<>();
        seen.add(Arrays.toString(heap.peek()));
        while (!heap.isEmpty() && k-- > 0) {
               int[] cand = heap.poll();
               res = cand[0];
               for (int row = 0; row < mat.length; row++) {
                   int nextCol = cand[row + 1];
                   if (nextCol < mat[0].length - 1) {
                       int[] nextCand = Arrays.copyOfRange(cand, 0, cand.length);
                       nextCand[0] -= mat[row][nextCol];
                       nextCand[0] += mat[row][++nextCol];
                       nextCand[row + 1]++;
                       String key = Arrays.toString(nextCand);
                       if (!seen.contains(key)) {
                           seen.add(key);
                           heap.offer(nextCand);
                       }

                   }
               }
        }
        return res;
    }

}
