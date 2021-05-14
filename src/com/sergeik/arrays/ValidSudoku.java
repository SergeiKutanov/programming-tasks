package com.sergeik.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 */
public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        assert bruteSolution(board);

        board = new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        assert !bruteSolution(board);

    }

    public static boolean bruteSolution(char[][] board) {
        boolean res = true;
        //validate rows
        for (int row = 0; row < board.length; row++) {
            res = validateRow(board, row);
            if (!res) return res;
        }
        //validate columns
        for (int col = 0; col < board.length; col++) {
            res = validateColumn(board, col);
            if (!res) return res;
        }
        //validate sub boxes
        for (int row = 0; row < board.length; row += 3) {
            for (int col = 0; col < board.length; col +=3) {
                res = validateThreeByThree(board, row, col);
                if (!res) return res;
            }
        }
        return res;
    }

    private static boolean validateThreeByThree(char[][] board, int row, int col) {
        Set<Integer> chars = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int charVal = board[row + i][col + j] - '0';
                if (charVal > 0 && charVal <= 9) {
                    if (chars.contains(charVal)) {
                        return false;
                    }
                    chars.add(charVal);
                }
            }
        }
        return true;
    }

    private static boolean validateRow(char[][] board, int row) {
        Set<Integer> chars = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            int charVal = board[row][i] - '0';
            if (charVal > 0 && charVal <= 9) {
                if (chars.contains(charVal)) {
                    return false;
                }
                chars.add(charVal);
            }
        }
        return true;
    }

    private static boolean validateColumn(char[][] board, int col) {
        Set<Integer> chars = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            int charVal = board[i][col] - '0';
            if (charVal > 0 && charVal <= 9) {
                if (chars.contains(charVal)) {
                    return false;
                }
                chars.add(charVal);
            }
        }
        return true;
    }

}
