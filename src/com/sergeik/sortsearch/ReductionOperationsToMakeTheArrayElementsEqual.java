package com.sergeik.sortsearch;

import java.util.Arrays;

/**
 * Given an integer array nums, your goal is to make all elements in nums equal. To complete one operation,
 * follow these steps:
 *
 * Find the largest value in nums. Let its index be i (0-indexed) and its value be largest. If there are multiple
 * elements with the largest value, pick the smallest i.
 * Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
 * Reduce nums[i] to nextLargest.
 * Return the number of operations to make all elements in nums equal.
 */
public class ReductionOperationsToMakeTheArrayElementsEqual {

    public static void main(String[] args) {
        assert 45 == solution(new int[] {7,9,10,8,6,4,1,5,2,3});
        assert 3 == solution(new int[]{5,1,3});
        assert 0 == solution(new int[]{1,1,1});
        assert 4 == solution(new int[]{1,1,2,2,3});
    }

    private static int solution(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] != nums[i - 1])
                res += nums.length - i;
        }
        return res;
    }

}
