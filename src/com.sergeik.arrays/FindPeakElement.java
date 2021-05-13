package com.sergeik.arrays;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. 
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 */
public class FindPeakElement {

    public static void main(String[] args) {
        assert 5 == solution(new int[]{1,2,1,3,5,6,4});
    }

    private static int solution(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;
        while (high - low > 1) {
            int middle = low + (high - low) / 2;
            if (nums[middle] < nums[middle + 1]) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return (low == length - 1 || nums[low] > nums[low + 1]) ? low : high;
    }

}
