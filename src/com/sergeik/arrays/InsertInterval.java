package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 */
public class InsertInterval {

    public static void main(String[] args) {
        int[][] res = solution(
                new int[][] {
                        {1,2},{3,5},{6,7},{8,10},{12,16}
                },
                new int[] {4,8}
        );
        int[][] exp = new int[][] {
                {1,2},
                {3,10},
                {12,16}
        };
        assert verify(exp, res);

        assert verify(
                new int[][] {{1,5}, {6,9}},
                solution(
                        new int[][]{
                                {1,3},{6,9}
                        },
                        new int[]{2,5}
                )
        );

        assert verify(
                new int[][] {{5,7}},
                solution(
                        new int[][]{},
                        new int[]{5,7}
                )
        );

        assert verify(
                new int[][] {{1,7}},
                solution(
                        new int[][]{{1,5}},
                        new int[]{2,7}
                )
        );
    }

    private static boolean verify(int[][] exp, int[][] res) {
        if (exp.length != res.length)
            return false;
        for (int i = 0; i < exp.length; i++) {
            int[] i1 = exp[i];
            int[] i2 = res[i];
            if (i1[0] != i2[0] || i1[1] != i2[1])
                return false;
        }
        return true;
    }

    private static int[][] solution(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] {newInterval};
        List<int[]> res = new LinkedList<>();
        int i = 0;
        while (i < intervals.length && !overlap(intervals[i], newInterval)) {
            res.add(intervals[i++]);
        }

        while (i < intervals.length && overlap(newInterval, intervals[i])) {
            newInterval = merge(newInterval, intervals[i++]);
        }
        res.add(newInterval);

        while (i < intervals.length) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[0][0]);
    }

    private static int[] merge(int[] i1, int[] i2) {
        int start = Math.min(i1[0], i2[0]);
        int end = Math.max(i1[1], i2[1]);
        return new int[]{start, end};
    }

    private static boolean overlap(int[] i1, int[] i2) {
        return  (i2[0] <= i1[1]);
    }

}
