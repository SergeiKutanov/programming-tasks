package com.sergeik.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
 * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all
 * the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 */
public class TheKWeakestRowsInMatrix {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {2,0,3},
                solution(new int[][] {
                        {1,1,0,0,0},
                        {1,1,1,1,0},
                        {1,0,0,0,0},
                        {1,1,0,0,0},
                        {1,1,1,1,1}
                }, 3)
        );
    }

    private static int[] solution(int[][] mat, int k) {
        int[] counts = new int[mat.length];
        for (int i = 0; i < mat.length; i++)
            counts[i] = countOnes(mat[i]);

        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> {
            if (counts[a] == counts[b])
                return a - b;
            return counts[a] - counts[b];
        });
        for (int i = 0; i < counts.length; i++)
            heap.offer(i);

        int[] res = new int[k];
        for (int i = 0; i < k && !heap.isEmpty(); i++)
            res[i] = heap.poll();
        return res;
    }

    private static int countOnes(int[] row) {
        int left = 0, right = row.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (row[mid] == 1)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

}
