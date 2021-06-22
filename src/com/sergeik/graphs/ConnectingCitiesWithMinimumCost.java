package com.sergeik.graphs;

import java.util.PriorityQueue;

/**
 * There are n cities numbered from 1 to n.
 *
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1
 * and city2 together.  (A connection is bidirectional: connecting city1 and city2
 * is the same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities, there exists a path of connections
 * (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used.
 * If the task is impossible, return -1.
 */
public class ConnectingCitiesWithMinimumCost {

    public static void main(String[] args) {
        assert -1 == solution(4, new int[][] {
                {1,2,3},
                {3,4,4}
        });
        assert 6 == solution(3, new int[][] {
                {1,2,5},
                {1,3,6},
                {2,3,1}
        });
    }

    private static int solution(int n, int[][] connections) {
        int[] groups = new int[n + 1];
        int[] sizes = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            groups[i] = i;
            sizes[i] = 1;
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        for (int[] c: connections)
            heap.offer(c);

        int cost = 0;
        while (!heap.isEmpty() && n > 1) {
            int[] conn = heap.poll();
            int parentOne = findParent(conn[0], groups);
            int parentTwo = findParent(conn[1], groups);
            if (parentOne != parentTwo) {
                if (sizes[parentOne] > sizes[parentTwo]) {
                    groups[parentOne] = parentTwo;
                    sizes[parentTwo] += sizes[parentOne];
                } else {
                    groups[parentTwo] = parentOne;
                    sizes[parentOne] += sizes[parentTwo];
                }
                cost += conn[2];
                n--;
            }
        }
        return n == 1 ? cost : -1;
    }

    private static int findParent(int n, int[] groups) {
        int parent = n;
        while (groups[parent] != parent)
            parent = groups[parent];

        //path compression
        while (parent != groups[n]) {
           n = groups[n];
           groups[n] = parent;
        }

        return parent;
    }

}
