package com.sergeik.graphs;

/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates
 * that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 */
public class NumberOfConnectedComponents {

    public static void main(String[] args) {
        assert 3 == solution(10, new int[][] {
                {5,8},{3,5},{1,9},{4,5},{0,2},{7,8},{4,9}});
        assert 1 == solution(4, new int[][]{{0,1},{2,3},{1,2}});
        assert 2 == solution(5, new int[][]{{0,1},{1,2},{3,4}});
        assert 1 == solution(5, new int[][]{{0,1},{1,2},{2,3},{3,4}});
    }

    private static int solution(int n, int[][] edges) {
        int[] parents = new int[n];
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }

        for (int[] edge: edges) {
            int parentLeft = findParent(parents, edge[0]);
            int parentRight = findParent(parents, edge[1]);
            if (parentLeft != parentRight) {
                if (sizes[parentLeft] > sizes[parentRight]) {
                    sizes[parentLeft] += sizes[parentRight];
                    parents[parentRight] = parentLeft;
                } else {
                    sizes[parentRight] += sizes[parentLeft];
                    parents[parentLeft] = parentRight;
                }
                n--;
            }
        }
        return n;
    }

    private static int findParent(int[] parents, int node) {
        int parent = node;
        while (parent != parents[parent]) {
            parent = parents[parent];
        }
        //compress path
        while (parent != parents[node]) {
            node = parents[parent];
            parents[node] = parent;
        }
        return parent;
    }

}
