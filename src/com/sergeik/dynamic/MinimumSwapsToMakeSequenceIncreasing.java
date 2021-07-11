package com.sergeik.dynamic;

/**
 * You are given two integer arrays of the same length nums1 and nums2. In one operation, you are allowed
 * to swap nums1[i] with nums2[i].
 *
 * For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element at i = 3 to obtain
 * nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
 * Return the minimum number of needed operations to make nums1 and nums2 strictly increasing. The test
 * cases are generated so that the given input always makes it possible.
 *
 * An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].
 */
public class MinimumSwapsToMakeSequenceIncreasing {

    public static void main(String[] args) {
        assert 1 == solution(new int[] {1,3,5,4}, new int[] {1,2,3,7});
    }

    private static int solution(int[] nums1, int[] nums2) {
        int n = nums1.length;
        /**
         * state[i][0] - is min swaps too make A[0..i] and B[0..i] increasing if we do not swap A[i] and B[i];
         * state[i][1] - is min swaps too make A[0..i] and B[0..i] increasing if we swap A[i] and B[i].
         */
        int[][] state = new int[n][2];
        state[0][1] = 1;

        for (int i = 1; i < nums1.length; i++) {
            boolean areBothSelfIncreasing = nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i];
            boolean areInterchangeIncreasing = nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i];
            if (areBothSelfIncreasing && areInterchangeIncreasing) {
                state[i][0] = Math.min(state[i - 1][0], state[i - 1][1]);
                state[i][1] = Math.min(state[i - 1][0], state[i - 1][1]) + 1;
            } else if (areBothSelfIncreasing) {
                state[i][0] = state[i - 1][0];
                state[i][1] = state[i - 1][1] + 1;
            } else {
                state[i][0] = state[i - 1][1];
                state[i][1] = state[i - 1][0] + 1;
            }
        }

        return Math.min(state[n - 1][0], state[n - 1][1]);
    }

}
