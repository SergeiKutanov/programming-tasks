package com.sergeik.arrays;

import java.util.*;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        assert Arrays.equals(new int[]{0, 1}, bruteSolution(nums, target));
        assert Arrays.equals(new int[]{0, 1}, twoPassMap(nums, target));
        assert Arrays.equals(new int[]{1, 0}, onePassMap(nums, target));

        nums = new int[] {3, 2, 4};
        target = 6;
        assert Arrays.equals(new int[] {1, 2}, bruteSolution(nums, target));
        assert Arrays.equals(new int[] {1, 2}, twoPassMap(nums, target));
        assert Arrays.equals(new int[] {2, 1}, onePassMap(nums, target));

        nums = new int[] {3, 3};
        target = 6;
        assert Arrays.equals(new int[] {0, 1}, bruteSolution(nums, target));
        assert Arrays.equals(new int[] {0, 1}, twoPassMap(nums, target));
        assert Arrays.equals(new int[] {1, 0}, onePassMap(nums, target));
    }

    /**
     * Brute force - time O(n*n)
     * @param nums
     * @param target
     * @return
     */
    private static int[] bruteSolution(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {};
    }

    /**
     * time O(n), memory O(n)
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoPassMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Worst case as bad as two pass, but the overage case should be much better
     * @param nums
     * @param target
     * @return
     */
    private static int[] onePassMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff) && map.get(diff) != i) {
                return new int[]{i, map.get(diff)};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

}
