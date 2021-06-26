package com.sergeik.arrays;

public class MaximumSumOfTwoNonOverlappingSubArrays {

    public static void main(String[] args) {
        assert 20 == solution(new int[] {0,6,5,2,2,5,1,9,4}, 1, 2);
        assert 29 == solution(new int[] {3,8,1,3,2,1,8,9,0}, 3, 2);
        assert 31 == solution(new int[] {2,1,5,6,0,9,5,0,3,8}, 4, 3);
    }

    private static int bruteSolution(int[] nums, int firstLen, int secondLen) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        int res = 0;
        for (int i = 0; i <= nums.length - firstLen; i++) {
            int fLenSum = prefix[i + firstLen] - prefix[i];
            for (int j = 0; j + secondLen < i; j++) {
                int sLenSum = prefix[j + secondLen] - prefix[j];
                res = Math.max(res, fLenSum + sLenSum);
            }
            for (int j = i + firstLen; j <= nums.length - secondLen; j++) {
                int sLenSum = prefix[j + secondLen] - prefix[j];
                res = Math.max(res, fLenSum + sLenSum);
            }
        }
        return res;
    }

    private static int solution(int[] nums, int firstLen, int secondLen) {
        for (int i = 1; i < nums.length; ++i)
            nums[i] += nums[i - 1];
        int res = nums[firstLen + secondLen - 1], Lmax = nums[firstLen - 1], Mmax = nums[secondLen - 1];
        for (int i = firstLen + secondLen; i < nums.length; ++i) {
            Lmax = Math.max(Lmax, nums[i - secondLen] - nums[i - firstLen - secondLen]);
            Mmax = Math.max(Mmax, nums[i - firstLen] - nums[i - firstLen - secondLen]);
            res = Math.max(res, Math.max(Lmax + nums[i] - nums[i - secondLen], Mmax + nums[i] - nums[i - firstLen]));
        }
        return res;
    }

}
