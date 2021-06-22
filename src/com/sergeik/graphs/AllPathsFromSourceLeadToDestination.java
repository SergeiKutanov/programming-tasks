package com.sergeik.graphs;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi,
 * and two nodes source and destination of this graph, determine whether or not all paths starting from
 * source eventually, end at destination, that is:
 *
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 */
public class AllPathsFromSourceLeadToDestination {

    public static void main(String[] args) {
        assert solution(4, new int[][] {{0,1},{0,2},{1,3},{2,3}}, 0, 3);
    }

    private static boolean solution(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = buildGraph(n, edges);
        return dfs(graph, source, destination, new Boolean[n]);
    }

    /**
     * states:
     *      - null = white, not visited
     *      - false = grey, in traversal
     *      - true = black, processed
     * @param graph
     * @param source
     * @param destination
     * @param states
     * @return
     */
    private static boolean dfs(List<Integer>[] graph, int source, int destination, Boolean[] states) {
        if (states[source] != null)
            return states[source] == true;
        if (graph[source].isEmpty())
            return source == destination;
        states[source] = false;
        for (int next: graph[source]) {
            if (!dfs(graph, next, destination, states))
                return false;
        }
        states[source] = true;
        return true;
    }

    private static List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
        }
        return graph;
    }



}
