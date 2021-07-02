package com.sergeik.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 */
public class Shift2DGrid {

    public static void main(String[] args) {
        List<List<Integer>> exp, res;

        exp = new LinkedList<>();
        exp.add(Arrays.asList(6));
        exp.add(Arrays.asList(5));
        exp.add(Arrays.asList(1));
        exp.add(Arrays.asList(2));
        exp.add(Arrays.asList(3));
        exp.add(Arrays.asList(4));
        exp.add(Arrays.asList(7));
        res = solution(new int[][]{
                {1},
                {2},
                {3},
                {4},
                {7},
                {6},
                {5},
        }, 23);
        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));

        exp = new LinkedList<>();
        exp.add(Arrays.asList(9,1,2));
        exp.add(Arrays.asList(3,4,5));
        exp.add(Arrays.asList(6,7,8));
        res = solution(new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        }, 1);
        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));


        exp = new LinkedList<>();
        exp.add(Arrays.asList(12,0,21,13));
        exp.add(Arrays.asList(3,8,1,9));
        exp.add(Arrays.asList(19,7,2,5));
        exp.add(Arrays.asList(4,6,11,10));
        res = solution(new int[][]{
                {3,8,1,9},
                {19,7,2,5},
                {4,6,11,10},
                {12,0,21,13}
        }, 4);
        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
    }

    private static List<List<Integer>> solution(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            res.add(new ArrayList<>(n));
            for (int j = 0; j < n; j++)
                res.get(i).add(0);
        }

        int horOffset = k % n;
        for (int i = 0; i < n; i++) {
            int verOffset = ((i + k) / n) % m;
            for (int j = 0; j < m; j++)
                res.get((j + verOffset) % m).set((i + horOffset) % n, grid[j][i]);
        }
        return res;
    }

}
