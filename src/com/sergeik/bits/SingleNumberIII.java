package com.sergeik.bits;

import java.util.Arrays;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 */
public class SingleNumberIII {

    public static void main(String[] args) {
        assert Arrays.equals(new int[] {5,3}, solution(new int[] {1,2,1,3,2,5}));
    }

    /**
     * Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:
     *
     * In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find.
     * Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1')
     * in the XOR result. Find
     * out an arbitrary set bit (for example, the rightmost set bit).
     *
     * In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with
     * the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups.
     * XOR numbers in each group, we can find a number in either group.
     * @param nums
     * @return
     */
    private static int[] solution(int[] nums) {
        int xor = 0;
        for (int n: nums)
            xor ^= n;
        //get last set bit
        xor &= -xor;

        int[] res = new int[2];
        for (int n: nums) {
            if ((n & xor) == 0)
                res[0] ^= n;
            else
                res[1] ^= n;
        }
        return res;
    }

}
