package com.sergeik.strings;

/**
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip
 * two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other
 * person will be the winner.
 *
 * Return true if the starting player can guarantee a win, and false otherwise.
 */
public class FlipGameII {

    public static void main(String[] args) {
        assert !canWin("++++++-++++++");
        assert !canWin("++++--++++");
        assert !canWin("+++++++++");
        assert canWin("++++");
        assert !canWin("+");
    }

    public static boolean canWin(String s) {
        for (int i= -1; (i = s.indexOf("++", i + 1)) >= 0; )
            if (!canWin(s.substring(0, i) + "-" + s.substring(i + 2)))
                return true;
        return false;
    }

}
