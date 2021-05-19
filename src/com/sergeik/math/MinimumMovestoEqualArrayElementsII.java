package com.sergeik.math;

import java.util.Arrays;

public class MinimumMovestoEqualArrayElementsII {

    public static void main(String[] args) {
        assert 2 == solution(new int[]{1,2,3});
        assert 16 == solution(new int[]{1,10,2,9});
    }

    private static int solution(int[] nums) {
        Arrays.sort(nums);
        int middle = nums[nums.length / 2];
        int count = 0;
        for (int n: nums) {
            count += Math.abs(middle - n);
        }
        return count;
    }

}
