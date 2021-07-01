package com.sergeik.dynamic;

/**
 * You are given an integer array nums. You want to maximize the number of points you get by performing the
 * following operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to
 * nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 */
public class DeleteAndEarn {

    public static void main(String[] args) {
        assert 61 == solution(new int[] {8,3,4,7,6,6,9,2,5,8,2,4,9,5,9,1,5,7,1,4});
        assert 6 == solution(new int[] {3,4,2});
        assert 9 == solution(new int[] {2,2,3,3,3,4});
    }

    private static int solution(int[] nums) {
        int[] sums = new int[10002];
        for (int n: nums)
            sums[n] += n;
        for (int i = 2; i < sums.length; i++) {
            sums[i] = Math.max(sums[i - 1], sums[i] + sums[i - 2]);
        }
        return sums[10001];
    }

}
