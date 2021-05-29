package com.sergeik.bits;

import java.util.Arrays;

/**
 * You are given a sorted array nums of n non-negative integers and an integer maximumBit.
 * You want to perform the following query n times:
 *
 * Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1]
 * XOR k is maximized. k is the answer to the ith query.
 * Remove the last element from the current array nums.
 * Return an array answer, where answer[i] is the answer to the ith query.
 */
public class MaximumXorForEachQuery {

    public static void main(String[] args) {
        assert Arrays.equals(
                new int[]{0,3,2,3},
                solution(new int[]{0,1,1,3}, 2)
        );
        assert Arrays.equals(
                new int[] {4,3,6,4,6,7},
                solution(new int[]{0,1,2,2,5,7}, 3)
        );
        assert Arrays.equals(
                solution(new int[] {2,3,4,7}, 3),
                new int[]{5,2,6,5}
        );
    }

    private static int[] solution(int[] nums, int maximumBit) {
        int[] res = new int[nums.length];
        int mask = (1 << maximumBit) - 1;
        int j = 0;
        int xor = 0;
        for (int n: nums) {
            xor ^= n;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = ~xor & mask;
            res[j++] = max;
            xor ^= nums[i];
        }
        return res;
    }

}
