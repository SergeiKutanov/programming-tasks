package com.sergeik.dynamic;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
 * and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 */
public class MaximumProductSubarray {

    public static void main(String[] args) {
        assert 90 == solution(new int[] {0,-2,3,3,-5});
        assert 9 == solution(new int[] {0,-2,3,3});
        assert 2 == solution(new int[] {0,2});
        assert 36 == solution(new int[] {2,3,-2,-3});
        assert 6 == solution(new int[] {2,3,-2,4});
        assert 0 == solution(new int[] {-2,0,-1});
    }

    private static int solution(int[] nums) {
        if (nums.length == 0)
            return 0;
        int min = nums[0], max = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int tmpMax = Math.max(curr, Math.max(max * curr, min * curr));
            min = Math.min(curr, Math.min(max * curr, min * curr));
            max = tmpMax;
            res = Math.max(max, res);
        }
        return res;
    }

}
