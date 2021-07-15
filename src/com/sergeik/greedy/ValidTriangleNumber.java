package com.sergeik.greedy;

import java.util.Arrays;

/**
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles
 * if we take them as side lengths of a triangle.
 */
public class ValidTriangleNumber {

    public static void main(String[] args) {
        assert 4 == solution(new int[] {4,2,3,4});
    }

    private static int solution(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        //4,4,3,2
        for (int i = 2; i < nums.length; i++) {
            int j = 0, k = i - 1;
            while (j < k) {
                if (nums[j] + nums[k] > nums[i]) {
                    res += k - j;
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

}
