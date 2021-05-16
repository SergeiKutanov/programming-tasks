package com.sergeik.strings;

/**
 * You are given coordinates, a string that represents the coordinates of a square of the chessboard.
 * Below is a chessboard for your reference.
 *
 *
 *
 * Return true if the square is white, and false if the square is black.
 *
 * The coordinate will always represent a valid chessboard square.
 * The coordinate will always have the letter first, and the number second.
 */
public class DetermineColorOfChessboardSquare {

    public static void main(String[] args) {
        assert !solution("a1");
        assert solution("h3");
        assert !solution("c7");
    }

    private static boolean solution(String coordinates) {
        return (coordinates.charAt(0) - 'a' + Integer.valueOf(coordinates.charAt(1))) % 2 == 0;
    }

}
