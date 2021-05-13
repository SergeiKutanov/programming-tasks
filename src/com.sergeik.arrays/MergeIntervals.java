package com.sergeik.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] res = solution(new int[][] {
                {1,3},
                {2,6},
                {4,7},
                {8,10},
                {15,18},
        });
        assert verify(new int[][] {
                {1,7},
                {8,10},
                {15,18}
        }, res);
    }

    private static boolean verify(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i]))
                return false;
        }
        return true;
    }

    private static int[][] solution(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new LinkedList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval: intervals) {
            if (interval[0] <= newInterval[1]) { //overlapping intervals, move the end
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else {
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }


}
