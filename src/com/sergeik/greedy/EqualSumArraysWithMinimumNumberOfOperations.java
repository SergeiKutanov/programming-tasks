package com.sergeik.greedy;

import java.util.Arrays;

/**
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values
 * in the arrays are between 1 and 6, inclusive.
 *
 * In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.
 *
 * Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of
 * values in nums2. Return -1​​​​​ if it is not possible to make the sum of the two arrays equal.
 */
public class EqualSumArraysWithMinimumNumberOfOperations {

    public static void main(String[] args) {
        assert -1 == solution(
                new int[]{1,1,1,1,1,1,1},
                new int[] {6}
        );

        assert 1 == solution(
                new int[] {1},
                new int[] {6}
        );

        assert 3 == solution(
                new int[]{1,2,3,4,5,6},
                new int[]{1,1,2,2,2,2}
        );

        assert 3 == solution(
                new int[] {6,6},
                new int[] {1}
        );

        assert 3 == solution(
                new int[]{1,2,3,4,5,6},
                new int[]{1,1,2,2,2,2}
        );
    }

    private static int solution(int[] nums1, int[] nums2) {
        if (nums2.length * 6 < nums1.length || nums1.length * 6 < nums2.length)
            return -1;
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if (sum1 < sum2)
            return minOperations(nums1, nums2, sum1, sum2);
        return minOperations(nums2, nums1, sum2, sum1);
    }

    private static int minOperations(int[] n1, int[] n2, int sum1, int sum2) {
        int cnt[] = new int[6];
        int diff = sum2 - sum1;
        int res = 0;
        for (int n: n1)
            cnt[6 - n]++;
        for (int n: n2){
            cnt[n - 1]++;
        }
        for (int i = 5; i > 0 && diff >= 0; i--) {
            int take = Math.min(cnt[i], diff / i + (diff % i != 0 ? 1: 0));
            diff -= take * i;
            res += take;
        }
        return res;
    }

}
