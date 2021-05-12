package com.sergeik.backtracking;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assert solution(board, "ABCCED");

        board = new char[][] {
                {'a'}
        };
        assert solution(board, "a");
    }

    private static boolean solution(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (attempt(board, word.toCharArray(), r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean attempt(char[][] board, char[] word, int r, int c, int i) {
        if (word.length == i)
            return true;
        if (r < 0 || r == board.length || c < 0 || c == board[0].length)
            return false;
        if (board[r][c] != word[i])
            return false;
        char ch = board[r][c];
        board[r][c] = '-';
        int[][] dirs = new int[][] {
                {0,1},
                {0,-1},
                {1, 0},
                {-1, 0},
        };
        for (int[] dir: dirs) {
            int newR = r + dir[0];
            int newC = c + dir[1];
            if (attempt(board, word, newR, newC, i + 1)) {
                return true;
            }
        }
        board[r][c] = ch;
        return false;
    }

}
