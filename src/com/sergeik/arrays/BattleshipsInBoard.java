package com.sergeik.arrays;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the
 * battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of
 * the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one
 * horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 */
public class BattleshipsInBoard {

    public static void main(String[] args) {
        assert 2 == solution(new char[][] {
                {'X', '.', 'X'},
                {'.', '.', 'X'}
        });
    }

    private static int solution(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') continue;
                if (j > 0 && board[i][j - 1] == 'X') continue;
                if (i > 0 && board[i - 1][j] == 'X') continue;
                res++;
            }
        }
        return res;
    }

}
