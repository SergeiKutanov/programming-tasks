package com.sergeik.trees;

import java.util.*;

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel
 * between two different cities (this network form a tree). Last year, The ministry of transport decided
 * to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number
 * of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    public static void main(String[] args) {
        assert 0 == solution(3, new int[][] {{1,0}, {2,0}});
        assert 2 == solution(5, new int[][] {{1,0}, {1,2}, {3,2}, {3,4}});
        assert 3 == solution(6, new int[][]{{0,1}, {1,3}, {2,3}, {4,0}, {4,5}});
    }

    private static int solution(int n, int[][] connections) {
        Map<Integer, List<int[]>> paths = new HashMap<>();
        for (int[] con: connections) {
            paths.computeIfAbsent(con[0], l -> new LinkedList<>());
            paths.computeIfAbsent(con[1], l -> new LinkedList<>());
            paths.get(con[0]).add(new int[] {con[1], 0}); //existing path
            paths.get(con[1]).add(new int[] {con[0], 1}); //reversed path
        }

        Stack<Integer> dfs = new Stack<>();
        dfs.push(0);
        boolean[] seen = new boolean[n];
        seen[0] = true;

        int res = 0;
        while (!dfs.isEmpty()) {
            int node = dfs.pop();
            for (int[] neigh: paths.get(node)) {
                if (!seen[neigh[0]]) {
                    if (neigh[1] == 0)
                        res++;
                    seen[neigh[0]] = true;
                    dfs.push(neigh[0]);
                }
            }
        }
        return res;
    }

}
