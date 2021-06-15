package com.sergeik.sortsearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 *
 * You are also given some queries. Each query consists of two integers i and c, return the shortest
 * distance between the given index i and the target color c. If there is no solution return -1.
 */
public class ShortestDistanceToTargetColor {

    public static void main(String[] args) {
        List<Integer> exp, res;

        exp = Arrays.asList(-1);
        res = solution(new int[]{1,2}, new int[][] {{0,3}});
        for (int i = 0; i < exp.size(); i++)
            assert exp.get(i).equals(res.get(i));

        exp = Arrays.asList(3,0,3);
        res = solution(new int[]{1,1,2,1,3,2,2,3,3}, new int[][] {{1,3}, {2,2}, {6,1}});
        for (int i = 0; i < exp.size(); i++)
            assert exp.get(i).equals(res.get(i));
    }

    private static List<Integer> solution(int[] colors, int[][] queries) {
        TreeSet<Integer>[] dist = new TreeSet[4];
        for (int i = 1; i < 4; i++)
            dist[i] = new TreeSet();

        for (int i = 0; i < colors.length; i++) {
            dist[colors[i]].add(i);
        }

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < queries.length; i++) {
            Integer ceil = dist[queries[i][1]].ceiling(queries[i][0]);
            Integer floor = dist[queries[i][1]].floor(queries[i][0]);
            int distance = -1;
            if (ceil != null) {
                distance = Math.abs(ceil - queries[i][0]);
            }
            if (floor != null) {
                int floorDist = Math.abs(floor - queries[i][0]);
                distance = distance == -1 ? floorDist : Math.min(floorDist, distance);
            }
            res.add(distance);
        }
        return res;
    }

}
