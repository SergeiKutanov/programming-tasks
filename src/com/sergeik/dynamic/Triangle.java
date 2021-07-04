package com.sergeik.dynamic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on
 * the current row, you may move to either index i or index i + 1 on the next row.
 */
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle;

        triangle = new LinkedList<>();
        triangle.add(Arrays.asList(-7));
        triangle.add(Arrays.asList(-2,1));
        triangle.add(Arrays.asList(-5,-5,9));
        triangle.add(Arrays.asList(-4,-5,4,4));
        triangle.add(Arrays.asList(-6,-6,2,-1,-5));
        triangle.add(Arrays.asList( 3, 7,8,-3, 7,-9));
        triangle.add(Arrays.asList(-9,-1,-9,6, 9, 0,7));
        triangle.add(Arrays.asList(-7, 0,-6,-8,7, 1,-4,9));
        triangle.add(Arrays.asList(-3, 2,-6,-9,-7,-6,-9,4,0));
        triangle.add(Arrays.asList(-8,-6,-3,-9,-2,-6,7,-5,0,7));
        triangle.add(Arrays.asList(-9,-1,-2,4,-2,4,4,-1,2,-5,5));
        triangle.add(Arrays.asList( 1, 1,-6,1,-2,-4,4,-2,6,-6,0,6));
        assert -59 == solution(triangle);

        triangle = new LinkedList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(2,3));
        triangle.add(Arrays.asList(5,5,1));
        triangle.add(Arrays.asList(5,5,5,1));
        triangle.add(Arrays.asList(5,5,5,5,1));
        assert 8 == solution(triangle);

        triangle = new LinkedList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        assert 11 == solution(triangle);
    }

    private static int solution(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> level = triangle.get(i);
            dp[i][0] = level.get(0) + dp[i - 1][0];
            for (int j = 1; j < level.size() - 1; j++) {
                int val = level.get(j);
                dp[i][j] = Math.min(dp[i - 1][j] + val, dp[i - 1][j - 1] + val);
            }
            dp[i][level.size() - 1] = level.get(level.size() - 1) + dp[i - 1][level.size() - 2];
        }
        int res = Integer.MAX_VALUE;
        for (int sum: dp[triangle.size() - 1])
            res = Math.min(res, sum);
        return res;
    }

    private static int bruteSolution(List<List<Integer>> triangle) {
        int res = backtrack(triangle, 1, 0, triangle.get(0).get(0));
        return res;
    }

    private static int backtrack(List<List<Integer>> triangle, int level, int col, int sum) {
        if (level == triangle.size())
            return sum;
        int left = backtrack(triangle, level + 1, col, sum + triangle.get(level).get(col));
        int right = backtrack(triangle, level + 1, col + 1, sum + triangle.get(level).get(col + 1));
        return Math.min(left, right);
    }

}
