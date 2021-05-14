package com.sergeik.arrays;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        assert Arrays.equals(new int[]{3,3,5,5,6,7}, solution(new int[]{1,3,-1,-3,5,3,6,7}, 3));
        assert Arrays.equals(new int[]{1}, solution(new int[]{1}, 1));
        assert Arrays.equals(new int[]{1, -1}, solution(new int[]{1, -1}, 1));
        assert Arrays.equals(new int[]{11}, solution(new int[]{9, 11}, 2));
        assert Arrays.equals(new int[]{4}, solution(new int[]{4, -2}, 2));
    }

    private static int[] solution(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }

        int currentWindow = 0;
        for (int i = k; i < nums.length; i++) {
            res[currentWindow++] = nums[dq.peekFirst()];

            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.removeFirst();
            }

            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        res[currentWindow] = nums[dq.peekFirst()];
        return res;
    }

    private static int[] bruteSolution(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < res.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < k + i; j++) {
                if (max < nums[j])
                    max = nums[j];
            }
            res[i] = max;
        }
        return res;
    }

}
