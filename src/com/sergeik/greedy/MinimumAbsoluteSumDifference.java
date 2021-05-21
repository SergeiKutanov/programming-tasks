package com.sergeik.greedy;

import java.util.Arrays;

/**
 * You are given two positive integer arrays nums1 and nums2, both of length n.
 *
 * The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]|
 * for each 0 <= i < n (0-indexed).
 *
 * You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.
 *
 * Return the minimum absolute sum difference after replacing at most one element in the array nums1.
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * |x| is defined as:
 *
 * x if x >= 0, or
 * -x if x < 0.
 */
public class MinimumAbsoluteSumDifference {

    public static void main(String[] args) {

        assert 0 == solution(
                new int[]{2,4,6,8,10},
                new int[]{2,4,6,8,10}
        );
        assert 3 == solution(
                new int[]{1,7,5},
                new int[]{2,3,5}
        );
        assert 20 == solution(
                new int[]{1,10,4,4,2,7},
                new int[]{9,3,5,1,7,4}
        );
    }

    private static int solution(int[] nums1, int[] nums2) {
        int modulo = 1000000007;
        long sum = 0;

        int[] sorted = Arrays.copyOf(nums1, nums1.length);
        Arrays.sort(sorted);

        int save = 0;
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum += diff;
            if (diff == 0)
                continue;
            int replIndex = Arrays.binarySearch(sorted, nums2[i]);
            if (replIndex >= 0) {
                save = Math.max(save, diff);
            } else {
                int actualIndex = -(replIndex + 1);
                if (actualIndex > 0) {
                    save = Math.max(
                            save,
                            diff - Math.abs(sorted[actualIndex - 1] - nums2[i])
                    );
                }
                if (actualIndex < nums1.length) {
                    save = Math.max(
                            save,
                            diff - Math.abs(sorted[actualIndex] - nums2[i])
                    );
                }
            }
        }

        sum -= save;

        return (int)(sum % modulo);
    }

}
