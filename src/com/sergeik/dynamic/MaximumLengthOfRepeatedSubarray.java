package com.sergeik.dynamic;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 */
public class MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        assert 3 == solution(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7});
        assert 5 == solution(new int[]{0,0,0,0,0}, new int[]{0,0,0,0,0});
        assert 0 == solution(new int[]{1,2}, new int[]{3,4});
    }

    private static int solution(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

}
