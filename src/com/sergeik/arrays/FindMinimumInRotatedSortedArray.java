package com.sergeik.arrays;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array
 * nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1],
 * a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {3,4,5,1,2});
        assert 0 == solution(new int[] {4,5,6,7,0,1,2});
        assert 11 == solution(new int[] {11,13,15,17});
    }

    private static int solution(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] < nums[right])
            return nums[left];
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] > nums[left])
                left = middle;
            else if (nums[middle] < nums[right])
                right = middle;
            else
                return Math.min(nums[left], Math.min(nums[right], nums[middle]));
        }
        return Math.min(nums[left], nums[right]);
    }

}
