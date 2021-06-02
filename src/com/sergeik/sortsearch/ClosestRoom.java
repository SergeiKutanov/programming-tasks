package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms
 * where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei.
 * Each roomIdi is guaranteed to be unique.
 *
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej].
 * The answer to the jth query is the room number id of a room such that:
 *
 * The room has a size of at least minSizej, and
 * abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id.
 * If there is no such room, the answer is -1.
 *
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 */
public class ClosestRoom {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[]{12,6,6,12,12,12,6,6,6,12},
                solution(
                        new int[][]{{7,14},{11,6},{3,1},{9,4},{14,14},{17,11},{22,13},{6,25},{12,22},{21,9}},
                        new int[][]{{21,17},{4,6},{17,25},{15,18},{17,16},{18,16},{8,17},{6,7},{9,22},{17,18}}
                    )
        );

        assert Arrays.equals(
                new int[]{3, -1, 3},
                solution(new int[][] {
                        {2,2},
                        {1,2},
                        {3,2}
                }, new int[][] {
                        {3,1},
                        {3,3},
                        {5,2}
                })
        );

        assert Arrays.equals(
                new int[] {2,1,3},
                solution(new int[][] {
                        {1,4},
                        {2,3},
                        {3,5},
                        {4,1},
                        {5,2}
                }, new int[][] {
                        {2,3},
                        {2,4},
                        {2,5}
                })
        );
    }

    private static int[] solution(int[][] rooms, int[][] queries) {
        int[] res = new int[queries.length];
        int[][] qs = new int[queries.length][];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            qs[i] = new int[]{q[0], q[1], i};
        }
        Arrays.sort(rooms, (a,b) -> b[1] - a[1]);
        Arrays.sort(qs, (a, b) -> b[1] - a[1]);

        int id = 0;
        TreeSet<Integer> ids = new TreeSet<>();
        for (int[] q: qs) {
            for (; id < rooms.length && rooms[id][1] >= q[1]; id++) {
                ids.add(rooms[id][0]);
            }
            Integer ansFloor = ids.floor(q[0]);
            Integer ansCeil = ids.ceiling(q[0]);
            if (ansFloor != null && ansCeil != null) {
                res[q[2]] = q[0] - ansFloor <= ansCeil - q[0] ? ansFloor : ansCeil;
            } else if (ansFloor == null && ansCeil == null) {
                res[q[2]] = -1;
            } else {
                res[q[2]] = ansCeil == null ? ansFloor : ansCeil;
            }
        }
        return res;
    }

    private static int[] heapSolution(int[][] rooms, int[][] queries) {
        int[] res = new int[queries.length];
        Arrays.sort(rooms, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int firstMatch = solutionGreater(rooms, q[1] - 1);
            int bestMatch = -1;
            int diff = Integer.MAX_VALUE;
            for (; firstMatch < rooms.length; firstMatch++) {
                int cDiff = Math.abs(q[0] - rooms[firstMatch][0]);
                if (cDiff == 0) {
                    bestMatch = rooms[firstMatch][0];
                    break;
                }
                if (cDiff == diff) {
                    if (bestMatch > rooms[firstMatch][0]) {
                        bestMatch = rooms[firstMatch][0];
                    }
                } else if (cDiff < diff) {
                    bestMatch = rooms[firstMatch][0];
                    diff = cDiff;
                }
            }
            res[i] = bestMatch;
        }

        return res;
    }

    private static int solutionGreater(int[][] rooms, int n) {
        int l = 0;
        int r = rooms.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (rooms[m][1] <= n) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

}
