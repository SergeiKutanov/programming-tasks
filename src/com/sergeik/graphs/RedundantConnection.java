package com.sergeik.graphs;

import java.util.Arrays;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
 * The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 * The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an
 * edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
 * return the answer that occurs last in the input.
 */
public class RedundantConnection {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[] {1,4},
                solution(new int[][] {{1,2},{2,3},{3,4},{1,4},{1,5}})
        );
        assert Arrays.equals(
                new int[] {2,3},
                solution(new int[][] {{1,2},{1,3},{2,3}})
        );
    }

    private static int[] solution(int[][] edges) {
        int[] groups = new int[edges.length + 1];
        for (int i = 1; i < groups.length; i++)
            groups[i] = i;

        int[] res = new int[0];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int p1 = findParent(edge[0], groups);
            int p2 = findParent(edge[1], groups);
            if (p1 != p2) {
                groups[p1] = p2;
            } else {
                res = edge;
            }
        }
        return res;
    }

    private static int findParent(int node, int[] groups) {
        while (node != groups[node])
            node = groups[node];
        return node;
    }

}
