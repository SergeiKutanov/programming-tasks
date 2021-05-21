package com.sergeik.graphs;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels
 * in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target.
 * You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 */
public class BusRoutes {

    public static void main(String[] args) {
        assert 2 == solution(new int[][]{
                {1,2,7},
                {3,6,7}
        }, 1, 6);
    }

    private static int solution(int[][] routes, int source, int target) {
        int routesLength =routes.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < routesLength; i++) {
            for (int j : routes[i]) {
                if (!map.containsKey(j))
                    map.put(j, new HashSet<>());
                map.get(j).add(i);
            }
        }

        Queue<int[]> bfs = new ArrayDeque<>();
        bfs.offer(new int[] {source, 0});
        Set<Integer> seen = new HashSet<>();
        seen.add(source);
        boolean[] seenRoutes = new boolean[routesLength];
        while (!bfs.isEmpty()) {
            int stop = bfs.peek()[0];
            int bus = bfs.peek()[1];
            bfs.poll();
            if (stop == target)
                return bus;
            for (int i: map.get(stop)) {
                if (seenRoutes[i])
                    continue;
                for (int j : routes[i]) {
                    if (!seen.contains(j)) {
                        seen.add(j);
                        bfs.offer(new int[] {j, bus + 1});
                    }
                }
                seenRoutes[i] = true;
            }
        }
        return -1;
    }

}
