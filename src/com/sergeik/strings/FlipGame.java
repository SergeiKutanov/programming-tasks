package com.sergeik.strings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip
 * two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the
 * other person will be the winner.
 *
 * Return all possible states of the string currentState after one valid move. You may return the answer in any order.
 * If there is no valid move, return an empty list [].
 */
public class FlipGame {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"--++", "+--+", "++--"},
                solution("++++").toArray()
        );
        assert Arrays.equals(
                new String[0],
                solution("+").toArray()
        );
    }

    private static List<String> solution(String currentState) {
        List<String> res = new LinkedList<>();
        dfs(0, currentState, res);
        return res;
    }

    private static void dfs(int start, String state, List<String> res) {
        if (start >= state.length() - 1)
            return;
        if (state.charAt(start) == '+' && state.charAt(start + 1) == '+') {
            res.add(state.substring(0, start) + "--" + state.substring(start + 2));
        }
        dfs(start + 1, state, res);
    }

}
