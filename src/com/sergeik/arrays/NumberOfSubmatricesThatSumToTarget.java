package com.sergeik.arrays;

/**
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is
 * different: for example, if x1 != x1'.
 */
public class NumberOfSubmatricesThatSumToTarget {

    public static void main(String[] args) {
        assert 0 == solution(new int[][] {
                {904}
        }, 0);
        assert 5 == solution(new int[][] {
                {1,-1},
                {-1,1}
        }, 0);
        assert 4 == solution(new int[][] {
                {0,1,0},
                {1,1,1},
                {0,1,0}
        }, 0);
    }

    private static int solution(int[][] matrix, int target) {
        int[][] pref = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < pref.length; i++) {
            for (int j = 1; j < pref[i].length; j++) {
                pref[i][j] = matrix[i - 1][j - 1] + pref[i - 1][j] + pref[i][j - 1] - pref[i - 1][j - 1];
            }
        }
        int res = 0;
        for (int i = 1; i < pref.length; i++) {
            for (int j = 1; j < pref[i].length; j++) {
                res += check(pref, target, i, j);
            }
        }
        return res;
    }

    private static int check(int[][] pref, int target, int r, int c) {
        int res = 0;
        for (int row = 0; row + r < pref.length; row++) {
            for (int col = 0; col + c < pref[r].length; col++) {
                int offsetRow = r + row, offsetCol = c + col;
                int sum = pref[offsetRow][offsetCol] - pref[r - 1][offsetCol] - pref[offsetRow][c - 1] + pref[r - 1][c - 1];
                if (sum == target)
                    res++;
            }
        }
        return res;
    }

}
