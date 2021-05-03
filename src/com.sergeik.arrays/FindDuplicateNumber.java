package com.sergeik.arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        assert 2 == solution(new int[]{1,3,4,2,2});
        assert 3 == solution(new int[]{3,1,3,4,2});
        assert 2 == tortoiseHareSolution(new int[]{1,3,4,2,2});
        assert 3 == tortoiseHareSolution(new int[]{3,1,3,4,2});
        assert 1 == tortoiseHareSolution(new int[]{1,1});
        assert 2 == tortoiseHareSolution(new int[]{2,2,2,2,2});
    }

    /**
     * Set solution
     * @param nums
     * @return
     */
    private static int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n: nums) {
            if (set.contains(n))
                return n;
            set.add(n);
        }
        return -1;
    }

    /**
     * Fast slow pointers = same as finding first cycle element in a list
     * @param nums
     * @return
     */
    private static int tortoiseHareSolution(int[] nums) {
        int slowPointer = nums[0];
        int fastPointer = nums[0];
        do {
            slowPointer = nums[slowPointer];
            fastPointer = nums[nums[fastPointer]];
        } while (slowPointer != fastPointer);

        slowPointer = nums[0];
        while (slowPointer != fastPointer) {
            slowPointer = nums[slowPointer];
            fastPointer = nums[fastPointer];
        }
        return fastPointer;
    }

}
