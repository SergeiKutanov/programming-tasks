package com.sergeik.arrays;

import java.util.*;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
 * by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state:
 * live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors
 * (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 */
public class GameOfLife {

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        int[][] expected = new int[][] {
                {0,0,0},
                {1,0,1},
                {0,1,1},
                {0,1,0}
        };
        solution(board);
        assert compareMatrix(expected, board);
    }

    private static boolean compareMatrix(int[][] expected, int[][] result) {
        for (int row = 0; row < expected.length; row++) {
            for (int col = 0; col < expected[0].length; col++) {
                if (expected[row][col] != result[row][col])
                    return false;
            }
        }
        return true;
    }

    private static void solution(int[][] board) {
        List<List<Integer>> updatedCells = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int aliveNeighbours = getAliveNeighbours(row, col, board);
                int updatedState = board[row][col];
                if (updatedState == 1 && aliveNeighbours < 2) {
                    updatedState = 0;
                } else if (updatedState == 1 && aliveNeighbours > 3) {
                    updatedState = 0;
                } else if (updatedState == 0 && aliveNeighbours == 3) {
                    updatedState = 1;
                }
                if (updatedState != board[row][col]) {
                    updatedCells.add(Arrays.asList(row, col, updatedState));
                }
            }
        }
        for (List<Integer> coor : updatedCells) {
            board[coor.get(0)][coor.get(1)] = coor.get(2);
        }
    }

    private static int getAliveNeighbours(int row, int col, int[][] board) {
        int res = 0;

        int upperRow = row - 1;
        int leftCol = col - 1;
        int rightCol = col + 1;
        int lowerRow = row + 1;
        if (upperRow >= 0) {
            if (leftCol >= 0) {
                res += board[upperRow][leftCol];
            }
            res += board[upperRow][col];
            if (rightCol < board[0].length) {
                res += board[upperRow][rightCol];
            }
        }
        if (lowerRow < board.length) {
            if (leftCol >= 0) {
                res += board[lowerRow][leftCol];
            }
            res += board[lowerRow][col];
            if (rightCol < board[0].length) {
                res += board[lowerRow][rightCol];
            }
        }

        if (leftCol >= 0) {
            res += board[row][leftCol];
        }
        if (rightCol < board[0].length) {
            res += board[row][rightCol];
        }

        return res;
    }

}
