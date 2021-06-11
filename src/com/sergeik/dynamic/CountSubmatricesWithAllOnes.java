package com.sergeik.dynamic;


import java.util.Arrays;
import java.util.Stack;

/**
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 */
public class CountSubmatricesWithAllOnes {

    public static void main(String[] args) {
        assert 13 == solution(new int[][] {
                {1,0,1},
                {1,1,0},
                {1,1,0}
        });
    }

    private static int solution(int[][] mat) {
        int res = 0;
        int[] heights = new int[mat[0].length];
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                heights[c] = mat[r][c] == 0 ? 0 : (heights[c] + 1);
            }
            res += stackHelper(heights);
        }
        return res;
    }

    private static int stackHelper(int[] heights) {
        int[] sum = new int[heights.length];
        Stack<Integer> monoStack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i])
                monoStack.pop();

            if (!monoStack.isEmpty()) {
                int preIndex = monoStack.peek();
                sum[i] = sum[preIndex];
                sum[i] += heights[i] * (i - preIndex);
            } else {
                sum[i] = heights[i] * (i + 1);
            }

            monoStack.push(i);
        }

        return Arrays.stream(sum).sum();
    }

    private static int scannerSolution(int[][] mat) {
        int res = 0;
        int[] scanner = new int[mat[0].length];

        for (int h = 0; h < mat.length; h++) {
            Arrays.fill(scanner, 1);
            for (int l = h; l < mat.length; l++) {
                for (int c = 0; c < scanner.length; c++)
                    scanner[c] &= mat[l][c];
                res += countOneRow(scanner);
            }
        }
        return res;
    }

    private static int countOneRow(int[] row) {
        int length = 0, res = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1)
                length++;
            else
                length = 0;
            res += length;
        }
        return res;
    }

    private static int bruteSolution(int[][] mat) {
        int res = 0;
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                if (mat[r][c] > 0) {
                    res += count(mat, r, c);
                }
            }
        }
        return res;
    }

    private static int count(int[][] mat, int r, int c) {
        int res = 0;

        int bound = mat[0].length;
        for (int i = r; i < mat.length; i++) {
            for (int j = c; j < bound; j++) {
                if (mat[i][j] > 0)
                    res++;
                else
                    bound = j;
            }
        }

        return res;
    }

}
