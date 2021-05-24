package com.sergeik.backtracking;

import java.util.*;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        char[][] expected = new char[][] {
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','9'}
        };
        solution(board);
        for (int row = 0; row < expected.length; row++) {
            assert Arrays.equals(expected[row], board[row]);
        }
    }

    private static void solution(char[][] board) {
        Map<Integer, boolean[]> rowsSet = new HashMap<>();
        Map<Integer, boolean[]> colsSet = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            rowsSet.put(i, new boolean[10]);
            colsSet.put(i, new boolean[10]);
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] != '.') {
                    rowsSet.get(r)[board[r][c] - '0'] = true;
                    colsSet.get(c)[board[r][c] - '0'] = true;
                }
            }
        }
        solve(board, rowsSet, colsSet);
    }

    private static boolean solve(char[][] board, Map<Integer, boolean[]> rows, Map<Integer, boolean[]> cols) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++) {
                        if (isValid(board, r, c, ch, rows, cols)) {
                            board[r][c] = ch;
                            rows.get(r)[ch - '0'] = true;
                            cols.get(c)[ch - '0'] = true;
                            if (solve(board, rows, cols))
                                return true;
                            else {
                                board[r][c] = '.';
                                rows.get(r)[ch - '0'] = false;
                                cols.get(c)[ch - '0'] = false;
                            }

                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(char[][] board, int row, int col, char ch, Map<Integer, boolean[]> rows, Map<Integer, boolean[]> cols) {
        if (rows.get(row)[ch - '0'] == true)
            return false;
        if (cols.get(col)[ch - '0'] == true)
            return false;
        //check block
        for (int i = 0; i < board.length; i++) {
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == ch)
                return false;
        }
        return true;
    }

}
