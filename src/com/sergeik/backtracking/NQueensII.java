package com.sergeik.backtracking;

/**
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 */
public class NQueensII {

    private static int counter = 0;

    public static void main(String[] args) {
        assert 2 == solution(4);
    }

    private static int solution(int n) {
        counter = 0;
        boolean[][] board = new boolean[n][n];
        dfs(board, 0);
        return counter;
    }

    private static void dfs(boolean[][] board, int col) {
        if (col == board.length) {
            counter++;
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (validate(board, row, col)) {
                board[row][col] = true;
                dfs(board, col + 1);
                board[row][col] = false;
            }
        }
    }

    private static boolean validate(boolean[][] board, int row, int col) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < col; y++) {
                if (board[x][y] && (x == row || y == col || x + col == y + row || x + y == col + row))
                    return false;
            }
        }
        return true;
    }

}
