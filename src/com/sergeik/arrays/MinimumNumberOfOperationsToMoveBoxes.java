package com.sergeik.arrays;

import java.util.Arrays;

/**
 * You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0'
 * if the ith box is empty, and '1' if it contains one ball.
 *
 * In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if
 * abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
 *
 * Return an array answer of size n, where answer[i] is the minimum number of operations needed to move
 * all the balls to the ith box.
 *
 * Each answer[i] is calculated considering the initial state of the boxes.
 */
public class MinimumNumberOfOperationsToMoveBoxes {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{11,8,5,4,3,4},
                solution("001011")
        );
    }

    private static int[] solution(String boxes) {
        int[] operations = new int[boxes.length()];
        for (int i = 0, op = 0, cnt = 0; i < boxes.length(); i++) {
            operations[i] += op;
            cnt += boxes.charAt(i) == '1' ? 1 : 0;
            op += cnt;
        }
        for (int i = boxes.length() - 1, op = 0, cnt = 0; i >= 0; i--) {
            operations[i] += op;
            cnt += boxes.charAt(i) == '1' ? 1: 0;
            op += cnt;
        }
        return operations;
    }

    private static int[] bruteSolution(String boxes) {
        int[] res = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++)
            res[i] = getNumberOfSteps(boxes, i);
        return res;
    }

    private static int getNumberOfSteps(String boxes, int dest) {
        int steps = 0;
        for (int i = 0; i < boxes.length(); i++) {
            if (boxes.charAt(i) == '1') {
                int diff = Math.abs(dest - i);
                steps += diff;
            }
        }
        return steps;
    }

}
