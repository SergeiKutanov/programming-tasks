package com.sergeik.arrays;

import java.util.*;

/**
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However,
 * you do remember every pair of adjacent elements in nums.
 *
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi]
 * indicates that the elements ui and vi are adjacent in nums.
 *
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
 * either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 *
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 *
 */
public class RestoreArrayFromAdjacentPAirs {

    public static void main(String[] args) {

        assert Arrays.equals(
                new int[] {-10, 4, -3, 3, -1},
                solution(new int[][] {
                        {4, -10},
                        {-1, 3},
                        {4, -3},
                        {-3, 3}
                })
        );

        assert Arrays.equals(
                new int[] {-100000,100000},
                solution(new int[][] {
                        {100000,-100000}
                })
        );

        assert Arrays.equals(
                new int[] {4,3,2,1},
                solution(new int[][] {
                        {2,1},
                        {3,4},
                        {3,2}
                })
        );

        assert Arrays.equals(
                new int[] {-3,1,4,-2},
                solution(new int[][] {
                        {4,-2},
                        {1,4},
                        {-3,1}
                })
        );
    }

    private static int[] solution(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        for (int[] v: adjacentPairs) {
            adjList.computeIfAbsent(v[0], val -> new LinkedList<>()).add(v[1]);
            adjList.computeIfAbsent(v[1], val -> new LinkedList<>()).add(v[0]);
        }

        int start = 0;
        for (int v: adjList.keySet()) {
            if (adjList.get(v).size() == 1)
                start = v;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        seen.add(start);
        int[] res = new int[adjacentPairs.length + 1];
        int idx = 0;
        while (!stack.isEmpty()) {
            int edge = stack.pop();
            res[idx++] = edge;
            for (int n: adjList.get(edge)) {
                if (!seen.contains(n)) {
                    seen.add(n);
                    stack.add(n);
                }
            }
        }
        return res;
    }

}
