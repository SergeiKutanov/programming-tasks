package com.sergeik.graphs;

import javafx.util.Pair;

import java.util.*;

/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming
 * a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can
 * reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 */
public class CriticalConnectionsInANetwork {

    public static void main(String[] args) {
        List<List<Integer>> res, exp, con;

        exp = new LinkedList<>();
        exp.add(Arrays.asList(1,3));
        con = new LinkedList<>();
        con.add(Arrays.asList(0,1));
        con.add(Arrays.asList(1,2));
        con.add(Arrays.asList(2,0));
        con.add(Arrays.asList(1,3));
        res = solution(4, con);
        for (int i = 0; i < exp.size(); i++) {
            assert exp.get(i).get(0) == res.get(i).get(0);
            assert exp.get(i).get(1) == res.get(i).get(1);
        }
    }

    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Map<Integer, Integer> rank = new HashMap<>();
    private static Map<Pair<Integer, Integer>, Boolean> connDict = new HashMap<>();

    private static List<List<Integer>> solution(int n, List<List<Integer>> connections) {
        //for graph as adjacency list
        //also put all ranks to null
        formGraph(n, connections);
        dfs(0, 0);

        List<List<Integer>> res = new LinkedList<>();
        for (Pair<Integer, Integer> criticalConnection: connDict.keySet()) {
            res.add(new LinkedList<>(Arrays.asList(criticalConnection.getKey(), criticalConnection.getValue())));
        }
        return res;
    }

    private static int dfs(int node, int discoveryRank) {
        //if rank is set - we saw the node before, just return the rank
        if (rank.get(node) != null)
            return rank.get(node);

        //put the given rank for the node
        rank.put(node, discoveryRank);

        int minRank = discoveryRank + 1;

        //explore node's neighbours
        for (int neigh: graph.get(node)) {
            //check if rank is set == node has been seen before
            Integer neighRank = rank.get(neigh);
            //if rank is set and it's -1 from current rank -- skipping node's parent
            if (neighRank != null && neighRank == discoveryRank - 1)
                continue;
            //get rank of the neighbour recursively
            int recursiveRank = dfs(neigh, discoveryRank + 1);
            //if the rank is lower or equal to current node remove the edge from connections
            if (recursiveRank <= discoveryRank) {
                int sortedU = Math.min(node, neigh), sortedV = Math.max(node, neigh);
                connDict.remove(new Pair(sortedU, sortedV));
            }
            //retain the main rank seen so far
            minRank = Math.min(minRank, recursiveRank);
        }
        return minRank;
    }

    private static void formGraph(int n, List<List<Integer>> connections) {
        graph = new HashMap<>();
        rank = new HashMap<>();
        connDict = new HashMap<>();

        for (int i = 0; i < n; i++) {
            rank.put(i, null);
            graph.put(i, new LinkedList<>());
        }

        for (List<Integer> edge: connections) {
            int u = edge.get(0), v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);

            int sortedU = Math.min(u, v), sortedV = Math.max(u, v);
            connDict.put(new Pair(sortedU, sortedV), true);
        }

    }

}
