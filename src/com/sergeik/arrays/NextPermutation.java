package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order
 * (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 */
public class NextPermutation {

    public static void main(String[] args) {

        int[] nums = new int[]{1,3,5,7,2,3,9,2,1};
        solution(nums);
        assert Arrays.equals(
                new int[]{1,3,5,7,2,9,1,2,3},
                nums
        );

        nums = new int[]{1,2,3};
        solution(nums);
        assert Arrays.equals(
                new int[]{1,3,2},
                nums
        );

        nums = new int[]{1,1,5};
        solution(nums);
        assert Arrays.equals(
                new int[]{1,5,1},
                nums
        );
    }

    private static void solution(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return;

        int index = len - 1;
        while (index > 0) {
            if (nums[index] > nums[index - 1])
                break;
            index--;
        }

        if (index == 0) {
            reverse(nums, 0, len - 1);
        } else {
            int val = nums[index - 1];
            int j = len - 1;
            while (j >= index) {
                if (nums[j] > val)
                    break;
                j--;
            }
            swap(nums, j, index - 1);
            reverse(nums, index, len - 1);
        }
    }

    private static void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    private static void reverse(int[] nums, int start, int end) {
        if (start > end)
            return;
        for (int i = start; i <= (start + end) / 2; i++) {
            swap(nums, i, start + end - i);
        }
    }


}
