package com.sergeik.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = new int[] {2, 2, 1};
        assert hashMapSolution(nums) == 1;
        assert noExtraMemorySolution(nums) == 1;

        nums = new int[] {5, 1, 2, 1, 2};
        assert hashMapSolution(nums) == 5;
        assert noExtraMemorySolution(nums) == 5;

        nums = new int[] {4, 1, 2, 1, 2};
        assert hashMapSolution(nums) == 4;
        assert noExtraMemorySolution(nums) == 4;

        nums = new int[] {1};
        assert hashMapSolution(nums) == 1;
        assert noExtraMemorySolution(nums) == 1;
    }

    /**
     * Keep track of single elements and remove from the set once seen the second time
     * @param nums
     * @return
     */
    private static int hashMapSolution(int[] nums) {
        Set<Integer> singles = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (singles.contains(value)) {
                singles.remove(value);
            } else {
                singles.add(value);
            }
        }
        return singles.iterator().next();
    }

    /**
     * If we take XOR of zero and some bit, it will return that bit
     * a ^ 0 = a
     * If we take XOR of two same bits, it will return 0
     * a ^ a = 0
     * a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b
     * So we can XOR all bits together to find the unique number.
     * @param nums
     * @return
     */
    private static int noExtraMemorySolution(int[] nums) {
        int a = 0;
        for (int i: nums) {
            a ^= i;
        }
        return a;
    }

    /*
    Other solution:
        - Math: 2∗(a+b+c)−(a+a+b+b+c)=c
     */
}
