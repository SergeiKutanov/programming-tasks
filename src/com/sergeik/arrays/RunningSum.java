package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 */
public class RunningSum {

    public static void main(String[] args) {
        assert Arrays.equals(new int[]{1,3,6,10}, solution(new int[]{1,2,3,4}));
        assert Arrays.equals(new int[]{1,2,3,4,5}, solution(new int[]{1,1,1,1,1}));
        assert Arrays.equals(new int[]{3,4,6,16,17}, solution(new int[]{3,1,2,10,1}));

        assert Arrays.equals(new int[]{1,3,6,10}, inPlaceSolution(new int[]{1,2,3,4}));
        assert Arrays.equals(new int[]{1,2,3,4,5}, inPlaceSolution(new int[]{1,1,1,1,1}));
        assert Arrays.equals(new int[]{3,4,6,16,17}, inPlaceSolution(new int[]{3,1,2,10,1}));
    }

    private static int[] inPlaceSolution(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }

    private static int[] solution(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length == 0)
            return res;
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i] + res[i - 1];
        }
        return res;
    }

}
