package com.sergeik.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 1};
        assert solution(nums) == true;

        nums = new int[] {1, 2, 3 ,4};
        assert solution(nums) == false;

        nums = new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        assert solution(nums) == true;
    }

    /**
     * Use a set to store values already seen.
     * Once we come across a value in the set we found a duplicate.
     * @param nums
     * @return
     */
    private static boolean solution(int[] nums) {
        Set<Integer> valuesSeen = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (valuesSeen.contains(nums[i])) {
                return true;
            }
            valuesSeen.add(nums[i]);
        }
        return false;
    }

}
