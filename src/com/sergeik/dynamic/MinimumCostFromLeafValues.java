package com.sergeik.dynamic;

import java.util.Stack;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right
 * subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 */
public class MinimumCostFromLeafValues {

    public static void main(String[] args) {

        assert 500 == dpSolution(new int[] {15,13,5,3,15});
        assert 32 == dpSolution(new int[] {6,2,4});

        assert 500 == stackSolution(new int[] {15,13,5,3,15});
        assert 32 == stackSolution(new int[] {6,2,4});
    }

    /**
     * For each possible way to partition the subarray i <= k < j,
     * the answer is max(arr[i]..arr[k]) * max(arr[k+1]..arr[j]) + dp(i, k) + dp(k+1, j).
     * @param arr
     * @return
     */
    private static int dpSolution(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        return dfs(arr, 0, arr.length - 1, dp);
    }

    private static int dfs(int[] arr, int l, int r, int[][] dp) {
        if (l == r)
            return 0;
        if (dp[l][r] > 0)
            return dp[l][r];
        int res = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            int left = dfs(arr, l, i, dp);
            int right = dfs(arr, i + 1, r, dp);
            int maxLeft = 0, maxRight = 0;
            for (int s = l; s <= i; s++)
                maxLeft = Math.max(maxLeft, arr[s]);
            for (int s = i + 1; s <= r; s++)
                maxRight = Math.max(maxRight, arr[s]);
            res = Math.min(res, left + right + maxLeft * maxRight );
        }
        dp[l][r] = res;
        return res;
    }

    private static int stackSolution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        int res = 0;
        for (int n: arr) {
            while (stack.peek() <= n) {
                res += stack.pop() * Math.min(n, stack.peek());
            }
            stack.push(n);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }

}
