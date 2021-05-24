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
        boolean[][] rowsSet = new boolean[10][10];
        boolean[][] colsSet = new boolean[10][10];
        boolean[][] blockSet = new boolean[10][10];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] != '.') {
                    int ch = board[r][c] - '0';
                    rowsSet[r][ch] = true;
                    colsSet[c][ch] = true;
                    int blockId = r / 3 * 3 + c / 3;
                    blockSet[blockId][ch] = true;
                }
            }
        }
        solve(board, rowsSet, colsSet, blockSet);
    }

    private static boolean solve(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] blocks) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++) {
                        if (isValid(r, c, ch, rows, cols, blocks)) {
                            board[r][c] = ch;
                            int idx = ch - '0';
                            rows[r][idx] = true;
                            cols[c][idx] = true;
                            int blockId = r / 3 * 3 + c / 3;
                            blocks[blockId][idx] = true;
                            if (solve(board, rows, cols, blocks))
                                return true;
                            else {
                                board[r][c] = '.';
                                rows[r][idx] = false;
                                cols[c][idx] = false;
                                blocks[blockId][idx] = false;
                            }

                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int row, int col, char ch, boolean[][] rows, boolean[][] cols, boolean[][] blocks) {
        int idx = ch - '0';
        if (rows[row][idx] == true)
            return false;
        if (cols[col][idx] == true)
            return false;
        if (blocks[row / 3 * 3 + col / 3][idx] == true)
            return false;
        return true;
    }

}
