package com.sergeik.graphs;

import java.util.*;

/**
 * There are n cities numbered from 1 to n. You are given an array edges of size n-1, where edges[i] = [ui, vi]
 * represents a bidirectional edge between cities ui and vi. There exists a unique path between each pair of cities.
 * In other words, the cities form a tree.
 *
 * A subtree is a subset of cities where every city is reachable from every other city in the subset, where the path
 * between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in
 * one subtree that is not present in the other.
 *
 * For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the
 * subtree is equal to d.
 *
 * Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum
 * distance between any two cities is equal to d.
 *
 * Notice that the distance between the two cities is the number of edges in the path between them.
 */
public class CountSubtreesWithMaxDistanceBetweenCities {

    private static int max = 0;

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {3,4,0},
                solution(4, new int[][] {
                        {1,2}, {2,3}, {2,4}
                })
        );
    }

    private static int[] solution(int n, int[][] edges) {
        int[] res = new int[n - 1];
        //build graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        for (int i = 0; i < 1 << n; i++) {
            List<Integer> subTree = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0)
                    subTree.add(j + 1);
            }

            //check if tree is valid
            int edgeNumber = 0;
            for (int[] edge: edges) {
                if (subTree.contains(edge[0]) && subTree.contains(edge[1]))
                    edgeNumber++;
            }
            //not a valid tree
            if (edgeNumber < 1 || subTree.size() != edgeNumber + 1)
                continue;

            //got the valid tree, now let's find max diameter
            max = 0;
            Set<Integer> seen = new HashSet<>();
            seen.add(subTree.get(0));
            dfs(subTree.get(0), seen, graph, subTree);
            res[max - 1]++;
        }

        return res;
    }

    private static int dfs(int current, Set<Integer> seen, Map<Integer, List<Integer>> graph, List<Integer> subTree) {
        int max1 = 0, max2 = 0;
        for (int neighbour: graph.get(current)) {
            if (!seen.contains(neighbour) && subTree.contains(neighbour)) {
                seen.add(neighbour);
                int depth = dfs(neighbour, seen, graph, subTree);
                if (depth > max1) {
                    max2 = max1;
                    max1 = depth;
                } else if (depth > max2)
                    max2 = depth;
            }
        }
        max = Math.max(max, max1 + max2);
        return max1 + 1;
    }

}
