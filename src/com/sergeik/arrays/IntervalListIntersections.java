package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi]
 * and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as
 * a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 */
public class IntervalListIntersections {

    public static void main(String[] args) {
        int[][] res, exp;
        exp = new int[][] {{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}};
        res = solution(new int[][]{{0,2},{5,10},{13,23},{24,25}}, new int[][]{{1,5},{8,12},{15,24},{25,26}});
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int[][] firstList, int[][] secondList) {
        List<int[]> res = new LinkedList<>();
        int left = 0, right = 0;
        while (left < firstList.length && right < secondList.length) {
            while (right < secondList.length && secondList[right][1] < firstList[left][0])
                right++;
            while (right < secondList.length && secondList[right][0] <= firstList[left][1]) {
                int[] interval = new int[] {
                        Math.max(firstList[left][0], secondList[right][0]),
                        Math.min(firstList[left][1], secondList[right][1])
                };
                res.add(interval);
                right++;
            }
            left++;
            right -= right > 0 ? 1 : 0;
        }
        return res.toArray(new int[res.size()][2]);
    }

    private static int[][] loopSolution(int[][] firstList, int[][] secondList) {
        List<int[]> res = new LinkedList<>();
        int item2Idx = 0;
        for (int[] item1: firstList) {
            while (item2Idx < secondList.length && secondList[item2Idx][1] < item1[0])
                item2Idx++;
            while (item2Idx < secondList.length && secondList[item2Idx][0] <= item1[1]) {
                int[] interval = new int[] {
                        Math.max(item1[0], secondList[item2Idx][0]),
                        Math.min(item1[1], secondList[item2Idx][1])
                };
                res.add(interval);
                item2Idx++;
            }
            item2Idx -= item2Idx > 0 ? 1 : 0;
        }
        return res.toArray(new int[res.size()][2]);
    }

}
