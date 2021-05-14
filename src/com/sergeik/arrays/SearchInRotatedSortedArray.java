package com.sergeik.arrays;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the rotation and an integer target, return the index of target if it is in nums, 
 * or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        assert 4 == solution(new int[] {4,5,6,7,0,1,2}, 0);
        assert -1 == solution(new int[] {4,5,6,7,0,1,2}, 3);
        assert -1 == solution(new int[] {1}, 3);
        assert -1 == solution(new int[0], 4);
    }

    private static int solution(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int firstEl = 0;
        int length = nums.length;
        if (nums[firstEl] > nums[length - 1]) {
            while (firstEl < length - 1 && nums[firstEl] < nums[++firstEl]);
        }
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int offsetMiddle = (middle + firstEl) % length;
            if (nums[offsetMiddle] > target) {
                right = middle - 1;
            } else if (nums[offsetMiddle] < target) {
                left = middle + 1;
            } else {
                return offsetMiddle;
            }
        }
        return -1;
    }


}
