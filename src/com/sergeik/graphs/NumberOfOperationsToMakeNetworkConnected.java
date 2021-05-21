package com.sergeik.graphs;

import java.util.*;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where
 * connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other
 * computer directly or indirectly through the network.
 *
 * Given an initial computer network connections. You can extract certain cables between two directly connected
 * computers, and place them between any pair of disconnected computers to make them directly connected.
 * Return the minimum number of times you need to do this in order to make all the computers connected.
 * If it's not possible, return -1.
 */
public class NumberOfOperationsToMakeNetworkConnected {

    public static void main(String[] args) {
        assert 3 == recSolution(11, new int[][]{
                {1,4},{0,3},{1,3},{3,7},{2,7},{0,1},{2,4},{3,6},{5,6},{6,7},{4,7},{0,7},{5,7}
        });
        assert 1 == recSolution(6, new int[][]{{0,1}, {0,2}, {0,3}, {1,2}, {1,3}, {4,5}});
        assert 2 == recSolution(6, new int[][]{{0,1}, {0,2}, {0,3}, {1,2}, {1,3}});
        assert -1 == recSolution(6, new int[][]{{0,1}, {0,2}, {0,3}, {1,2}});
    }

    private static int recSolution(int n, int[][] connections) {
        if (n > connections.length + 1)
            return -1;

        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();

        for (int[] connection: connections) {
            graph[connection[0]].add(connection[1]);
            graph[connection[1]].add(connection[0]);
        }

        int components = 0;
        boolean[] visited = new boolean[n];
        for (int start = 0; start < n; start++) {
            components += dfs(graph, start, visited);
        }
        return components - 1;
    }

    private static int dfs(List<Integer>[] graph, int start, boolean[] visited) {
        if (visited[start])
            return 0;
        visited[start] = true;
        for (int n: graph[start]) {
            dfs(graph, n, visited);
        }
        return 1;
    }

    private static int solution(int n, int[][] connections) {
        if (n > connections.length + 1)
            return -1;

        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();

        for (int[] connection: connections) {
            graph[connection[0]].add(connection[1]);
            graph[connection[1]].add(connection[0]);
        }

        int components = 0;
        boolean[] visited = new boolean[n];
        Stack<Integer> dfs = new Stack<>();
        for (int node = 0; node < n; node++) {
            if (visited[node])
                continue;
            components++;
            dfs.push(node);
            visited[node] = true;
            while (!dfs.isEmpty()) {
                int start = dfs.pop();
                for (int neigh: graph[start]) {
                    if (!visited[neigh]) {
                        visited[neigh] = true;
                        dfs.push(neigh);
                    }
                }
            }
        }
        return components - 1;
    }

}
