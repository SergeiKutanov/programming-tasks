package com.sergeik.arrays;

/**
 * On an 8 x 8 chessboard, there is exactly one white rook 'R' and some number of white bishops 'B', black pawns
 * 'p', and empty squares '.'.
 *
 * When the rook moves, it chooses one of four cardinal directions (north, east, south, or west), then moves in that
 * direction until it chooses to stop, reaches the edge of the board, captures a black pawn, or is blocked by a white
 * bishop. A rook is considered attacking a pawn if the rook can capture the pawn on the rook's turn. The number of
 * available captures for the white rook is the number of pawns that the rook is attacking.
 *
 * Return the number of available captures for the white rook.
 */
public class AvailableCaptresForRook {

    public static void main(String[] args) {
        assert 3 == solution(new char[][]{
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','R','.','.','.','p'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','p','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}
        });
    }

    private static int solution(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    int[][] dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
                    for (int[] dir: dirs) {
                        int x = i + dir[0], y = j + dir[1];
                        while (x >= 0 && x < board.length && y >= 0 && y < board[x].length) {
                            if (board[x][y] == 'p') {
                                res++;
                                break;
                            }
                            if (board[x][y] == 'B')
                                break;
                            x += dir[0]; y += dir[1];
                        }
                    }
                    return res;
                }
            }
        }

        return res;
    }

}
