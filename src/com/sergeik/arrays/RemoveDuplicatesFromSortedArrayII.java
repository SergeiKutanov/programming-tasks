package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared
 * at most twice and return the new length.
 *
 * Do not allocate extra space for another array; you must do this by modifying the input array
 * in-place with O(1) extra memory.
 *
 * Clarification:
 *
 * Confused why the returned value is an integer, but your answer is an array?
 *
 * Note that the input array is passed in by reference, which means a modification to the input array
 * will be known to the caller.
 *
 * Internally you can think of this:
 */
public class RemoveDuplicatesFromSortedArrayII {

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,2,2,3};
        assert 5 == solution(nums);
        assert Arrays.equals(
                new int[] {1,1,2,2,3,3},
                nums
        );

        nums = new int[] {0,0,1,1,1,1,2,3,3};
        assert 7 == solution(nums);
        assert Arrays.equals(
                new int[] {0,0,1,1,2,3,3,3,3},
                nums
        );
    }

    private static int solution(int[] nums) {
        int i = 0;
        for (int n: nums) {
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        }
        return i;
    }

    private static int solutionShift(int[] nums) {
        int length = nums.length;
        int startPointer = 0;
        while (startPointer < length) {
            int end = startPointer;
            int val = nums[startPointer];
            while (end < length && val == nums[end]) {
                end++;
            }
            int segmentLen = end - startPointer;
            if (segmentLen > 2) {
                startPointer += 2;
                length = length - segmentLen + 2;
                shift(nums, startPointer, end);
            } else {
                startPointer = end;
            }
        }

        return length;
    }

    private static void shift(int[] nums, int start, int end) {
        while (start < nums.length && end < nums.length) {
            nums[start++] = nums[end++];
        }
    }

}
