package com.sergeik.arrays;

import java.util.PriorityQueue;

/**
 * You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the elements
 * in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other words, you
 * do not know the elements of the matrix, but you do know the sums of each row and column.
 *
 * Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and
 * colSum requirements.
 *
 * Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one matrix
 * that fulfills the requirements exists.
 */
public class FindValidMatrixGivenRowAndColumnSums {

    public static void main(String[] args) {
        int[][] exp, res;

        exp = new int[][] {
                {0,5,0},
                {6,1,0},
                {2,0,0}
        };
        res = solution(new int[]{5,7,10}, new int[]{8,6,8});
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int[] rowSum, int[] colSum) {
        PriorityQueue<Integer> rows = new PriorityQueue<>((a,b) -> rowSum[a] - rowSum[b]);
        PriorityQueue<Integer> cols = new PriorityQueue<>((a,b) -> colSum[a] - colSum[b]);
        for (int i = 0; i < rowSum.length; i++)
            rows.offer(i);
        for (int i = 0; i < colSum.length; i++)
            cols.offer(i);

        int[][] res = new int[rowSum.length][colSum.length];

        while (!rows.isEmpty() && !cols.isEmpty()) {
            int minRow = rows.poll();
            int minCol = cols.poll();
            int min = Math.min(rowSum[minRow], colSum[minCol]);
            res[minRow][minCol] = min;
            rowSum[minRow] -= min;
            colSum[minCol] -= min;
            if (rowSum[minRow] > 0)
                rows.offer(minRow);
            if (colSum[minCol] > 0)
                cols.offer(minCol);
        }

        return res;
    }

}
