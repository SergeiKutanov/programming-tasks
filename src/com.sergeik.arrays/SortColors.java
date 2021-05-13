package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects
 * of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 */
public class SortColors {

    public static void main(String[] args) {
        int[] colors = new int[]{2,0,2,1,1,0};
        solution(colors);
        assert Arrays.equals(
                new int[]{0,0,1,1,2,2},
                colors
        );
    }

    private static void solution(int[] nums) {
        int[] counters = new int[3];
        for (int n: nums) {
            counters[n]++;
        }
        int pointer = 0;
        for (int i = 0; i < counters.length; i++) {
            for (int j = 0; j < counters[i]; j++) {
                nums[pointer++] = i;
            }
        }
    }

}
