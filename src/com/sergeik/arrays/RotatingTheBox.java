package com.sergeik.arrays;

import java.util.Arrays;

/**
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 *
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box
 * is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls
 * down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the
 * obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 */
public class RotatingTheBox {

    public static void main(String[] args) {
        char[][] box = new char[][] {
                {'#','#','*','.','*','.'},
                {'#','#','#','*','.','.'},
                {'#','#','#','.','#','.'}
        };
        /*
        0,0 - 2,0
        0,1 - 1,0
        0,2 - 0,0

        1,0 - 2,1
        1,1 - 1,1
        1,2 - 0,1
         */
        char[][] expected = new char[][] {
                {'.','#','#'},
                {'.','#','#'},
                {'#','#','*'},
                {'#','*','.'},
                {'#','.','*'},
                {'#','.','.'}
        };
        char[][] res = solution(box);
        for (int r = 0; r < expected.length; r++) {
            Arrays.equals(expected[r], res[r]);
        }
    }


    private static char[][] solution(char[][] box) {
        char[][] res = new char[box[0].length][box.length];

        int i, len;
        for (int row = 0; row < box.length; row++) {
            i = box[row].length - 1;
            len = box[row].length;
            while (i >= 0) {
                if (box[row][i] == '#') {
                    int j = i;
                    while (j < len - 1 && box[row][j + 1] == '.')
                        j++;
                    if (j != i) {
                        box[row][i] = '.';
                        box[row][j] = '#';
                    }
                }
                i--;
            }
        }

        int bRow = box.length - 1;
        int bCol = 0;
        for (int row = 0; row < res.length; row++) {
            for (int col = 0; col < res[row].length; col++) {
                res[row][col] = box[bRow--][bCol];
            }
            bRow = box.length - 1;
            bCol++;
        }

        return res;

    }

}
