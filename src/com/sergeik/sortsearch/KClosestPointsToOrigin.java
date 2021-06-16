package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][] exp, res;

        res = solution(new int[][]{{1,3},{-2,2}},1);
        exp = new int[][] {{-2,2}};
        for (int i = 0; i < exp.length; i++)
            assert Arrays.equals(exp[i], res[i]);

        res = solution(new int[][]{{3,3},{5,-1},{-2,4}}, 2);
        exp = new int[][] {{3,3}, {-2,4}};
        for (int i = 0; i < exp.length; i++)
            assert Arrays.equals(exp[i], res[i]);
    }

    private static int[][] solution(int[][] points, int k) {
        Queue<int[]> heap = new PriorityQueue<>((a,b) -> {
            double aDist = Math.sqrt(a[0] * a[0] + a[1] * a[1]);
            double bDist = Math.sqrt(b[0] * b[0] + b[1] * b[1]);
            return Double.compare(aDist, bDist);
        });
        for (int[] p: points)
            heap.offer(p);
        int size = Math.min(k, heap.size());
        int[][] res = new int[size][2];
        int idx = 0;
        while (size-- > 0)
            res[idx++] = heap.poll();
        return res;
    }

}
