package com.sergeik.heap;

import java.util.*;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going
 * outside the boundaries of the array. That is, you can jump from index i to any index in the
 * range [i + 1, min(n - 1, i + k)] inclusive.
 *
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for
 * each index j you visited in the array.
 *
 * Return the maximum score you can get.
 */
public class JumpGameVi {

    public static void main(String[] args) {
        //10,4,3
        assert 17 == solution(new int[] {10,-5,-2,4,0,3}, 3);
        assert 0 == solution(new int[] {1,-5,-20,4,-1,3,-6,-3}, 2);
        assert 7 == solution(new int[] {1,-1,-2,4,-7,3}, 2);
    }

    private static int solution(int[] nums, int k){
        int res = 0;
        Deque<int[]> deque = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!deque.isEmpty() && deque.peekFirst()[1] > i + k)
                deque.pollFirst();
            if (i == nums.length - 1) {
                res = nums[i];
            } else {
                res = nums[i] + deque.peekFirst()[0];
            }

            while (!deque.isEmpty() && deque.peekLast()[0] <= res)
                deque.pollLast();
            deque.offer(new int[] {res, i});
        }
        return res;
    }


    private static int heapSolution(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Queue<int[]> heap = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        for (int i = dp.length - 1; i >= 0; i--) {
            findMax(dp, nums, i, k, heap);
        }
        return dp[0];
    }

    private static void findMax(int[] dp, int[] nums, int start, int k, Queue<int[]> heap) {
        if (start == nums.length - 1) {
            dp[start] = nums[start];
        } else {
            dp[start] = nums[start];
            while (heap.peek()[1] > start + k)
                heap.poll();
            dp[start] += heap.peek()[0];
        }
        heap.offer(new int[] {dp[start], start});
    }

}
