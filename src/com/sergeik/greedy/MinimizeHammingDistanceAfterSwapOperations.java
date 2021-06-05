package com.sergeik.greedy;

import java.util.*;

/*8
You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps
 where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index
  bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times
  and in any order.

The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements
 are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).

Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
 */
public class MinimizeHammingDistanceAfterSwapOperations {

    public static void main(String[] args) {

        assert 1 == solution(
                new int[] {2,3,1},
                new int[] {1,2,2},
                new int[][] {{0,2}, {1,2}}
        );

        assert 0 == solution(
                new int[] {5,1,2,4,3},
                new int[] {1,5,4,2,3},
                new int[][] {{0,4}, {4,2}, {1,3}, {1,4}}
        );

        assert 2 == solution(
                new int[] {1,2,3,4},
                new int[] {1,3,2,4},
                new int[0][0]
        );

        assert 1 == solution(
                new int[] {1,2,3,4},
                new int[] {2,1,4,5},
                new int[][] {{0,1}, {2,3}}
        );
    }

    private static int solution(int[] source, int[] target, int[][] allowedSwaps) {
        //keep track of components
        int[] groups = new int[source.length];

        //initiate components with one element in each
        for (int i = 0; i < groups.length; i++) {
            groups[i] = i;
        }

        //populate components
        for (int[] swap: allowedSwaps) {
            int parentOne = find(groups, swap[0]);
            int parentTwo = find(groups, swap[1]);

            if (parentOne != parentTwo) {
                groups[parentOne] = parentTwo;
            }
        }

        //populate all elements of component in root map with frequency
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            int parent = find(groups, i);
            map.putIfAbsent(parent, new HashMap<>());
            Map<Integer, Integer> pMap = map.get(parent);
            pMap.put(source[i], pMap.getOrDefault(source[i], 0) + 1);
        }

        //count elements not in common
        int count = 0;
        for (int i = 0; i < target.length; i++) {
            int parent = find(groups, i);
            Map<Integer, Integer> pMap = map.get(parent);
            Integer v = pMap.get(target[i]);
            if (v != null) {
                v--;
                if (v == 0)
                    pMap.remove(target[i]);
                else
                    pMap.put(target[i], v);
            } else {
                count++;
            }
        }

        return count;
    }

    private static int find(int[] groups, int node) {
        int parent = node;
        while (parent != groups[parent])
            parent = groups[parent];

        //path compression
        int prev = node;
        while (prev != parent) {
            int tmp = groups[prev];
            groups[tmp] = parent;
            prev = tmp;
        }

        return parent;
    }

    private static int dfsSolution(int[] source, int[] target, int[][] allowedSwaps) {
        List<Integer>[] comp = new List[target.length];
        for (int i = 0; i < source.length; i++) {
            comp[i] = new LinkedList();
        }
        for (int[] swap: allowedSwaps) {
            comp[swap[0]].add(swap[1]);
            comp[swap[1]].add(swap[0]);
        }
        int counter = 0;
        boolean[] used = new boolean[source.length];
        for (int i = 0; i < target.length; i++) {
            int n = target[i];
            boolean found = dfs(n, i, comp, source, used);
            if (!found)
                counter++;
        }
        return counter;
    }

    private static boolean dfs(int target, int idx, List<Integer>[] comp, int[] source, boolean[] used) {
        boolean[] seen = new boolean[source.length];
        Stack<Integer> stack = new Stack<>();
        seen[idx] = true;
        stack.push(idx);
        while (!stack.isEmpty()) {
            idx = stack.pop();
            if (source[idx] == target && !used[idx]) {
                used[idx] = true;
                return true;
            }
            for (int neigh: comp[idx]) {
                if (!seen[neigh]) {
                    seen[neigh] = true;
                    stack.push(neigh);
                }
            }
        }
        return false;
    }

}
