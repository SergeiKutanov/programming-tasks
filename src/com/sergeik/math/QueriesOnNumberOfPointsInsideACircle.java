package com.sergeik.math;

import java.util.Arrays;

/**
 * You are given an array points where points[i] = [xi, yi] is the coordinates of the ith point on a 2D plane.
 * Multiple points can have the same coordinates.
 *
 * You are also given an array queries where queries[j] = [xj, yj, rj] describes a circle centered at (xj, yj)
 * with a radius of rj.
 *
 * For each query queries[j], compute the number of points inside the jth circle. Points on the border of the
 * circle are considered inside.
 *
 * Return an array answer, where answer[j] is the answer to the jth query.
 *
 *
 */
public class QueriesOnNumberOfPointsInsideACircle {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[]{11,7,8,2,7,11,13,10,10,14,3,3},
                solutionWithSort(
                        new int[][] {
                                {99,113},{150,165},{23,65},{175,154},{84,83},{24,59},{124,29},{19,97},{117,182},
                                {105,191},{83,117},{114,35},{0,111},{22,53}
                        },
                        new int[][] {
                                {105,191,155}, {114,35,94},{84,83,68},{175,154,28},{99,113,80},{175,154,177},
                                {175,154,181},{114,35,134},{22,53,105},{124,29,164},{6,99,39},{84,83,35}
                        }
                )
        );


        assert Arrays.equals(
                new int[]{3,2,2},
                solutionWithSort(
                        new int[][] {{1,3}, {3,3}, {5,3}, {2,2}},
                        new int[][] {{2,3,1}, {4,3,1}, {1,1,2}}
                )
        );
        assert Arrays.equals(
                new int[]{2,3,2,4},
                solutionWithSort(
                        new int[][] {{1,1}, {2,2}, {3,3}, {4,4}, {5,5}},
                        new int[][] {{1,2,2}, {2,2,2}, {4,3,2}, {4,3,3}}
                )
            );
    }

    /**
     * Euclidean distance d(p, q) = d^2 = (px - qx)^2 + (py - qy)^2
     * @param points
     * @param queries
     * @return
     */
    private static int[] solution(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            for (int[] p: points) {
                int d = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                if (d <= q[2] * q[2])
                    res[i]++;
            }
        }
        return res;
    }

    private static int findUpperBound(int[][] points, int bound) {
        int l = 0;
        int r = points.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (points[m][0] <= bound) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    private static int findLowerBound(int[][] points, int bound) {
        int l = 0;
        int r = points.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (points[m][0] >= bound) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ++r;
    }

    private static int[] findBoundaries(int[][] points, int lowerBound, int upperBound) {
        int lIndex;
        int rIndex;

        int l = 0;
        int r = points.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (points[m][0] >= lowerBound) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        lIndex = ++r;

        l = 0;
        r = points.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (points[m][0] <= upperBound) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        rIndex = l;

        return new int[]{lIndex, rIndex};
    }

    private static int[] solutionWithSort(int[][] points, int[][] queries) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int[] res = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int lowerBound = findLowerBound(points, q[0] - q[2]);
            int upperBound = findUpperBound(points, q[0] + q[2]);

            for (int j = lowerBound; j < upperBound; j++) {
                int[] p = points[j];
                int d = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                if (d <= q[2] * q[2])
                    res[i]++;
            }
        }

        return res;
    }

}
