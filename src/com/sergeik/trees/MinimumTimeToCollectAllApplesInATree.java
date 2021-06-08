package com.sergeik.trees;

import java.util.*;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
 * You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend
 * to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists
 * an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple,
 * where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 */
public class MinimumTimeToCollectAllApplesInATree {

    public static void main(String[] args) {

        assert 2 == solution(
                7,
                new int[][] {{0,1},{0,2},{2,3},{1,4},{4,5},{4,6}},
                Arrays.asList(false, false, true, false, false, false, false)
        );

        assert 4 == solution(
                4,
                new int[][] {{0,2}, {0,3}, {1,2}},
                Arrays.asList(false, true, false, false)
        );

        assert 6 == solution(
                4,
                new int[][] {{0,1},{1,2},{0,3}},
                Arrays.asList(true, true, true, true)
        );

        assert 0 == solution(
                7,
                new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},
                Arrays.asList(false,false,false,false,false,false,false)
        );

        assert 6 == solution(
                7,
                new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},
                Arrays.asList(false,false,true,false,false,true,false)
        );

        assert 8 == solution(
                7,
                new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}},
                Arrays.asList(false,false,true,false,true,true,false)
            );
    }

    private static int solution(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> paths = new HashMap<>();
        for (int[] edge: edges) {
            paths.computeIfAbsent(edge[0], l -> new LinkedList<>());
            paths.computeIfAbsent(edge[1], l -> new LinkedList<>());
            paths.get(edge[0]).add(edge[1]);
            paths.get(edge[1]).add(edge[0]);
        }
        boolean[] apples = new boolean[hasApple.size()];
        for (int i = 0; i < hasApple.size(); i++)
            apples[i] = hasApple.get(i);

        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int res = dfs(paths, 0, apples, seen);
        return res;
    }

    private static int dfs(Map<Integer, List<Integer>> paths, int start, boolean[] apples, Set<Integer> seen) {
        int count = 0;
        if (paths.containsKey(start)) {
            for (int n: paths.get(start)) {
                if (!seen.contains(n)) {
                    seen.add(n);
                    count += dfs(paths, n, apples, seen);
                }
            }
        }
        if ((apples[start] || count > 0) && start > 0)
            count += 2;
        return count;
    }

}
