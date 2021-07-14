package com.sergeik.stack;

import java.util.*;

/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the
 * next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for
 * this number.
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {2,3,-1,3,2}, solution(new int[] {1,2,3,2,1}));
        assert Arrays.equals(new int[] {-1,-1,-1,-1,-1}, solution(new int[] {1,1,1,1,1}));
        assert Arrays.equals(new int[] {2,-1,2}, solution(new int[] {1,2,1}));
        assert Arrays.equals(new int[] {6,-1,6,4,6,6}, solution(new int[] {1,6,5,3,4,3}));
        assert Arrays.equals(new int[] {2,3,4,-1,4}, solution(new int[] {1,2,3,4,3}));
    }

    private static int[] solution(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % nums.length])
                stack.pop();
            res[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    private static int[] stackMapSolution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[nums.length * 2];
        for (int i = 0; i < dp.length; i++) {
            int originalIdx = i % nums.length;
            dp[i] = nums[originalIdx];
        }
        for (int i = 0; i < dp.length; i++) {
            while (!stack.isEmpty() && dp[stack.peek()] < dp[i])
                map.put(stack.pop(), i);
            stack.push(i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(i))
                nums[i] = map.get(i);
            else
                nums[i] = map.getOrDefault(i + nums.length, -1);
            if (nums[i] >= 0)
                nums[i] = dp[nums[i]];
        }
        return nums;
    }

}
