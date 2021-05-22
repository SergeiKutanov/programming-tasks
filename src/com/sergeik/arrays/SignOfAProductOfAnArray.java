package com.sergeik.arrays;

/**
 *There is a function signFunc(x) that returns:
 *
 * 1 if x is positive.
 * -1 if x is negative.
 * 0 if x is equal to 0.
 * You are given an integer array nums. Let product be the product of all values in the array nums.
 *
 * Return signFunc(product).
 */
public class SignOfAProductOfAnArray {

    public static void main(String[] args) {
        assert 1 == solution(new int[]{-1,-2,-3,-4,3,2,1});
        assert 0 == solution(new int[]{1,5,0,2,-3});
        assert -1 == solution(new int[]{-1,1,-1,1,-1});
    }

    private static int solution(int[] nums) {
        boolean negatives = false;
        for (int n: nums) {
            if (n == 0)
                return 0;
            if (n < 0)
                negatives = !negatives;
        }
        return negatives ? -1 : 1;
    }

}
