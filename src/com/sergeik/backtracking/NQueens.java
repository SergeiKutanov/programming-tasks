package com.sergeik.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
 * both indicate a queen and an empty space, respectively.
 */
public class NQueens {

    public static void main(String[] args) {
        solution(4);
    }

    private static List<List<String>> solution(int n) {
        List<List<String>> res = new LinkedList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(res, 0, board);
        return res;
    }

    private static void dfs(List<List<String>> res, int col, char[][] board) {
        if (col == board.length) {
            res.add(constructBoard(board));
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (validate(row, col, board)) {
                board[row][col] = 'Q';
                dfs(res, col + 1, board);
                board[row][col] = '.';
            }
        }
    }

    private static boolean validate(int x, int y, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'Q' && (x + j == y + i || x + y == j + i || x == i))
                    return false;
            }
        }
        return true;
    }

    private static List<String> constructBoard(char[][] board) {
        List<String> brd = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            brd.add(new String(board[i]));
        }
        return brd;
    }

}
