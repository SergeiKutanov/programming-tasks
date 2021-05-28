package com.sergeik.arrays;

import java.util.Stack;

/**
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 *
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums.
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that the min-product should be maximized before performing the modulo operation. Testcases are generated
 * such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 *
 * A subarray is a contiguous part of an array.
 */
public class MaximumSubarrayMinProduct {

    public static void main(String[] args) {
        assert 25 == stackSolution(new int[]{1,1,3,2,2,2,1,5,1,5});
        assert 18 == stackSolution(new int[]{2,3,3,1,2});
        assert 14 == stackSolution(new int[]{1,2,3,2});
        assert 60 == stackSolution(new int[]{3,1,5,6,4,2});
    }

    private static int stackSolution(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        long res = 0;
        long[] dp = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = dp[i] + nums[i];
        }
        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && (i == nums.length || nums[stack.peek()] > nums[i])) {
                int j = stack.pop();
                int minN = nums[j];
                long sum = dp[i] - dp[stack.isEmpty() ? 0 : stack.peek() + 1];
                res = Math.max(
                        res,
                        minN * sum
                    );
            }
            stack.push(i);
        }
        return (int)(res % 1000000007);
    }

    private static int solution(int[] nums) {
        long[] dp = new long[nums.length];
        int i = 0;
        while (i < nums.length) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
            } else {
                int start = i;
                long sum = nums[i];
                while (start > 0 && nums[start - 1] >= nums[i]) {
                    start--;
                    sum += nums[start];
                }
                int end = i;
                while (end < nums.length - 1 && nums[end + 1] >= nums[i]) {
                    end++;
                    sum += nums[end];
                }
                dp[i] = sum * nums[i];
                i++;
            }

        }

        long max = 0;
        for (long n: dp) {
            max = Math.max(max, n);
        }
        return (int) (max % 1000000007);
    }

}
