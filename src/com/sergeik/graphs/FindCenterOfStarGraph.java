package com.sergeik.graphs;

/**
 * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where
 * there is one center node and exactly n - 1 edges that connect the center node with every other node.
 *
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an
 * edge between the nodes ui and vi. Return the center of the given star graph.
 */
public class FindCenterOfStarGraph {

    public static void main(String[] args) {
        assert 1 == solution(new int[][]{
                {1,2},
                {5,1},
                {1,3},
                {1,4}
        });
        assert 2 == solution(new int[][]{
                {1,2},
                {2,3},
                {4,2}
        });
    }

    private static int solution(int[][] edges) {
        int center = edges[0][0];
        if (center == edges[1][0] || center == edges[1][1])
            return edges[0][0];
        return edges[0][1];
    }

}
