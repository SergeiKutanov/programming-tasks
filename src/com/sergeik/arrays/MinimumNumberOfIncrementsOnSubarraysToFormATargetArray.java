package com.sergeik.arrays;

import java.util.Stack;

/**
 * Given an array of positive integers target and an array initial of same size with all zeros.
 *
 * Return the minimum number of operations to form a target array from initial if you are allowed to do
 * the following operation:
 *
 * Choose any subarray from initial and increment each value by one.
 * The answer is guaranteed to fit within the range of a 32-bit signed integer.
 */
public class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

    public static void main(String[] args) {
        assert 6 == solution(new int[] {1,1,5,5,4,5,1,1});
        assert 1 == solution(new int[] {1,1,1,1});
        assert 4 == solution(new int[] {3,1,1,2});
        assert 3 == solution(new int[] {1,2,3,2,1});
        assert 7 == solution(new int[] {3,1,5,4,2});
    }

    private static int solution(int[] target) {
        int res = target[0];
        for (int i = 1; i < target.length; i++)
            res += Math.max(target[i] - target[i - 1], 0);
        return res;
    }

    private static int monoStackSolution(int[] target) {
        Stack<Integer> stack = new Stack<>();
        int res = target[0];
        stack.push(target[0]);
        for (int i = 1; i < target.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= target[i]) {
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek() < target[i])
                res += target[i] - stack.pop();
            stack.push(target[i]);
        }
        return res;
    }

}
