package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class SpiralMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        assert Arrays.equals(new int[]{1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13}, toArray(solution(matrix)));
        assert Arrays.equals(new int[]{1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13}, toArray(optimizedSolution(matrix)));



        matrix = new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        assert Arrays.equals(new int[]{1,2,3,4,8,12,11,10,9,5,6,7}, toArray(solution(matrix)));
        assert Arrays.equals(new int[]{1,2,3,4,8,12,11,10,9,5,6,7}, toArray(optimizedSolution(matrix)));
        assert Arrays.equals(new int[]{1,2,3,6,9,8,7,4,5}, toArray(solution(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
        assert Arrays.equals(new int[]{1,2,3,6,9,8,7,4,5}, toArray(optimizedSolution(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
    }

    private static List<Integer> solution(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        /*
                0,0|0,1|0,2|0,3
                1,3|2,3|3,4
                3,3|2,2|2,1|2,0
                1,0|1,1|1,2
         */

        int row = 0;
        int col = 0;
        int total = matrix.length * matrix[0].length;

        int direction = 1;
        /*
        directions:
        1 - go right
        2 - go down
        3 - go left
        4 - go up
         */

        int leftBound = 0;
        int topBound = 1;
        int rightBound = matrix[0].length - 1;
        int bottomBound = matrix.length - 1;

        /*
                {1, 2,   3,     4,       5},
                {6, 7,   8,     9,       10},
                {11,12, 13,     14,      15},
                {16,17, 18,     19,      20},
                {21,22, 23,     24,      25}
         */

        while (total > 0) {
            res.add(matrix[row][col]);

            if (direction == 1 && col == rightBound) {
                rightBound--;
                direction = 2;
            } else if (direction == 2 && row == bottomBound) {
                bottomBound--;
                direction = 3;
            } else if (direction == 3 && col == leftBound) {
                leftBound++;
                direction = 4;
            } else if (direction == 4 && row == topBound) {
                topBound++;
                direction = 1;
            }

            switch (direction) {
                case 1: col++; break;
                case 2: row++; break;
                case 3: col--; break;
                case 4: row--; break;
            }
            total--;
        }

        return res;
    }

    private static List<Integer> optimizedSolution(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;

        int r1 = 0;
        int r2 = matrix.length - 1;
        int c1 = 0;
        int c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                res.add(matrix[r1][c]);
            }
            for (int r = r1 + 1; r <= r2; r++) {
                res.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) {
                    res.add(matrix[r2][c]);
                }
                for (int r = r2; r > r1; r--) {
                    res.add(matrix[r][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }

    private static int[] toArray(List<Integer> list) {
        int[] res = new int[list.size()];
        int index = 0;
        for (int n: list) {
            res[index++] = n;
        }
        return res;
    }

}
