package com.sergeik.sortsearch;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {

        int[] nums1 = new int[] {1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[] {2, 5, 6};
        solution(nums1, 3, nums2, 3);
        assert Arrays.equals(new int[] {1, 2, 2, 3, 5, 6}, nums1);

        nums1 = new int[]{1};
        nums2 = new int[]{};
        solution(nums1, 1, nums2, 0);
        assert Arrays.equals(new int[]{1}, nums1);
    }

    private static void solution(int[] nums1, int m, int[] nums2, int n) {
        int curN1 = 0;
        int curN2 = 0;
        int firstEmpty = m;
        while (curN2 < n) {
            if (nums1[curN1] <= nums2[curN2] && curN1 < m + curN2) {
                curN1++;
            } else if (curN1 >= m + curN2) {
                nums1[curN1] = nums2[curN2];
                curN1++;
                curN2++;
            } else {
                for (int i = firstEmpty; i > curN1; i--) {
                    nums1[i] = nums1[i - 1];
                }
                nums1[curN1] = nums2[curN2];
                curN1++;
                firstEmpty++;
                curN2++;
            }
        }
    }

}
