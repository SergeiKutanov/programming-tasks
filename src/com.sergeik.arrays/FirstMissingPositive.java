package com.sergeik.arrays;

import java.util.*;

/**
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 */
public class FirstMissingPositive {

    public static void main(String[] args) {

        assert 7 == arraySolution(new int[]{1,2,6,3,5,4});
        assert 3 == arraySolution(new int[]{1,2,0});
        assert 2 == arraySolution(new int[]{3,4,-1,1});
        assert 1 == arraySolution(new int[]{7,8,9,11,12});

        assert 7 == solution(new int[]{1,2,6,3,5,4});
        assert 3 == solution(new int[]{1,2,0});
        assert 2 == solution(new int[]{3,4,-1,1});
        assert 1 == solution(new int[]{7,8,9,11,12});
    }

    private static int arraySolution(int[] nums) {
        int len = nums.length;
        //ignore all numbers greater than array's length or non positives
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] > len) {
                nums[i] = len + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int n = Math.abs(nums[i]);
            if (n > len)
                continue;
            n--; // to use 0-indexed array
            if (nums[n] >= 0) { //this takes care of duplicate values in array
                nums[n] = -1 * nums[n];
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return len + 1;
    }

    private static int solution(int[] nums) {
        Set<Integer> numbers = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n <= 0)
                continue;
            numbers.add(n);
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }
        if (min > 1)
            return 1;
        for (int i = min + 1; i < max; i++) {
            if (!numbers.contains(i))
                return i;
        }
        return max + 1;
    }

}
