package com.sergeik.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city
 * are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */
public class FriendCircles {

    public static void main(String[] args) {
        assert 3 == solution(new int[][]{
                {1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},
                {0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},
                {1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},
                {0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},
                {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}
        });
        assert 1 == solution(new int[][]{
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        });
        assert 2 == solution(new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1}
        });
        assert 3 == solution(new int[][]{
                {1,0,0},
                {0,1,0},
                {0,0,1}
        });
    }

    /**
     * Do DFS until all vertices are covered counting each time starting it again
     * @param isConnected
     * @return
     */
    private static int solution(int[][] isConnected) {
        int nNodes = isConnected.length;
        boolean[] visited = new boolean[nNodes];
        int visitedNodes = 0;
        int nRegions = 0;
        while (visitedNodes < nNodes) {
            int nextNode = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    nextNode = i;
                    break;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(nextNode);
            visited[nextNode] = true;
            while (!queue.isEmpty()) {
                int nextCity = queue.poll();
                visitedNodes++;
                for (int n = 0; n < nNodes; n++) {
                    if (isConnected[nextCity][n] == 1 && !visited[n]) {
                        queue.add(n);
                        visited[n] = true;
                    }
                }
            }
            nRegions++;
        }
        return nRegions;
    }

}
