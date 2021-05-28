package com.sergeik.arrays;

/**
 * You are given two non-increasing 0-indexed integer arrays nums1​​​​​​ and nums2​​​​​​.
 *
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if
 * both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i​​​​.
 *
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 *
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 */
public class MaximumDistanceBetweenAPairOfValues {

    public static void main(String[] args) {
        assert 0 == binarySearchSolution(
                new int[]{5,4},
                new int[]{3,2}
        );
        assert 2 == binarySearchSolution(
                new int[]{30,29,19,5},
                new int[]{25,25,25,25,25}
        );
        assert 1 == binarySearchSolution(
                new int[] {2,2,2},
                new int[] {10,10,1}
        );
        assert 2 == binarySearchSolution(
                new int[]{55,30,5,4,2},
                new int[]{100,20,10,10,5}
        );
    }

    private static int binarySearchSolution(int[] nums1, int[] nums2) {
        int max = 0;
        for (int i = 0; i < nums1.length; i++) {
            int curMax = bs(nums1[i], i, nums2);
            max = Math.max(max, curMax);
        }
        return max;
    }

    private static int bs(int n, int i, int[] nums) {
        int left = i;
        int right = nums.length - 1;
        while (left < right) {
            int m = (right + left) / 2 + 1;
            if (nums[m] < n) {
                right = m - 1;
            } else {
                left = m;
            }
        }
        return Math.max(0, right - i);
    }

    private static int solution(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int max = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                i++;
            } else {
                max = Math.max(max, j - i);
                j++;
            }
        }
        return max;
    }

}
