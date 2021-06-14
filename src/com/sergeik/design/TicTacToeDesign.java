package com.sergeik.design;

import java.util.*;

/**
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col)
 * of the board. The move is guaranteed to be a valid move.
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 */
public class TicTacToeDesign {

    public static void main(String[] args) {
        TicTacToe ticTacToe;

        ticTacToe = new TicTacToe(3);
        assert 0 == ticTacToe.move(1,1,1);
        assert 0 == ticTacToe.move(0,1,2);
        assert 0 == ticTacToe.move(0,0,1);
        assert 0 == ticTacToe.move(2,2,2);
        assert 0 == ticTacToe.move(0,2,1);
        assert 0 == ticTacToe.move(2,0,2);
        assert 0 == ticTacToe.move(1,0,1);
        assert 2 == ticTacToe.move(2,1,2);

        ticTacToe = new TicTacToe(2);
        assert 0 == ticTacToe.move(0,1,1);
        assert 0 == ticTacToe.move(1,1,2);
        assert 1 == ticTacToe.move(1,0,1);


        ticTacToe = new TicTacToe(3);
        assert 0 == ticTacToe.move(0,0,1);
        assert 0 == ticTacToe.move(0,2,2);
        assert 0 == ticTacToe.move(2,2,1);
        assert 0 == ticTacToe.move(1,1,2);
        assert 0 == ticTacToe.move(2,0,1);
        assert 0 == ticTacToe.move(1,0,2);
        assert 1 == ticTacToe.move(2,1,1);
    }

    static class TicTacToeWithLists {

        Deque<Integer>[] rows, cols;
        Deque<Integer> diag, antiDiag;

        /** Initialize your data structure here. */
        public TicTacToeWithLists(int n) {
            rows = new LinkedList[n];
            cols = new LinkedList[n];
            diag = new LinkedList<>();
            antiDiag = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                rows[i] = new LinkedList<>();
                cols[i] = new LinkedList<>();
            }
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            place(rows[row], player);
            place(cols[col], player);

            if (row == col)
                place(diag, player);
            if (row + col == rows.length - 1)
                place(antiDiag, player);

            return checkBoard(row, col, player);
        }

        private static void place(Deque<Integer> deque, int player) {
            if (deque.size() == 0)
                deque.addFirst(player);
            else if (deque.peekFirst() == player)
                deque.addFirst(player);
            else
                deque.addLast(player);
        }

        private int checkBoard(int row, int col, int player) {
            if (rows[row].size() == rows.length && rows[row].peekFirst() == rows[row].peekLast())
                return player;
            if (cols[col].size() == cols.length && cols[col].peekFirst() == cols[col].peekLast())
                return player;
            if (diag.size() == rows.length && diag.peekLast() == diag.peekFirst())
                return diag.peekFirst();
            if (antiDiag.size() == rows.length && antiDiag.peekFirst() == antiDiag.peekLast())
                return antiDiag.peekFirst();
            return 0;
        }
    }

    static class TicTacToe {

        int[] rows, cols;
        int diag, antiDiag;

        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        public int move(int row, int col, int player) {
            int mark = player == 1 ? 1 : -1;
            rows[row] += mark;
            cols[col] += mark;
            if (row == col)
                diag += mark;
            if (row + col == rows.length - 1)
                antiDiag += mark;
            if (Math.abs(rows[row]) == rows.length ||
                Math.abs(cols[col]) == cols.length ||
                Math.abs(diag) == rows.length ||
                Math.abs(antiDiag) == rows.length)
                return player;
            return 0;
        }

    }

}
