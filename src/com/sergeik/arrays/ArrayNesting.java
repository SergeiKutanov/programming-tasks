package com.sergeik.arrays;

/**
 * You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].
 *
 * You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:
 *
 * The first element in s[k] starts with the selection of the element nums[k] of index = k.
 * The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
 * We stop adding right before a duplicate element occurs in s[k].
 * Return the longest length of a set s[k].
 */
public class ArrayNesting {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {0,1,2});
        assert 4 == solution(new int[] {5,4,0,3,1,6,2});
    }

    private static int solution(int[] nums) {
        int res = 0;
        int[] memo = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int cRes = dfs(i, new boolean[nums.length], nums, memo);
            res = Math.max(res, cRes);
        }
        return res;
    }

    private static int dfs(int idx, boolean[] mask, int[] nums, int[] memo) {
        if (mask[idx])
            return 0;
        if (memo[idx] != 0)
            return memo[idx];
        mask[idx] = true;
        int res = 1;
        int cRes = dfs(nums[idx], mask, nums, memo);
        memo[idx] = res + cRes;
        return memo[idx];
    }

}
