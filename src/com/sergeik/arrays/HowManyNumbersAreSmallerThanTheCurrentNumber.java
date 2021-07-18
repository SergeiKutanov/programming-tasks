package com.sergeik.arrays;

import java.util.Arrays;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is,
 * for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    public static void main(String[] args) {
        assert Arrays.equals(new int[]{4,0,1,1,3}, solution(new int[]{8,1,2,2,3}));
    }

    private static int[] solution(int[] nums) {
        int[] res = new int[nums.length];
        int[] count = new int[101];
        for (int n: nums)
            count[n]++;
        //0 1   2   3   4   5   6   7   8
        //0 1   2   1   0   0   0   0   1
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        //0 1   2   3   4   5   6   7   8
        //0 1   3   4   4   4   4   4   5
        int idx = 0;
        for (int n: nums) {
            res[idx++] = n == 0 ? 0 : count[n - 1];
        }
        return res;
    }

}
