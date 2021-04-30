package com.sergeik.arrays;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        assert 2 == solution(new int[]{1,3}, new int[]{2});
        assert 2.5 == solution(new int[]{1,2}, new int[]{3,4});
        assert 0 == solution(new int[]{0,0}, new int[]{0,0});
        assert 1 == solution(new int[]{}, new int[]{1});
        assert 2 == solution(new int[]{2}, new int[]{});
    }

    private static double solution(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int n1Index = 0;
        int n2Index = 0;
        int mIndex = 0;
        while (n1Index < nums1.length && n2Index < nums2.length) {
            if (nums1[n1Index] < nums2[n2Index]) {
                merged[mIndex] = nums1[n1Index];
                n1Index++;
            } else {
                merged[mIndex] = nums2[n2Index];
                n2Index++;
            }
            mIndex++;
        }
        if (n1Index < nums1.length) {
            for (int n = n1Index; n < nums1.length; n++) {
                merged[mIndex] = nums1[n];
                mIndex++;
            }
        }
        if (n2Index < nums2.length) {
            for (int n = n2Index; n < nums2.length; n++) {
                merged[mIndex] = nums2[n];
                mIndex++;
            }
        }

        if (merged.length == 1)
            return merged[0];

        int middle = merged.length / 2;
        if (merged.length % 2 == 1) {
            return merged[middle];
        }

        return (merged[middle] + merged[middle - 1]) / 2.0;

    }

}
