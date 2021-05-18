package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 */
public class SpiralMatrixII {

    enum DIR {RIGHT, DOWN, LEFT, UP};

    public static void main(String[] args) {

        assert verify(new int[][] {
                {1}
        }, solution(1));

        assert verify(new int[][] {
            {1,2,3},
            {8,9,4},
            {7,6,5}
        }, solution(3));

        assert verify(new int[][] {
                {1,2,3,4},
                {12,13,14,5},
                {11,16,15,6},
                {10,9,8,7}
        }, solution(4));
    }

    private static int[][] solution(int n) {
        int[][] m = new int[n][n];
        DIR dir = DIR.RIGHT;

        int[] cell = new int[]{0, 0};
        int val = 1;
        int leftBound = 0;
        int rightBound = n - 1;
        int upperBound = 0;
        int downBound = n - 1;
        while (val <= n * n) {
            m[cell[0]][cell[1]] = val++;
            switch (dir) {
                case RIGHT:
                    if (cell[1] == rightBound) {
                        upperBound++;
                        dir = DIR.DOWN;
                        cell[0]++;
                    } else {
                        cell[1]++;
                    }
                    break;
                case DOWN:
                    if (cell[0] == downBound) {
                        rightBound--;
                        dir = DIR.LEFT;
                        cell[1]--;
                    } else {
                        cell[0]++;
                    }
                    break;
                case LEFT:
                    if (cell[1] == leftBound) {
                        downBound--;
                        dir = DIR.UP;
                        cell[0]--;
                    } else {
                        cell[1]--;
                    }
                    break;
                case UP:
                    if (cell[0] == upperBound) {
                        leftBound++;
                        dir = DIR.RIGHT;
                        cell[1]++;
                    } else {
                        cell[0]--;
                    }
                    break;
            }
        }
        return m;
    }

    private static boolean verify(int[][] m1, int[][] m2) {
        if (m1.length != m2.length)
            return false;
        for (int i = 0; i < m1.length; i++) {
            if (!Arrays.equals(m1[i], m2[i]))
                return false;
        }
        return true;
    }

}
