package com.sergeik.graphs;

import java.util.*;

/**
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that
 * the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti]
 * denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 *
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start
 * and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 *
 * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x)
 * denote the shortest distance of a path between node n and node x. A restricted path is a path that
 * also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 *
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large,
 * return it modulo 109 + 7.
 */
public class NumberOfRestrictedPathsFromFirstToLastNode {

    public static void main(String[] args) {
        assert 3 == solution(5, new int[][] {
                {1,2,3},
                {1,3,3},
                {2,3,1},
                {1,4,2},
                {5,2,2},
                {3,5,1},
                {5,4,10}
        });
    }

    private static int solution(int n, int[][] edges) {
        if (n == 1)
            return 0;
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new LinkedList<>();
        for (int[] edge: edges) {
            graph[edge[0]].add(new int[]{edge[2], edge[1]});
            graph[edge[1]].add(new int[]{edge[2], edge[0]});
        }
        int[] dist = dijkstra(n, graph);
        return dfs(1, n, graph, dist, new Integer[n + 1]);
    }

    private static int[] dijkstra(int n, List<int[]>[] graph) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, n});
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int d = top[0];
            int u = top[1];
            if (d != dist[u])
                continue;
            for (int[] nei: graph[u]) {
                int w = nei[0], v = nei[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    minHeap.offer(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    private static int dfs(int src, int n, List<int[]>[] graph, int[] dist, Integer[] memo) {
        if (memo[src] != null)
            return memo[src];
        if (src == n)
            return 1;
        int ans = 0;
        for (int[] nei: graph[src]) {
            int w = nei[0], v = nei[1];
            if (dist[src] > dist[v])
                ans = (ans + dfs(v, n, graph, dist, memo)) % 1000000007;
        }
        memo[src] = ans;
        return ans;
    }

}
