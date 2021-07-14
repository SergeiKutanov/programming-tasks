package com.sergeik.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * There are several squares being dropped onto the X-axis of a 2D plane.
 *
 * You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi] represents the ith
 * square with a side length of sideLengthi that is dropped with its left edge aligned with X-coordinate lefti.
 *
 * Each square is dropped one at a time from a height above any landed squares. It then falls downward
 * (negative Y direction) until it either lands on the top side of another square or on the X-axis.
 * A square brushing the left/right side of another square does not count as landing on it. Once it lands,
 * it freezes in place and cannot be moved.
 *
 * After each square is dropped, you must record the height of the current tallest stack of squares.
 *
 * Return an integer array ans where ans[i] represents the height described above after dropping the ith square.
 */
public class FallingSquares {

    public static void main(String[] args) {
        List<Integer> res, exp;

        res = solution(new int[][]{
                {1,2},
                {2,3},
                {6,1}
        });
        exp = Arrays.asList(2,5,5);
        for (int i = 0; i < exp.size(); i++)
            assert exp.get(i).equals(res.get(i));
    }

    private static List<Integer> solution(int[][] positions) {
        int[] qans = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int left = positions[i][0];
            int size = positions[i][1];
            int right = left + size;
            qans[i] += size;

            for (int j = i + 1; j < positions.length; j++) {
                int left2 = positions[j][0];
                int size2 = positions[j][1];
                int right2 = left2 + size2;
                if (left2 < right && left < right2) { //intersect
                    qans[j] = Math.max(qans[i], qans[j]);
                }
            }
        }

        List<Integer> res = new LinkedList<>();
        int cur = -1;
        for (int x: qans) {
            cur = Math.max(cur, x);
            res.add(cur);
        }
        return res;
    }

}
