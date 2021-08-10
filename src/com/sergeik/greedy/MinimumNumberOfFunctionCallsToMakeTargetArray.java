package com.sergeik.greedy;


/**
 * Your task is to form an integer array nums from an initial array of zeros arr that is the same size as nums.
 *
 * Return the minimum number of function calls to make nums from arr.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 */
public class MinimumNumberOfFunctionCallsToMakeTargetArray {

    public static void main(String[] args) {
        assert 9 == solution(new int[] {19,20});
        assert 12 == solution(new int[] {0,2,18,19,20});
        assert 8 == solution(new int[] {2,4,8,16});
        assert 7 == solution(new int[] {3,2,2,4});
        assert 6 == solution(new int[] {4,2,5});
    }

    private static int solution(int[] nums) {
        int res = 0, maxLength = 1;
        for (int n: nums) {
            int bits = 0;
            while (n > 0) {
                res += n & 1;
                n >>= 1;
                bits++;
            }
            maxLength = Math.max(maxLength, bits);
        }
        return res + maxLength - 1;
    }

    private static int bruteSolution(int[] nums) {
        int res = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            max = Math.max(max, num);
            while (num > 0) {
                if ((num & 1) == 1) {
                    res++;
                    num--;
                } else {
                    num >>= 1;
                }
            }
        }
        while (max > 0) {
            if ((max & 1) == 1)
                max--;
            else {
                max >>= 1;
                res++;
            }
        }
        return res;
    }

}
