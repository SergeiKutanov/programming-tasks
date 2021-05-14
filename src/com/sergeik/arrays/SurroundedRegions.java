package com.sergeik.arrays;

import java.util.*;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solution(board);
        assert compareBoards(
                new char[][] {
                        {'X', 'X', 'X', 'X'},
                        {'X', 'X', 'X', 'X'},
                        {'X', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'X'}
                },
                board
        );

        board = new char[][]{
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}
        };
        solution(board);
        //[["O","X","X","O","X"],["X","X","X","X","O"],["X","X","X","O","X"],["O","X","O","O","O"],["X","X","O","X","O"]]
        assert compareBoards(
                new char[][] {
                        {'O', 'X', 'X', 'O', 'X'},
                        {'X', 'X', 'X', 'X', 'O'},
                        {'X', 'X', 'X', 'O', 'X'},
                        {'O', 'X', 'O', 'O', 'O'},
                        {'X', 'X', 'O', 'X', 'O'}
                },
                board
        );

        board = new char[][] {
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'X'}
        };

        solution(board);
        assert compareBoards(
                new char[][] {
                        {'X', 'X', 'O', 'X', 'X'},
                        {'X', 'O', 'O', 'O', 'X'},
                        {'X', 'X', 'O', 'X', 'X'},
                        {'X', 'O', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'O', 'X'}
                },
                board
        );
    }

    private static void solution(char[][] board) {
        int nRows = board.length;
        if (nRows < 2)
            return;
        int nCols = board[0].length;
        if (nCols < 2)
            return;

        Queue<Point> queue = new LinkedList<>();

        //mark left and right edges
        for (int row = 0; row < nRows; row++) {
            if (board[row][0] == 'O') {
                board[row][0] = '+';
                queue.add(new Point(row, 0));
            }
            if (board[row][nCols - 1] == 'O') {
                board[row][nCols - 1] = '+';
                queue.add(new Point(row, nCols - 1));
            }
        }

        //mark top and bottom
        for (int col = 0; col < nCols; col++) {
            if (board[0][col] == 'O') {
                board[0][col] = '+';
                queue.add(new Point(0, col));
            }
            if (board[nRows - 1][col] == 'O') {
                board[nRows - 1][col] = '+';
                queue.add(new Point(nRows - 1, col));
            }
        }

        //DFS and mark neighbouring cells
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int row = p.x;
            int col = p.y;
            //mark cell above
            if (row - 1 > 0 && board[row - 1][col] == 'O') {
                board[row - 1][col] = '+';
                queue.add(new Point(row - 1, col));
            }
            //mark cell below
            if (row + 1 < nRows && board[row + 1][col] == 'O') {
                board[row + 1][col] = '+';
                queue.add(new Point(row + 1, col));
            }
            //mark left cell
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '+';
                queue.add(new Point(row, col - 1));
            }
            //mark right cell
            if (col + 1 < nCols && board[row][col + 1] == 'O') {
                board[row][col + 1] = '+';
                queue.add(new Point(row, col + 1));
            }
        }

        //update cells
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                if (board[row][col] == 'O')
                    board[row][col] = 'X';
                if (board[row][col] == '+')
                    board[row][col] = 'O';
            }
        }
    }

    private static boolean compareBoards(char[][] b1, char[][] b2) {
        for (int row = 0; row < b1.length; row++) {
            for (int col = 0; col < b1[row].length; col++) {
                if (b1[row][col] != b2[row][col])
                    return false;
            }
        }
        return true;
    }

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
