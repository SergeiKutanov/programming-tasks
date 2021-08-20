package com.sergeik.graphs;

import java.util.*;

/**
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists
 * of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved.
 * If it is impossible for the state of the board to be solved, return -1.
 */
public class SlidingPuzzle {

    public static void main(String[] args) {
        assert -1 == solution(new int[][] {
                {1,2,3},
                {5,4,0}
        });
        assert 1 == solution(new int[][] {
                {1,2,3},
                {4,0,5}
        });
        assert 5 == solution(new int[][] {
                {4,1,2},
                {5,0,3}
        });
    }

    private static int solution(int[][] board) {
        Set<String> keysSeen = new HashSet<>();
        Queue<int[][]> queue = new LinkedList<>();
        queue.add(board);
        String cKey = buildKey(board);
        String finalKey = "[1, 2, 3][4, 5, 0]";
        if (cKey.equals(finalKey))
            return 0;
        int[][] dirs = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};

        int res = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[][] cBoard = queue.poll();

                int x = 0, y = 0;
                for (int i = 0; i < cBoard.length; i++)
                    for (int j = 0; j < cBoard[i].length; j++) {
                        if (cBoard[i][j] == 0) {
                            x = i; y = j;
                            break;
                        }
                    }

                for (int[] dir: dirs) {
                    int xd = x + dir[0], yd = y + dir[1];
                    if (xd >= 0 && xd < cBoard.length && yd >= 0 && yd < cBoard[xd].length) {
                        int[][] nextBoard = copy(cBoard);
                        nextBoard[x][y] = nextBoard[xd][yd];
                        nextBoard[xd][yd] = 0;
                        String key = buildKey(nextBoard);
                        if (key.equals(finalKey))
                            return res + 1;
                        if (!keysSeen.contains(key)) {
                            queue.add(nextBoard);
                            keysSeen.add(key);
                        }
                    }
                }
            }
            res++;
        }

        return -1;
    }

    private static int[][] copy(int[][] board) {
        int[][] nBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                nBoard[i][j] = board[i][j];
        return nBoard;
    }

    private static String buildKey(int[][] board) {
        return Arrays.toString(board[0]) + Arrays.toString(board[1]);
    }


}
