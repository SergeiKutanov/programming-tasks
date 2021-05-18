package com.sergeik.sortsearch;

/**
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false
 * if it is not in nums.
 */
public class SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        assert solution(new int[] {2,5,6,0,0,1,2}, 0);
        assert !solution(new int[] {2,5,6,0,0,1,2}, 3);
    }

    private static boolean solution(int[] nums, int target) {
        int firstEl = 0;
        while (firstEl < nums.length - 1 && nums[firstEl] <= nums[firstEl + 1]) {
            firstEl++;
        }
        firstEl++;
        if (firstEl == nums.length)
            firstEl = 0;

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            int middleWithOffset = (middle + firstEl) % nums.length;
            if (nums[middleWithOffset] == target)
                return true;
            else if (nums[middleWithOffset] < target)
                start = middle + 1;
            else
                end = middle - 1;
        }
        return false;
    }

}
