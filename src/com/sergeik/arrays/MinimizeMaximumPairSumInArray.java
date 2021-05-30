package com.sergeik.arrays;

import java.util.Arrays;

/**
 * The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
 *
 * For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would
 * be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
 * Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
 *
 * Each element of nums is in exactly one pair, and
 * The maximum pair sum is minimized.
 * Return the minimized maximum pair sum after optimally pairing up the elements.
 */
public class MinimizeMaximumPairSumInArray {

    public static void main(String[] args) {
        assert 8 == solution(new int[]{3,5,4,2,4,6});
    }

    private static int solution(int[] nums) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        int max = 0;
        while (l < r) {
            max = Math.max(max, nums[l++] + nums[r--]);
        }
        return max;
    }

}
